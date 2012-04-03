package org.agetac.entity;


import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.AgentDTO;
import org.agetac.common.dto.BarrackDTO;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.IModel;
import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VictimDTO;
import org.agetac.view.IPictogram;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.graphics.Canvas;
import android.graphics.Point;

public class Entity implements IEntity {

	private static final long serialVersionUID = 9102938L;
	private static final int EARTH_RADIUS_METERS = 6378137;

	public enum EntityState {ON_SITAC, OFF_SITAC}
	
	private IModel model;
	private IPictogram picto;
	private IGeoPoint geoP;
	private EntityState state;
	
	public Entity(IModel model, IPictogram picto, EntityState state) {
		this.model = model;
		this.picto = picto;
		this.state = state;
		if (model == null) {
			System.out.println("model == NULL");
		} else {
			System.out.println("model NOT NULL >> "+model.getClass().getSimpleName());
			if (model.getPosition() == null) {
				System.out.println("position du model == null"+model.getClass().getSimpleName());
			} else {
				System.out.println("position du model NON NULL"+model.getClass().getSimpleName());
			}
		}
		int latE6 = (int) model.getPosition().getLatitude();
		int longE6 = (int) model.getPosition().getLongitude();
		this.geoP = new GeoPoint(latE6, longE6);
	}
	
	@Override
	public IGeoPoint getGeoPoint() {
		return geoP;
	}


	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IPictogram getPictogram() {
		return picto;
	}

	@Override
	public IEntity clone() {
		if (model instanceof ActionDTO) {
			return new Entity(new ActionDTO(new PositionDTO(model.getPosition()), ((ActionDTO) model).getType(), new PositionDTO(((ActionDTO) model).getOrigin()), new PositionDTO(((ActionDTO) model).getAim())), picto.clone(), state);
		} else if (model instanceof AgentDTO) {
			//humhum?
			//return new Entity(new AgentDTO(model.getId(), model.getId(), ((AgentDTO) model).getAptitude(), ((AgentDTO) model).getSubordonnes()), picto.clone(), state);
		} else if (model instanceof BarrackDTO) {
			//humhum?
			//return new Entity(new ActionDTO(model.getId(), new PositionDTO(model.getPosition()), ((ActionDTO) model).getActionDTOType(), new PositionDTO(((ActionDTO) model).getOrigin()), new PositionDTO(((ActionDTO) model).getAim())), picto.clone(), state);
		} else if (model instanceof TargetDTO) {
			return new Entity(new TargetDTO(new PositionDTO(model.getPosition()), ((TargetDTO) model).getType()), picto.clone(), state);
		} else if (model instanceof VehicleDemandDTO) {
			return new Entity(new VehicleDemandDTO(model.getName(), new PositionDTO(model.getPosition()), ((VehicleDemandDTO) model).getState(), ((VehicleDemandDTO) model).getGroup()), picto.clone(), state);
		} else if (model instanceof GroupDTO) {
			//humhum?
			//return new Entity(new ActionDTO(model.getId(), new PositionDTO(model.getPosition()), ((ActionDTO) model).getActionDTOType(), new PositionDTO(((ActionDTO) model).getOrigin()), new PositionDTO(((ActionDTO) model).getAim())), picto.clone(), state);
		} else if (model instanceof VictimDTO) {
			//humhum?
			//return new Entity(new ActionDTO(model.getId(), new PositionDTO(model.getPosition()), ((ActionDTO) model).getActionDTOType(), new PositionDTO(((ActionDTO) model).getOrigin()), new PositionDTO(((ActionDTO) model).getAim())), picto.clone(), state);
		} else if (model instanceof InterventionDTO) {
			//humhum?
			//return new Entity(new ActionDTO(model.getId(), new PositionDTO(model.getPosition()), ((ActionDTO) model).getActionDTOType(), new PositionDTO(((ActionDTO) model).getOrigin()), new PositionDTO(((ActionDTO) model).getAim())), picto.clone(), state);
		} else if (model instanceof SourceDTO) {
			return new Entity(new SourceDTO(new PositionDTO(model.getPosition()), ((SourceDTO) model).getType()), picto.clone(), state);
		} else if (model instanceof VehicleDTO) {
			//not sure of that
			return new Entity(new VehicleDTO(new PositionDTO(model.getPosition()), ((VehicleDTO) model).getType(), ((VehicleDTO) model).getBarrackName(), ((VehicleDTO) model).getState(), ((VehicleDTO) model).getGroup(), "Tsoin tsoin"), picto.clone(), state);
		}
		return null;
	}

	@Override
	public EntityState getState() {
		return state;
	}
	
	@Override
	public void setState(EntityState state) {
		this.state = state;
	}

	@Override
	public boolean isCloseTo(IGeoPoint pt, int precision) {
		double distance = getDistanceInMeter(geoP, pt);
		return (distance <= precision);
	}

	@Override
	public void draw(Canvas canvas, MapView mapV, boolean shadow) {
		Point p;
		p = mapV.getProjection().toMapPixels(geoP, null);
		picto.draw(canvas, p, shadow, mapV.getProjection());
	}

	@Override
	public void setModel(IModel model) {
		this.model = model;
		int latE6 = (int) model.getPosition().getLatitude();
		int longE6 = (int) model.getPosition().getLongitude();
		this.geoP = new GeoPoint(latE6, longE6);
		
		if (latE6 != 0 && longE6 != 0) {
			state = EntityState.ON_SITAC;
		}
	}
	
	/**
	 * Retourne la distance en mètre entre deux coordonnées GPS
	 * 
	 * @param src
	 *            une coordonnée GPS au format IGeoPoint
	 * @param dest
	 *            une coordonnée GPS au format IGeoPoint
	 * @return la distance en mètre entre ces deux coordonées
	 */
	public static double getDistanceInMeter(final IGeoPoint src, IGeoPoint dest) {
		double distance = 0;
		
        // premier point
        double srcLat = src.getLatitudeE6()*(1E-6);
        double srcLong = src.getLongitudeE6()*(1E-6);
        // deuxieme point
        double destLat = dest.getLatitudeE6()*(1E-6);
        double destLong = dest.getLongitudeE6()*(1E-6);

        // calcul des angles en radians
        double rlo1 = Math.toRadians(srcLong);
        double rla1 = Math.toRadians(srcLat);
        double rlo2 = Math.toRadians(destLong);
        double rla2 = Math.toRadians(destLat);

        double dlo = (rlo2 - rlo1) / 2;
        double dla = (rla2 - rla1) / 2;

        double angle = (Math.sin(dla) * Math.sin(dla)) + Math.cos(rla1)
                * Math.cos(rla2) * (Math.sin(dlo) * Math.sin(dlo));

        distance = EARTH_RADIUS_METERS
                * (2 * Math.atan2(Math.sqrt(angle), Math
                        .sqrt(1 - angle)));
		
		return distance;
	}
	
	@Override
	public String toString() {
		return "["+state.name()+"] "+model.toString();
	}
}
