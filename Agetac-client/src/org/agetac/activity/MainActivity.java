package org.agetac.activity;

import org.agetac.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	private ProgressDialog connectionDialog;
	private InputMethodManager imm;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ((Button) findViewById(R.id.connectBtn)).setOnClickListener(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        
		final EditText passField = (EditText) findViewById(R.id.passField);
		passField.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// If the event is a key-down event on the "enter" button
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
						&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
					imm.hideSoftInputFromWindow(passField.getWindowToken(), 0);
					startTabHostActivity();
					return true;
				}
				return false;
			}
		});
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
//        imm.hideSoftInputFromWindow(((EditText) findViewById(R.id.loginField)).getWindowToken(), 0);
    }
    
	@Override
	protected void onPause() {
		super.onPause();
		// fuite si on ne détruit pas manuellement le dialog
		// quand l'activitée est mise en pause
		if (connectionDialog != null) connectionDialog.dismiss();
	}

	@Override
	public void onClick(View v) {
		String title = getString(R.string.progress_title_connection);
		String message = getString(R.string.progress_connection);
		connectionDialog = new ProgressDialog(MainActivity.this);
		connectionDialog.setTitle(title);
		connectionDialog.setMessage(message);
		connectionDialog.setIndeterminate(true);
		connectionDialog.show();
		
		startTabHostActivity();
	}
	
	private void startTabHostActivity() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO
				// demander une connexion au serveur
				
				// si la connexion est valide, lancer l'activitée
				startActivity(new Intent(MainActivity.this, TabsActivity.class));
				
				// supprimer le progress dialog 
				if (connectionDialog != null) connectionDialog.dismiss();
			}
		}).start();
	}
}