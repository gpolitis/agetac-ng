package org.agetac.listener;

import org.agetac.entity.IEntity;
import org.osmdroid.views.MapView;

import android.view.MotionEvent;

public interface IOnOverlayEventListener {

	public void onEntityLongPressed(final IEntity entity);
	
	public void onOverlayLongPressed(final MotionEvent event, final MapView mapView);
	
	public boolean onTouchEvent(final MotionEvent event, final MapView mapView);

	public boolean lineBegin(final MotionEvent event, final MapView mapView);

	public boolean onUp(final MotionEvent end, final MapView mapView);

}
