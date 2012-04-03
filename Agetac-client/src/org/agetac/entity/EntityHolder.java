package org.agetac.entity;

import java.util.ArrayList;

import org.agetac.R;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.GroupDTO;
import org.agetac.common.dto.IModel;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.SourceDTO.SourceType;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.TargetDTO.TargetType;
import org.agetac.common.dto.VehicleDTO;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.entity.Entity.EntityState;
import org.agetac.view.Color;
import org.agetac.view.GraphicalOverload;
import org.agetac.view.LinePicto;
import org.agetac.view.Pictogram;
import org.agetac.view.Shape;
import org.agetac.view.State;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;

public class EntityHolder {

	public static final String RED_UP = "SourceDTO de feu";
	public static final String RED_DOWN = "red_down";
	public static final String GREEN_UP = "green_up";
	public static final String GREEN_DOWN = "green_down";
	public static final String BLUE_UP = "SourceDTO d'eau";
	public static final String BLUE_DOWN = "blue_down";
	public static final String ORANGE_UP = "Produits chimiques";
	public static final String ORANGE_DOWN = "orange_down";
	
	public static final String BLUE_NONE = "blue_none";
	public static final String BLUE_GRP = "blue_grp";
	public static final String BLUE_COL = "blue_col";
	public static final String BLUE_ISOLE = "blue_isole";
	
	public static final String GREEN_NONE = "green_none";
	public static final String GREEN_GRP = "green_grp";
	public static final String GREEN_COL = "green_col";
	public static final String GREEN_ISOLE = "green_isole";
	
	public static final String RED_DOTTED_SINGLE = "red_dotted_single";
	public static final String RED_DOTTED_NONE = "red_dotted_none";
	
	public static final String RED_NONE = "red_none";
	public static final String RED_GRP = "red_grp";
	public static final String RED_COL = "red_col";
	public static final String RED_ISOLE = "red_isole";
	
	public static final String LINE = "Ligne";
	public static final String POINT = "Point";
	public static final String ZONE = "Zone";

	private static EntityHolder instance;
	private Resources res;
	private ArrayList<IEntity> entities;

