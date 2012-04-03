package org.agetac.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.agetac.R;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDTO.VehicleState;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.controller.Controller;
import org.agetac.controller.Controller.ActionFlag;
import org.agetac.entity.EntityHolder;
import org.agetac.entity.IEntity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.SimpleAdapter;

public class MoyensActivity extends AbstractActivity implements OnClickListener, OnMenuItemClickListener, OnItemClickListener {
	
	private static final String TAG				= "MoyensActivity";
	private static final String DATA_IMG		= "data_img";
	private static final String DATA_TYPE		= "data_type";
	private static final String DATA_CASERNE	= "data_caserne";
	private static final String DATA_ETAT		= "data_etat";
	private static final String DATA_GHDEM		= "data_gh_demande";
	private static final String DATA_GHARR		= "data_gh_arrivee";
	private static final String DATA_GHRET		= "data_gh_retour";
	
	private SimpleAdapter listAdapter;
	private ListView listView;
	private List<Hashtable<String, String>> data;
	private PopupMenu popupMenu;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moyens);
	    
		listView = (ListView) findViewById(R.id.moyens_listview);
	    
	    // Creation de l'ArrayList qui nous permettra de remplir la listView
        data = new ArrayList<Hashtable<String, String>>();
        
        //Creation d'un SimpleAdapter qui se chargera de mettre les items present dans notre list (listItem) dans la vue moyens_list_item
        listAdapter = new SimpleAdapter(this.getBaseContext(), data, R.layout.moyens_list_item,
               new String[] {DATA_IMG, DATA_TYPE, DATA_CASERNE, DATA_ETAT, DATA_GHDEM, DATA_GHARR, DATA_GHRET}, new int[] {R.id.img, R.id.type_vehicule, R.id.caserne_vehicule, R.id.etat_vehicule, R.id.gh_demande, R.id.gh_arrivee, R.id.gh_retour});
        listView.setAdapter(listAdapter);
        
        listView.setOnItemClickListener(this);

		((Button) findViewById(R.id.btn_demande_moyens)).setOnClickListener(this);
		
		popupMenu = new PopupMenu(MoyensActivity.this, findViewById(R.id.menu_moyens));
		Menu menu = popupMenu.getMenu();
		MenuInflater inflater = popupMenu.getMenuInflater();
		inflater.inflate(R.menu.moyens_context_menu, menu);
		popupMenu.setOnMenuItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_demande_moyens:
				android.util.Log.d(TAG, "CLICK JE DEMANDE");
				popupMenu.show();		
