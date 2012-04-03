package org.agetac.activity;

import org.agetac.controller.Controller;
import org.agetac.controller.Controller.ActionFlag;
import org.agetac.entity.IEntity;
import org.agetac.observer.MyObservable;

import android.app.Activity;
import android.os.Bundle;

public abstract class AbstractActivity extends Activity implements ITabActivity {

	private static final String TOUCHED_ENTITY = "touched_entity";
	
	protected Controller controller;
	protected MyObservable observable;
	protected ActionFlag flag;
	protected IEntity touchedEntity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			touchedEntity = (IEntity) savedInstanceState.getSerializable(TOUCHED_ENTITY);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		controller = Controller.getInstance(this);
		observable = new MyObservable();
		observable.addObserver(controller);
		controller.setCurrentActivity(this);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		controller.quit();
	}
	
	@Override
	public ActionFlag getActionFlag() {
		return flag;
	}
	
	@Override
	public IEntity getTouchedEntity() {
		return touchedEntity;
	}
	
	@Override
	public String getMessage() {
		return null;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (touchedEntity != null) {
			// TODO vérifier la nécessité + check séria des attributs des entities
//			outState.putSerializable(TOUCHED_ENTITY, touchedEntity);
		}
	}
}
