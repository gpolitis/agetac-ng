package org.agetac.fragment;

import java.util.ArrayList;

import org.agetac.R;
import org.agetac.entity.EntityHolder;
import org.agetac.entity.IEntity;
import org.agetac.listener.IOnMenuEventListener;
import org.agetac.view.Color;
import org.agetac.view.MenuExpandableListAdapter;
import org.agetac.view.MenuGroup;
import org.agetac.view.Shape;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageButton;

public class OpenedMenuFragment extends Fragment implements IMenuFragment, OnClickListener, OnChildClickListener, OnGroupClickListener {

	private static final String TAG = "OpenedMenuFragment";
	
	private Animation hideMenuAnim;
	private Animation showMenuAnim;
	private IOnMenuEventListener listener;
	private ArrayList<MenuGroup> groups;
	private ArrayList<IEntity> pictosDangers, pictosCibles, pictosActions, pictosMoyens, pictosOffSitac;
	private MenuExpandableListAdapter menuAdapter;
	private ExpandableListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hideMenuAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_left);
		hideMenuAnim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				if (listener != null) listener.onHideMenu();
			}
		});
		showMenuAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_left);
		EntityHolder pHolder = EntityHolder.getInstance(getActivity());
		pictosDangers = pHolder.getEntities(Shape.TRIANGLE_UP);
		pictosCibles = pHolder.getEntities(Shape.TRIANGLE_DOWN);
		pictosActions = pHolder.getEntities(Color.BLACK);
		pictosMoyens = pHolder.getEntities(Shape.SQUARE);
		pictosOffSitac = new ArrayList<IEntity>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu_opened, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		((ImageButton) getActivity().findViewById(R.id.btn_hide_menu)).setOnClickListener(this);
		listView = (ExpandableListView) getActivity().findViewById(R.id.menu);
		groups = new ArrayList<MenuGroup>();
		
		// Groupe des vehicules en attente, TODO signalitique en cas de nouveau vehicule a placer
//		PictogramGroup waitingVehicles = new PictogramGroup("Véhicules en attente");
//		groups.add(waitingVehicles);
		
		// Groupe des dangers
		MenuGroup grpDangers = new MenuGroup(getString(R.string.dangers));
		grpDangers.setEntities(pictosDangers);
		groups.add(grpDangers);
		
		// Groupe des cibles
		MenuGroup grpCibles = new MenuGroup(getString(R.string.cibles));
		grpCibles.setEntities(pictosCibles);
		groups.add(grpCibles);
		
		// Groupe des moyens
		MenuGroup grpMoyens = new MenuGroup(getString(R.string.moyens));
		grpMoyens.setEntities(pictosMoyens);
		groups.add(grpMoyens);
		
		// Groupe des mapitems
		MenuGroup grpMapItems = new MenuGroup(getString(R.string.actions));
		grpMapItems.setEntities(pictosActions);
		groups.add(grpMapItems);
		
		menuAdapter = new MenuExpandableListAdapter(getActivity(), groups);
		listView.setAdapter(menuAdapter);
		listView.setOnChildClickListener(this);
		listView.setOnGroupClickListener(this);
		
		getView().startAnimation(showMenuAnim);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_hide_menu:
			getView().startAnimation(hideMenuAnim);
			break;
			
			default:
				android.util.Log.d("OpenedMenuFragment", "onClick: "+v.toString());
				break;
		}
	}
	
	public void startShowMenuAnim() {
		getView().startAnimation(showMenuAnim);
	}

	@Override
	public void setOnMenuEventListener(IOnMenuEventListener listener) {
		this.listener = listener;
	}

	@Override
	public void removeOnMenuEventListener() {
		this.listener = null;
	}

	@Override
	public boolean onChildClick(ExpandableListView p, View v, int grpIndex,
			int childIndex, long id) {

		//v.setSelected(true);
		menuAdapter.setChildSelected(childIndex, grpIndex);
		
		if (listener != null) {
			MenuGroup grp = groups.get(grpIndex);
			IEntity entity = grp.getEntities().get(childIndex);
			listener.onEntitySelected(entity, grp);
		}
		return true;
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {

//		if (menuAdapter.getNumGroupSelected() != -1) {
//			View childSelected = menuAdapter.getChildView(
//					menuAdapter.getNumGroupSelected(),
//					menuAdapter.getNumChildSelected(), false, null, null);
//			if (childSelected != null) {
//				menuAdapter.notifyDataSetChanged();
//			}
//		}
		return false;
	}
	
	
	public void addOffSitacEntities(ArrayList<IEntity> entities) {
		if (entities == null || entities.isEmpty()) {
			if (groups.get(0).getGroupName().equals(getString(R.string.off_sitac))) {
				android.util.Log.d(TAG, "REMOVE POSITION A DEFINIR");
				groups.remove(0);
			}
			
		} else {
			MenuGroup grpOffSitac = null;
			if (groups.get(0).getGroupName().equals(getString(R.string.off_sitac))) {
				android.util.Log.d(TAG, "POSITION A DEFINIR EXISTE DEJA AJOUT D'ITEM");
				grpOffSitac = groups.get(0);
			
			} else {
				android.util.Log.d(TAG, "POSITION A DEFINIR EXISTAIT PAS ON LE CREE");
				grpOffSitac = new MenuGroup(getString(R.string.off_sitac));
				groups.add(0, grpOffSitac);
			}
			
			pictosOffSitac.clear();
			pictosOffSitac.addAll(entities);			
			grpOffSitac.setEntities(pictosOffSitac);
		}
		
		menuAdapter.notifyDataSetChanged();
	}
	
	public void removeOnSitacEntity(IEntity entity) {
		MenuGroup grpOffSitac = null;
		if (groups.get(0).getGroupName().equals(getString(R.string.off_sitac))) {
			grpOffSitac = groups.get(0);
		} else {
			grpOffSitac = new MenuGroup(getString(R.string.off_sitac));
			groups.add(0, grpOffSitac);
		}	
		pictosOffSitac.remove(entity);			
		grpOffSitac.setEntities(pictosOffSitac);
		menuAdapter.notifyDataSetChanged();
	}
	
	public void unselectItem(View v) {
		v.setSelected(false);
	}

}
