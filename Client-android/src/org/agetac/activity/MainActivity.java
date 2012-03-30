package org.agetac.activity;

import org.agetac.network.ServerManager;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ServerManager s = new ServerManager("192.168.0.1", 8888);
		android.util.Log.d("WELLYEAH", ">>>>>>>>>>>><<<<<<<<<<<<<<<<<<<");

		System.out.println(s.getInterventions().toString());
	}
}
