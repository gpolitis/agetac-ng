package org.agetac.view;

import java.util.ArrayList;

import org.agetac.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuExpandableListAdapter extends BaseExpandableListAdapter {
	
	private ArrayList<MenuGroup> groups;
	//private ArrayList<PictogramGroup> groups;
	private LayoutInflater inflater;
	private int numChildSelected = -1;
	private int numGroupSelected = -1;
	
	public MenuExpandableListAdapter(Context context, ArrayList<MenuGroup> groups) {
		 this.groups = groups;
		 inflater = LayoutInflater.from(context);
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return groups.get(groupPosition).getEntities().get(childPosition).getPictogram();
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// Pictogramme correspondant à l'item récupéré par la méthode
		final IPictogram picto = (IPictogram) getChild(groupPosition, childPosition);
		
		ChildViewHolder childView;
		if(convertView == null) {
			childView = new ChildViewHolder();
			
			convertView = inflater.inflate(R.layout.menu_sitac_childs, null);
			childView.imgChild = (ImageView) convertView.findViewById(R.id.menu_child_img);
			childView.textViewChild = (TextView) convertView.findViewById(R.id.menu_child_text);
//			if (childPosition == numChildSelected && groupPosition == numGroupSelected) {
//				convertView.setBackgroundColor(Color.WHITE);
//				childView.textViewChild.setTextColor(Color.BLACK);
//			}
			
			convertView.setTag(childView);
		}
		else {
			childView = (ChildViewHolder) convertView.getTag();
		}
		childView.imgChild.setImageBitmap(picto.getBitmap());
		childView.textViewChild.setText(picto.getName());
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return groups.get(groupPosition).getEntities().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		GroupViewHolder groupView;
		MenuGroup group = (MenuGroup) getGroup(groupPosition);
		
		if(convertView == null) {
			groupView = new GroupViewHolder();
			convertView = inflater.inflate(R.layout.menu_sitac_groups, null);
			
			groupView.textViewGroup = (TextView) convertView.findViewById(R.id.sitac_group);
			convertView.setTag(groupView);
		}
		else {
			groupView = (GroupViewHolder) convertView.getTag();
		}
		groupView.textViewGroup.setText(group.getGroupName());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	
	public int getNumChildSelected() {
		return numChildSelected;
	}
	
	public int getNumGroupSelected() {
		return numGroupSelected;
	}
	
	public void setChildSelected(int numChild, int numGroup) {
		numChildSelected = numChild;
		numGroupSelected = numGroup;
	}
	

	/*
	 * Representation d'un groupe dans le menu (le nom du groupe)
	 */
    class GroupViewHolder {
        public TextView textViewGroup;
    }
    
    /*
     * Representation d'un item dans le menu (le pictogramme et son nom)
     */
    class ChildViewHolder {
    	public ImageView imgChild;
        public TextView textViewChild;
    }
}
