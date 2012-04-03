package org.agetac.engine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observer;

import org.agetac.R;
import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.IModel;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.common.dto.VictimDTO;
import org.agetac.entity.EntityHolder;
import org.agetac.entity.EntityList;
import org.agetac.entity.IEntity;
import org.agetac.handler.AddHandler;
import org.agetac.handler.DeleteHandler;
import org.agetac.handler.UpdateHandler;
import org.agetac.observer.MyObservable;
import org.restlet.engine.Engine;
import org.restlet.ext.jackson.JacksonConverter;

import android.content.Context;

public class InterventionEngine implements IInterventionEngine {

	private static final String TAG = "InterventionEngine";
	
	private InterventionDTO intervention;
	private EntityList entities;
	private MyObservable observable;
	private AgetacClient client;
	private UpdateInterventionThread updateThread;
	private Context context;
	private AddHandler addHandler;
	private DeleteHandler delHandler;
	private UpdateHandler updateHandler;
	private List<MessageDTO> listMessages;
	private int interId = -1;
	
	public InterventionEngine(final Context c) {
		observable = new MyObservable();
		entities = new EntityList();
		this.context = c;
		
		String host = c.getString(R.string.host);
		int port = Integer.valueOf(c.getString(R.string.port));
		
		Engine.getInstance().getRegisteredConverters().add(new JacksonConverter());
		client = new AgetacClient(host, port);
		
		// FIXME recuperer l'id de l'intervention via login/mdp avec
		// intervention liee a un compte
		interId = 0;
		intervention = client.getIntervention(interId);
			
		// cree les handlers pour les operations REST
		addHandler = new AddHandler(entities, client, interId);
		delHandler = new DeleteHandler(entities, client);
		updateHandler = new UpdateHandler(entities, client);

		// thread de MAJ de l'intervention via le serveur en "temps-reel"
		// TODO changer ça pour utiliser un système PUSH afin
		// que ce soit le serveur qui demande au client de se mettre à jour
		// et pas le client qui flood le serveur
		updateThread = new UpdateInterventionThread(this);
		updateThread.start();
	}
	
	@Override
	public boolean sendMessage(MessageDTO m) {
		MessageDTO mess = client.addMessage(interId, m);
		// FIXME check if this is ok (null thing)
		if (mess != null) return true;
		return false;
	}
	
	@Override
	public void addEntity(IEntity entity) {		
		addHandler.handle(entity);
		notifyObservers();
	}

	@Override
	public void removeEntity(IEntity entity) {
		delHandler.handle(entity);		
		notifyObservers();
	}

	@Override
	public void editEntity(IEntity entity) {
		updateHandler.handle(entity);
		notifyObservers();
	}
	
	@Override
	public void updateIntervention() {
		EntityHolder holder = EntityHolder.getInstance(context);
		
		List<VehicleDTO> vehList = new ArrayList<VehicleDTO>(client.getVehicles(interId));
		processUpdate(vehList, VehicleDTO.class);
		
		List<ActionDTO> actList = new ArrayList<ActionDTO>(client.getActions(interId));
		processUpdate(actList, ActionDTO.class);
		
		List<TargetDTO> cibList = new ArrayList<TargetDTO>(client.getTargets(interId));
		processUpdate(cibList, TargetDTO.class);
		
		List<VehicleDemandDTO> dMoyList = new ArrayList<VehicleDemandDTO>(client.getVehicleDemands(interId));
		for (int i=0; i<dMoyList.size(); i++) {
			// traiter les demandes acceptées et les supprimers de la sitac
			// pour les remplacers par des vehicules
			IEntity e = entities.find(dMoyList.get(i).getId(), VehicleDemandDTO.class);
			// si la demande existe deja cote client
			if (e != null) {
				// on met à jour le model de son entitee
				e.setModel(dMoyList.get(i));
				// on cherche à savoir si son état est "ACCEPTE"
				if (dMoyList.get(i).getState() == DemandState.ACCEPTED) {
					// la demande a ete acceptee, il faut donc supprimer
					// la demande de la SITAC pour la remplacer par un vehicule
					ArrayList<VehicleDTO> vList = new ArrayList<VehicleDTO>(intervention.getVehicles());
					for (int k=0; k<vList.size(); k++) {
						if (vList.get(k).getId() == dMoyList.get(i).getVehicleId()) {
							// on cree la future entitee du vehicule
							VehicleDTO v = vList.get(k);
							entities.add(holder.generateEntity(v));
							// on supprime la demande de la SITAC
							entities.remove(dMoyList.get(i));
						}
					}
				}
			}
		}
		
		List<VictimDTO> impList = new ArrayList<VictimDTO>(client.getVictims(interId));
		processUpdate(impList, VictimDTO.class);
		
		listMessages = new ArrayList<MessageDTO>(client.getMessages(interId));
		// TODO process messages differently
		//for(int i=0; i<messList.size(); i++) {
		//android.util.Log.d(TAG, "mess > " +  messList.get(0).toString());}
		
		List<SourceDTO> srcList = new ArrayList<SourceDTO>(client.getSources(interId));
		processUpdate(srcList, SourceDTO.class);
		
		notifyObservers();
	}
	
	/**
	 * Parcours la liste passee en parametre afin de mettre a jour les entitees
	 * deja connues ou bien de creer des entitees pour les objets qu'elle ne
	 * connait pas.
	 * @param list une liste qui etend IModel
	 * @param aClass la classe des objets de la liste
	 */
	private void processUpdate(List<? extends IModel> list, Class<? extends IModel> aClass) {
		EntityHolder holder = EntityHolder.getInstance(context);
		
		for (int i=0; i<list.size(); i++) {
			IEntity e = entities.find(list.get(i).getId(), aClass);
			// si l'entitee existe deja cote client
			if (e != null) {
				// on met à jour son model
				e.setModel(list.get(i));
				
			} else {
				// sinon on lui cree une entitee
				IModel model = list.get(i);
				entities.add(holder.generateEntity(model));
			}
		}
	}

	@Override
	public ArrayList<IEntity> getEntities() {
		return entities;
	}
	
	@Override
	public InterventionDTO getIntervention() {
		return intervention;
	}
	
	public List<MessageDTO> getListMessages() {
		return listMessages;
	}
	
	private void notifyObservers() {
		observable.setChanged();
		observable.notifyObservers(intervention);
	}

	public void addObserver(Observer observer) {
		observable.addObserver(observer);
	}
	
	@Override
	public void stopUpdates() {
		if (updateThread != null) updateThread.doStop();
	}

	@Override
	public int getInterventionId() {
		return interId;
	}
}
