package org.agetac.engine;


public class UpdateInterventionThread extends Thread {
	
	private static final String TAG = "UpdateInterventionThread";

	private InterventionEngine engine;
	private boolean doRun = true;
	
	public UpdateInterventionThread(InterventionEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public void run() {	
		while (doRun) {
			// TODO permettre de modifier l'intervalle de MAJ dans les prefs
			try {
				sleep(5000);
				engine.updateIntervention();
				
			} catch (InterruptedException e) {
				android.util.Log.d(TAG, e.getMessage());
			}
		}
		android.util.Log.d(TAG, "UpdateThread stopped");
	}
	
	public void doStop() {
		doRun = false;
	}
}
