package org.agetac.activity;

import java.util.List;
import java.util.Random;

import org.agetac.R;
import org.agetac.controller.Controller;
import org.agetac.controller.Controller.ActionFlag;
import org.agetac.entity.IEntity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SOEICActivity extends AbstractActivity implements OnClickListener, OnItemClickListener {
	
	private Button addEntityBtn;
	private TextView nbCurrentEntityView;
	private int nbCurrentEntity;
	private TextView nbFuturEntityView;
	private int nbFuturEntity;
	private ListView vehiculeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soeic);
		
		addEntityBtn = (Button) findViewById(R.id.addEntity);
		addEntityBtn.setOnClickListener(this);
		
		nbCurrentEntityView = (TextView) findViewById(R.id.nbCurrentEntity);
		nbFuturEntityView = (TextView) findViewById(R.id.nbFuturEntity);
		
		vehiculeList = (ListView) findViewById(R.id.vehicules_listview);
		vehiculeList.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Random gen = new Random();
		
		switch (v.getId()) {
		
			case R.id.addEntity:
//				flag = ActionFlag.ADD;
//				touchedEntity = new Vehicule("Entity "+(gen.nextInt(41)+1), false);
//				observable.setChanged();
//				observable.notifyObservers(SOEICActivity.this);
				break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> adpt, View v, int index, long l) {
		try {
			final AdapterView adapter = adpt;
			final int position = index;
			AlertDialog.Builder confirmDelete = new AlertDialog.Builder(this);
			confirmDelete.setTitle(R.string.dialog_title_deletevehicule);
			confirmDelete.setMessage(R.string.dialog_deletevehicule);
			confirmDelete.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ArrayAdapter<IEntity> vehicules = (ArrayAdapter) adapter.getAdapter();
					touchedEntity = vehicules.getItem(position);
					flag = ActionFlag.REMOVE;
					observable.setChanged();
					observable.notifyObservers(SOEICActivity.this);
				}
			});
			confirmDelete.setNegativeButton(R.string.no, null);
			confirmDelete.show();
			
		} catch (ClassCastException e) {}
	}

	@Override
	public void update() {
		List<IEntity> entities = controller.getEntities();
		ArrayAdapter<IEntity> adapter = new ArrayAdapter<IEntity>(
				this,
				android.R.layout.simple_list_item_1,
				entities
		);
		vehiculeList.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		nbCurrentEntity = 0;
		nbFuturEntity = 0;
		
//		for (int i=0; i<entities.size(); i++) {
//			if (entities.get(i).isDeBase()) {
//				nbCurrentEntity++;
//			} else {
//				nbFuturEntity++;
//			}
//		}
		nbCurrentEntityView.setText(String.valueOf(nbCurrentEntity));
		nbFuturEntityView.setText(String.valueOf(nbFuturEntity));
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}