//				flag = ActionFlag.ADD;
//				VehicleDTO veh = genVehicleDTO();
//		        IPictogram vehiculePicto = PictogramHolder.getInstance(this).getPictogram(PictogramHolder.RED_GRP);
//		        touchedEntity = new Entity<VehicleDTO>(veh, vehiculePicto, EntityState.OFF_SITAC);
//				observable.setChanged();
//				observable.notifyObservers(MoyensActivity.this);
				break;
		}
			
	}

	@Override
	public void onItemClick(AdapterView<?> adpt, View v, int index, long l) {
		 try {
			 final List<IEntity> entities = controller.getEntities();
			 final int position = index;
			 final AlertDialog.Builder confirmDelete = new AlertDialog.Builder(this);
			 confirmDelete.setTitle(R.string.dialog_title_vehicule);
			 confirmDelete.setMessage(R.string.dialog_update_gh_ou_supprimer_vehicule);
			 confirmDelete.setPositiveButton(R.string.dialog_vehicule_supprimer, new
				 DialogInterface.OnClickListener() {
					 @Override
					 public void onClick(DialogInterface dialog, int which) {
						 flag = ActionFlag.REMOVE;
						 touchedEntity = (IEntity) entities.get(position);
						 observable.setChanged();
						 observable.notifyObservers(MoyensActivity.this);
					}
				 });
//			 confirmDelete.setNeutralButton(R.string.dialog_vehicule_gerer_gh, new
//				 DialogInterface.OnClickListener() {
//					 @Override
//					 public void onClick(DialogInterface dialog, int which) {
//						 dialog.dismiss();
//						 AlertDialog.Builder gererGH = new AlertDialog.Builder(contx, R.layout.moyens_update_gh);
//						 gererGH.setTitle(R.string.dialog_title_vehicule);
//						 gererGH.setMessage(R.string.dialog_update_gh_ou_supprimer_vehicule);
//						 gererGH.setPositiveButton(R.string.dialog_vehicule_supprimer, new
//							 DialogInterface.OnClickListener() {
//								 @Override
//								 public void onClick(DialogInterface dialog, int which) {
//									 flag = ActionFlag.REMOVE;
//									 touchedEntity = (IEntity) entities.get(position);
//									 observable.setChanged();
//									 observable.notifyObservers(MoyensActivity.this);
//								}
//							 });
//						 /*
//						 flag = ActionFlag.UPDATE_GH;
//						 touchedEntity = (IEntity) entities.get(position);
//						 observable.setChanged();
//						 observable.notifyObservers(MoyensActivity.this);
//						 */
//					}
//				 });
			 confirmDelete.setNegativeButton(R.string.annuler, null);
			 confirmDelete.show();		
		 } catch (ClassCastException e) {
			 android.util.Log.e(TAG, e.getMessage());
		 }
		
	}	

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		VehicleDemandDTO dm;
		flag = ActionFlag.ADD;
		
		switch (item.getItemId()) {
			case R.id.FPT:
				// TODO récupérer le nom via un formulaire
				dm = genVehicleDemandDTO(VehicleType.FPT);
				break;
	
			case R.id.CCGC:
				dm = genVehicleDemandDTO(VehicleType.CCGC);
				break;
	
			case R.id.VSAV:
				dm = genVehicleDemandDTO(VehicleType.VSAV);
				break;
				
			case R.id.menu_attaque:
				return false;
				
				
			default:
				// TODO récupérer le nom via un formulaire
				return false;
		}
		
		touchedEntity = EntityHolder.getInstance(this).generateEntity(dm);
		observable.setChanged();
		observable.notifyObservers(MoyensActivity.this);
		return super.onContextItemSelected(item);
	}
	
  
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moyens_context_menu, menu);
        menu.getItem(0).getSubMenu().setHeaderIcon(R.drawable.firetruck);
  
        return true;
     }
		
	@Override
	public void update() {
		List<IEntity> entities = controller.getEntities();
		data.clear();
		Hashtable<String, String> map;
		for (int i=0; i<entities.size(); i++) {
			// la map pour la prochaine ligne a ajouter
			map = new Hashtable<String, String>();
			
			if (entities.get(i).getModel() instanceof VehicleDTO) {
				VehicleDTO v = (VehicleDTO) entities.get(i).getModel();
				map.put(DATA_IMG, ""+R.drawable.firetruck);
				map.put(DATA_TYPE, v.getName());
				map.put(DATA_CASERNE, v.getBarrackName());
				VehicleState ev = v.getState();
				map.put(DATA_ETAT, ev.toString());
				map.put(DATA_GHDEM, getString(R.string.unknown)); // mapping GH non implemente sur le model
				map.put(DATA_GHARR, getString(R.string.unknown)); // mapping GH non implemente sur le model
				map.put(DATA_GHRET, getString(R.string.unknown)); // mapping GH non implemente sur le model
				// on ajoute la map a la liste
				data.add(map);
			
			} else if (entities.get(i).getModel() instanceof VehicleDemandDTO) {
				VehicleDemandDTO dm = (VehicleDemandDTO) entities.get(i).getModel();
				// si la demande est en cours ou qu'elle a ete refusee on l'affiche
				// sinon ça signifie que le vehicule est deja affiche
				if (dm.getState() != DemandState.ACCEPTED) {
					map.put(DATA_IMG, ""+R.drawable.firetruck);
					map.put(DATA_TYPE, dm.getType().name());
					map.put(DATA_CASERNE, getString(R.string.unknown));
					map.put(DATA_ETAT, dm.getState().name());
					map.put(DATA_GHDEM, getString(R.string.unknown)); // mapping GH non implemente sur le model
					map.put(DATA_GHARR, getString(R.string.unknown)); // mapping GH non implemente sur le model
					map.put(DATA_GHRET, getString(R.string.unknown)); // mapping GH non implemente sur le model
					// on ajoute la map a la liste
					data.add(map);
				}
			}
		}
		
		// on notifie l'adapter que le contenu de la liste a change
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				listAdapter.notifyDataSetChanged();
			}
		});
	}
	
	public VehicleDemandDTO genVehicleDemandDTO(VehicleType type) {
		VehicleDemandDTO dm = new VehicleDemandDTO();
		dm.setType(type);
		dm.setState(DemandState.ASKED);
		dm.setTimestamp(new Date());
		return dm;
	}

	public String getTime(){
		Time t = new Time();
		t.setToNow();
		return (String) t.toString().subSequence(9, 13);
	}
}
