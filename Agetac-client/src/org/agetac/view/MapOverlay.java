package org.agetac.view;

import java.util.ArrayList;
import java.util.List;

import org.agetac.entity.IEntity;
import org.agetac.listener.IOnOverlayEventListener;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;

public class MapOverlay extends Overlay {
	
	private static final String TAG = "MapOverlay";
	
	private List<IEntity> items;
	private Paint itemPaint;
	private IOnOverlayEventListener listener;
	private int precision;
	private boolean lineTracingBegin,hasMoved;
	

	public MapOverlay(Context context) {
		super(context);
		items = new ArrayList<IEntity>();
		itemPaint = new Paint();
		itemPaint.setColor(Color.RED);
		itemPaint.setAntiAlias(true);
		
		// TODO permettre à l'utilisateur de pouvoir modifier la precision dans
		// les paramètres de l'application
		precision = 5;
		
		lineTracingBegin = hasMoved = false;
	}
	
	private boolean checkNoItemSelected(MotionEvent e, MapView mapView) {
		IGeoPoint clickedP = mapView.getProjection().fromPixels(e.getX(), e.getY());
		for (int i=0; i<items.size(); i++) {
			if (items.get(i).isCloseTo(clickedP, precision)) {
				if (listener != null) {
					listener.onEntityLongPressed(items.get(i));
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean onLongPress(MotionEvent e, MapView mapView) {
		if (checkNoItemSelected(e,mapView) && listener != null) {
			listener.onOverlayLongPressed(e, mapView);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onDown(MotionEvent event, MapView mapView) {
		lineTracingBegin = listener.lineBegin(event, mapView);
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		if (event.getAction()==MotionEvent.ACTION_UP) {
			if (lineTracingBegin && hasMoved && listener.onUp(event, mapView)) {
				lineTracingBegin = false;
				hasMoved = false;
			}
			else {
				lineTracingBegin = false;
				return false;
			}
		}
		if (lineTracingBegin) {
			hasMoved = true;
			}
		return lineTracingBegin;
	}
	
	
	@Override
	protected void draw(Canvas canvas, MapView mapV, boolean shadow) {
		IEntity item;
		
		for (int i=0; i<items.size(); i++) {
			item = items.get(i);
			//android.util.Log.d(TAG,item.toString());
			item.draw(canvas, mapV, shadow);
		}
	}
	
	public void addEntities(List<IEntity> entities) {
		items.clear();
		items.addAll(entities);
	}
	
	public void addEntity(IEntity entity) {
		items.add(entity);
	}
	
	public void setOnOverlayEventListener(IOnOverlayEventListener listener) {
		this.listener = listener;
	}
	
	public void removeOnOverlayEventListener() {
		this.listener = null;
	}

	/**
	 * Vide toutes les entitées de l'overlay
	 */
	public void clearEntities() {
		items.clear();
	}
	
}