	private EntityHolder(Context context) {
		this.res = context.getResources();
		entities = new ArrayList<IEntity>();
		
		Options options = new Options();
		options.inPurgeable = false;
		
		entities.add(new Entity(new SourceDTO(SourceType.FIRE), new Pictogram(RED_UP, BitmapFactory.decodeResource(res, R.drawable.picto_red_up, options), Color.RED, State.STATE_HAPPENING, Shape.TRIANGLE_UP, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new TargetDTO(TargetType.FIRE), new Pictogram(RED_DOWN, BitmapFactory.decodeResource(res, R.drawable.picto_red_down, options), Color.RED, State.STATE_HAPPENING, Shape.TRIANGLE_DOWN, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new TargetDTO(TargetType.HUMAN), new Pictogram(GREEN_DOWN, BitmapFactory.decodeResource(res, R.drawable.picto_green_down), Color.GREEN, State.STATE_HAPPENING, Shape.TRIANGLE_DOWN, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new SourceDTO(SourceType.CHEM), new Pictogram(ORANGE_UP, BitmapFactory.decodeResource(res, R.drawable.picto_orange_up), Color.ORANGE, State.STATE_HAPPENING, Shape.TRIANGLE_UP, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new TargetDTO(TargetType.CHEM), new Pictogram(ORANGE_DOWN, BitmapFactory.decodeResource(res, R.drawable.picto_orange_down), Color.ORANGE, State.STATE_HAPPENING, Shape.TRIANGLE_DOWN, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new SourceDTO(SourceType.WATER), new Pictogram(BLUE_UP, BitmapFactory.decodeResource(res, R.drawable.picto_blue_up), Color.BLUE, State.STATE_HAPPENING, Shape.TRIANGLE_UP, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new TargetDTO(TargetType.WATER), new Pictogram(BLUE_DOWN, BitmapFactory.decodeResource(res, R.drawable.picto_blue_down), Color.BLUE, State.STATE_HAPPENING, Shape.TRIANGLE_DOWN, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(BLUE_NONE, BitmapFactory.decodeResource(res, R.drawable.picto_blue_none), Color.BLUE, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(BLUE_ISOLE, BitmapFactory.decodeResource(res, R.drawable.picto_blue_isole), Color.BLUE, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.ISOLE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(BLUE_GRP, BitmapFactory.decodeResource(res, R.drawable.picto_blue_grp), Color.BLUE, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.GROUPE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(BLUE_COL, BitmapFactory.decodeResource(res, R.drawable.picto_blue_col), Color.BLUE, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.COLONNE), EntityState.OFF_SITAC));
	
		
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(GREEN_NONE, BitmapFactory.decodeResource(res, R.drawable.picto_green_none), Color.GREEN, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(GREEN_ISOLE, BitmapFactory.decodeResource(res, R.drawable.picto_green_isole), Color.GREEN, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.ISOLE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(GREEN_GRP, BitmapFactory.decodeResource(res, R.drawable.picto_green_grp), Color.GREEN, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.GROUPE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(GREEN_COL, BitmapFactory.decodeResource(res, R.drawable.picto_green_col), Color.GREEN, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.COLONNE), EntityState.OFF_SITAC));
		
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(RED_DOTTED_SINGLE, BitmapFactory.decodeResource(res, R.drawable.picto_red_dotted_isole), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.ISOLE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(RED_DOTTED_NONE, BitmapFactory.decodeResource(res, R.drawable.picto_red_dotted_none), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(RED_NONE, BitmapFactory.decodeResource(res, R.drawable.picto_red_none), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new VehicleDemandDTO(), new Pictogram(RED_ISOLE, BitmapFactory.decodeResource(res, R.drawable.picto_red_isole), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.ISOLE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(RED_GRP, BitmapFactory.decodeResource(res, R.drawable.picto_red_grp), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.GROUPE), EntityState.OFF_SITAC));
		entities.add(new Entity(new GroupDTO(), new Pictogram(RED_COL, BitmapFactory.decodeResource(res, R.drawable.picto_red_col), Color.RED, State.STATE_HAPPENING, Shape.SQUARE, GraphicalOverload.COLONNE), EntityState.OFF_SITAC));
		
		entities.add(new Entity(new ActionDTO(), new LinePicto(LINE, BitmapFactory.decodeResource(res, R.drawable.picto_line), Color.BLACK, State.STATE_HAPPENING, Shape.LINEAR_SHAPE, GraphicalOverload.NONE, new Point(0,0), new Point(0,0), 1), EntityState.OFF_SITAC));
		entities.add(new Entity(new SourceDTO(), new Pictogram(POINT, BitmapFactory.decodeResource(res, R.drawable.picto_point), Color.BLACK, State.STATE_HAPPENING, Shape.CIRCLE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
		entities.add(new Entity(new SourceDTO(), new Pictogram(ZONE, BitmapFactory.decodeResource(res, R.drawable.picto_zone), Color.BLACK, State.STATE_HAPPENING, Shape.STAR_SHAPE, GraphicalOverload.NONE), EntityState.OFF_SITAC));
	}

	public static EntityHolder getInstance(Context context) {
		if (instance == null) {
			instance = new EntityHolder(context);
		}
		return instance;
	}

	public ArrayList<IEntity> getEntities() {
		return entities;
	}

	public ArrayList<IEntity> getEntities(Shape s) {
		ArrayList<IEntity> list = new ArrayList<IEntity>();

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPictogram().getShape() == s)
				list.add(entities.get(i));
		}

		return list;
	}

	public ArrayList<IEntity> getEntities(Color c) {
		ArrayList<IEntity> list = new ArrayList<IEntity>();

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPictogram().getColor() == c)
				list.add(entities.get(i));
		}

		return list;
	}

	public ArrayList<IEntity> getEntities(State s) {
		ArrayList<IEntity> list = new ArrayList<IEntity>();

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPictogram().getState() == s)
				list.add(entities.get(i));
		}

		return list;
	}

	public ArrayList<IEntity> getEntities(GraphicalOverload go) {
		ArrayList<IEntity> list = new ArrayList<IEntity>();

		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPictogram().getGraphicalOverload() == go)
				list.add(entities.get(i));
		}

		return list;
	}

	public String[] getEntitiesName() {
		String[] names = new String[entities.size()];
		for (int i = 0; i < entities.size(); i++) {
			names[i] = entities.get(i).getPictogram().getName();
		}
		return names;
	}

	public IEntity getEntity(String name) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getPictogram().getName().equals(name))
				return entities.get(i);
		}
		return null;
	}
	
	public IEntity generateEntity(IModel model) {
		// default entity
		IEntity ent = getEntity(EntityHolder.RED_ISOLE).clone();
		
		if (model instanceof VehicleDTO) {
			switch (((VehicleDTO) model).getType()) {
				case FPT:
					ent = getEntity(EntityHolder.RED_ISOLE).clone();
					break;
					
				case VSAV:
					ent = getEntity(EntityHolder.GREEN_ISOLE).clone();
					break;
					
				// TODO prendre en compte les autres cas
			}
			
		} else if (model instanceof SourceDTO) {
			switch (((SourceDTO) model).getType()) {
				case CHEM:
					ent = getEntity(EntityHolder.ORANGE_UP).clone();
					break;
					
				case WATER:
					ent = getEntity(EntityHolder.BLUE_UP).clone();
					break;
					
				case FIRE:
					ent = getEntity(EntityHolder.RED_UP).clone();
					break;
			}
			
		} else if (model instanceof TargetDTO) {
			switch (((TargetDTO) model).getType()) {
				case CHEM:
					ent = getEntity(EntityHolder.ORANGE_DOWN).clone();
					break;
					
				case WATER:
					ent = getEntity(EntityHolder.BLUE_DOWN).clone();
					break;
					
				case FIRE:
					ent = getEntity(EntityHolder.RED_DOWN).clone();
					break;
					
				case HUMAN:
					ent = getEntity(EntityHolder.GREEN_DOWN).clone();
					break;
			}
			
		} else if (model instanceof VehicleDemandDTO) {
			switch (((VehicleDemandDTO) model).getType()) {
			case FPT:
				ent = getEntity(EntityHolder.RED_ISOLE).clone();
				break;
				
			case VSAV:
				ent = getEntity(EntityHolder.GREEN_ISOLE).clone();
				break;
				
			// TODO prendre en compte les autres cas
			}
		}
		
		if (model.getPosition().getLatitude() == 0 && model.getPosition().getLongitude() == 0) {
			// alors l'item n'a pas de position definie et donc son etat est OFF_SITAC
			ent.setState(EntityState.OFF_SITAC);
		
		} else {
			ent.setState(EntityState.ON_SITAC);
		}
		
		ent.setModel(model);
		
		return ent;
	}
}
