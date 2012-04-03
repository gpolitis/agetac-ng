package org.agetac.activity;

import org.agetac.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabWidget;

public class TabsActivity extends TabActivity {
	
	private static final String TAB_SITAC = "tab_sitac";
	private static final String TAB_SOEIC = "tab_soeic";
	private static final String TAB_MOYENS = "tab_moyens";
	private static final String TAB_MESSAGES = "tab_messages";
	private static final String TAB_CRM = "tab_crm";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		TabHost tHost = getTabHost();
		TabHost.TabSpec spec;
		Resources res = getResources();
		Intent tabIntent;
		
	    // Initialize a TabSpec for each tab and add it to the TabHost
		tabIntent = new Intent().setClass(this, SITACActivity.class);
	    spec = tHost.newTabSpec(TAB_SITAC);
	    spec.setIndicator(getString(R.string.sitac), res.getDrawable(R.drawable.ic_tab_sitac));
	    spec.setContent(tabIntent);
	    tHost.addTab(spec);
	    
//	    tabIntent = new Intent().setClass(this, SOEICActivity.class);
//	    spec = tHost.newTabSpec(TAB_SOEIC);
//	    spec.setIndicator(getString(R.string.soeic), res.getDrawable(R.drawable.ic_tab_soeic));
//        spec.setContent(tabIntent);
//	    tHost.addTab(spec);
	    
	    tabIntent = new Intent().setClass(this, MoyensActivity.class);
	    spec = tHost.newTabSpec(TAB_MOYENS);
	    spec.setIndicator(getString(R.string.moyens), res.getDrawable(R.drawable.ic_tab_moyens));
        spec.setContent(tabIntent);
	    tHost.addTab(spec);
	    
	    tabIntent = new Intent().setClass(this, MessagesActivity.class);
	    spec = tHost.newTabSpec(TAB_MESSAGES);
	    spec.setIndicator(getString(R.string.messages), res.getDrawable(R.drawable.ic_tab_messages));
        spec.setContent(tabIntent);
	    tHost.addTab(spec);
	    
//	    tabIntent = new Intent().setClass(this, CRMActivity.class);
//	    spec = tHost.newTabSpec(TAB_CRM);
//	    spec.setIndicator(getString(R.string.crm), res.getDrawable(R.drawable.ic_tab_crm));
//        spec.setContent(tabIntent);
//	    tHost.addTab(spec);
	    
	    // on fait en sorte que les onglets prennent tout l'espace dispo en largeur
		TabWidget tWidget = tHost.getTabWidget();
		int nbTabs = tWidget.getChildCount();
		int tabMaxWidth = getWindowManager().getDefaultDisplay().getWidth()/nbTabs;
		for (int i=0; i<nbTabs; i++) {
			tWidget.getChildAt(i).getLayoutParams().width = tabMaxWidth;
		}
	    
	    tHost.setCurrentTab(0);
	}
}
