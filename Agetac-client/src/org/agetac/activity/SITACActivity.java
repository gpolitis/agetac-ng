package org.agetac.activity;

import java.util.ArrayList;
import java.util.List;

import org.agetac.R;
import org.agetac.common.dto.ActionDTO;
import org.agetac.common.dto.ActionDTO.ActionType;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.controller.Controller;
import org.agetac.controller.Controller.ActionFlag;
import org.agetac.entity.Entity.EntityState;
import org.agetac.entity.IEntity;
import org.agetac.fragment.HiddenMenuFragment;
import org.agetac.fragment.OpenedMenuFragment;
import org.agetac.listener.IOnMenuEventListener;
import org.agetac.listener.IOnOverlayEventListener;
import org.agetac.view.LinePicto;
import org.agetac.view.MapOverlay;
import org.agetac.view.MenuGroup;
import org.agetac.view.Shape;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class SITACActivity extends AbstractActivity implements IOnMenuEventListener, IOnOverlayEventListener, OnMenuItemClickListener {
	
	private static final String TAG = "SITACACtivity";
	private static final String EDIT_ITEM = "edit_item";
	
	private MapView mapView;
	private MapOverlay mapOverlay;
	private MapController mapCtrl;
	private FragmentManager fManager;
	private OpenedMenuFragment openedMenuFrag;
	private HiddenMenuFragment hiddenMenuFrag;
	private IEntity currentEntity;
	private PopupMenu popupMenu;
	private AlertDialog editItemDialog;
	private GeoPoint lineBeginGeop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sitac);
		
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		
		mapCtrl = mapView.getController();
		mapCtrl.setZoom(18); // you must call setZoom before setCenter
		
		GeoPoint geoP = new GeoPoint(48115436 ,-1638084);
		mapCtrl.setCenter(geoP);
		
		fManager = getFragmentManager();
		openedMenuFrag = (OpenedMenuFragment) fManager.findFragmentById(R.id.fragment_menu_opened);
		openedMenuFrag.setOnMenuEventListener(this);
		hiddenMenuFrag = (HiddenMenuFragment) fManager.findFragmentById(R.id.fragment_menu_hidden);
		hiddenMenuFrag.setOnMenuEventListener(this);
		fManager.beginTransaction().hide(hiddenMenuFrag).commit();
		
		mapOverlay = new MapOverlay(this);
		mapOverlay.setOnOverlayEventListener(this);
		mapView.getOverlays().add(mapOverlay);
		
		mapView.setBuiltInZoomControls(true);
		
		popupMenu = new PopupMenu(SITACActivity.this, findViewById(R.id.menu_anchor));
		Menu menu = popupMenu.getMenu();
		MenuInflater inflater = popupMenu.getMenuInflater();
		inflater.inflate(R.menu.sitac_context_menu, menu);
		popupMenu.setOnMenuItemClickListener(this);
		
		if (savedInstanceState != null) {
			if (savedInstanceState.getBoolean(EDIT_ITEM, false)) {
				showEditItemDialog();
			}
		}
	}

	@Override
	public void update() {
		List<IEntity> entities = controller.getEntities();
		android.util.Log.d(TAG, entities.toString());
		mapOverlay.clearEntities();
		
		final ArrayList<IEntity> offSitacEntities = new ArrayList<IEntity>();
		for (int i=0; i<entities.size(); i++) {
			IEntity e = entities.get(i);
			
			// si c'est une demande de moyen
			if (e.getModel() instanceof VehicleDemandDTO) {
				VehicleDemandDTO dm = (VehicleDemandDTO) e.getModel();
				// si elle est encore dans l'etat LANCEE
				if (dm.getState() == DemandState.ASKED) {
					// si sa position est definie
					if (e.getState() == EntityState.ON_SITAC) {
						// on l'ajoute a la SITAC
						mapOverlay.addEntity(e);
					} else {
						// sinon on la met dans l'onglet "position a definir"
						offSitacEntities.add(e);
					}
				}
				
			// si ce n'est pas une demande de moyen
			} else {
				// on verifie juste l'etat de l'entitee
				// si sa position est definie
				if (e.getState() == EntityState.ON_SITAC) {
					// on l'ajoute a la SITAC
					mapOverlay.addEntity(e);
				} else {
					// sinon on la met dans l'onglet "position a definir"
					offSitacEntities.add(e);
				}
			}
		}
		
		// on demande aux composants de se mettre a jour graphiquement
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mapView.invalidate();
				openedMenuFrag.addOffSitacEntities(offSitacEntities);
			}
		});
	}

	@Override
	public void onHideMenu() {
		FragmentTransaction fTransac = fManager.beginTransaction();
		fTransac.hide(openedMenuFrag);
		fTransac.show(hiddenMenuFrag);
		fTransac.commit();
	}

	@Override
	public void onShowMenu() {
		FragmentTransaction fTransac = fManager.beginTransaction();
		fTransac.hide(hiddenMenuFrag);
		fTransac.show(openedMenuFrag);
		fTransac.commit();
		openedMenuFrag.startShowMenuAnim();
	}

	@Override
	public void onEntitySelected(IEntity e, MenuGroup grp) {
		this.currentEntity = e;
		android.util.Log.d(TAG, "selected entity menu = "+e.getModel().getName()+" "+e.getModel().getId());
	}

	@Override
	public void onEntityLongPressed(final IEntity entity) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				touchedEntity = entity;
				popupMenu.show();
			}
		});
	}
	
	@Override
	public void onOverlayLongPressed(MotionEvent e, MapView mapView) {

		if (currentEntity != null && currentEntity.getPictogram().getShape() != Shape.LINEAR_SHAPE) {
			GeoPoint m = (GeoPoint) mapView.getProjection().fromPixels(e.getX(), e.getY());
			PositionDTO p = new PositionDTO(m.getLatitudeE6(), m.getLongitudeE6());
			flag = ActionFlag.ADD;
			touchedEntity = currentEntity.clone();
			touchedEntity.getModel().setPosition(p);
			if (touchedEntity.getModel() instanceof VehicleDemandDTO) {
				((VehicleDemandDTO) touchedEntity.getModel()).setState(DemandState.ASKED);
				((VehicleDemandDTO) touchedEntity.getModel()).setType(VehicleType.FPT);
			}
			touchedEntity.setState(EntityState.ON_SITAC);
			
			observable.setChanged();
			observable.notifyObservers(SITACActivity.this);

			// Une fois qu'on a placé l'item sur la SITAC, on le désélectionne
			currentEntity = null;
			//openedMenuFrag.unselectItem();
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_item_delete:
				flag = ActionFlag.REMOVE;
				observable.setChanged();
				observable.notifyObservers(SITACActivity.this);
				break;
				
			case R.id.menu_item_edit:
				showEditItemDialog();
				break;
		}
		return super.onContextItemSelected(item);
	}
	
	private void showEditItemDialog() {
			AlertDialog.Builder builder = new AlertDialog.Builder(SITACActivity.this);
			builder.setTitle(getString(R.string.dialog_title_edit_item, touchedEntity.getModel().getName()));
			LayoutInflater inflater = getLayoutInflater();
			final View dialogView = inflater.inflate(R.layout.edit_item_dialog, null);
			builder.setView(dialogView);
			final EditText nameField = (EditText) dialogView.findViewById(R.id.name);
			nameField.setHint(touchedEntity.getModel().getName());
			
			builder.setPositiveButton(R.string.save, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					flag = ActionFlag.REMOVE;
					observable.setChanged();
					observable.notifyObservers(SITACActivity.this);
					
					touchedEntity.getModel().setName(nameField.getText().toString().trim());
					flag = ActionFlag.ADD;
					observable.setChanged();
					observable.notifyObservers(SITACActivity.this);
				}
			});
			builder.setNegativeButton(R.string.cancel, null);
			editItemDialog = builder.create();
			editItemDialog.show();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (editItemDialog != null && editItemDialog.isShowing()) {
			outState.putBoolean(EDIT_ITEM, true);
			editItemDialog.dismiss();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		return false;
	}

	@Override
	public boolean onUp(MotionEvent end, MapView mapView) {
		if (currentEntity != null && currentEntity.getPictogram().getShape()==Shape.LINEAR_SHAPE) {
			Point start, stop;
			GeoPoint stopGeoP = (GeoPoint) mapView.getProjection().fromPixels(end.getX(), end.getY());
			PositionDTO lineBeginPos = new PositionDTO(lineBeginGeop.getLongitudeE6(), lineBeginGeop.getLatitudeE6());
			PositionDTO lineEndPos = new PositionDTO(stopGeoP.getLongitudeE6(), stopGeoP.getLatitudeE6());
			PositionDTO lineMiddlePos = new PositionDTO((lineBeginGeop.getLongitudeE6()+stopGeoP.getLongitudeE6())/2, (lineBeginGeop.getLatitudeE6()+stopGeoP.getLatitudeE6())/2);
			GeoPoint lineMiddleGeoP = new GeoPoint((int)lineMiddlePos.getLatitude(), (int)lineMiddlePos.getLongitude());
			Point lineMiddlePoint = mapView.getProjection().toMapPixels(lineMiddleGeoP, null);

		
			GeoPoint me = (GeoPoint) mapView.getProjection().fromPixels(end.getX(), end.getY());
			stop = mapView.getProjection().toMapPixels(me, null);
			stop.set(stop.x-lineMiddlePoint.x, stop.y-lineMiddlePoint.y);
			
			start = mapView.getProjection().toMapPixels(lineBeginGeop, null);
			start.set(start.x-lineMiddlePoint.x, start.y-lineMiddlePoint.y);
			
			
			LinePicto lp = new LinePicto(currentEntity.getPictogram().getName(), currentEntity.getPictogram().getBitmap(), currentEntity.getPictogram().getColor(), currentEntity.getPictogram().getState(), currentEntity.getPictogram().getShape(), currentEntity.getPictogram().getGraphicalOverload(), start, stop, mapView.getProjection().metersToEquatorPixels(1.0f));
			touchedEntity = currentEntity.clone();
			touchedEntity.getModel().setPosition(lineMiddlePos);
			((ActionDTO)touchedEntity.getModel()).setPosition(lineBeginPos);
			((ActionDTO)touchedEntity.getModel()).setAim(lineEndPos);
			((ActionDTO)touchedEntity.getModel()).setType(ActionType.FIRE);
			((LinePicto) touchedEntity.getPictogram()).setscaleRef(mapView.getProjection().metersToEquatorPixels(1.0f));
			((LinePicto) touchedEntity.getPictogram()).setStart(start);
			((LinePicto) touchedEntity.getPictogram()).setStop(stop);
			touchedEntity.setState(EntityState.ON_SITAC);
			flag = ActionFlag.ADD;
			observable.setChanged();
			observable.notifyObservers(SITACActivity.this);
			
			// Une fois qu'on a tracé la flêche/ligne/autre sur la SITAC, on la désélectionne
			currentEntity = null;
			return true;
		}
		return false;
	}

	@Override
	public boolean lineBegin(MotionEvent start, MapView mapView) {
		if (currentEntity != null && currentEntity.getPictogram().getShape()==Shape.LINEAR_SHAPE) {
			lineBeginGeop = (GeoPoint) mapView.getProjection().fromPixels(start.getX(), start.getY());
			return true;
		}
		return false;
	}
}
