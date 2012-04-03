package org.agetac.engine;

import org.agetac.common.client.AgetacClient;
import org.agetac.common.dto.InterventionDTO;

public class UpdateInterventionThread extends Thread {
	
	private static final String TAG = "UpdateInterventionThread";

	private AgetacClient client;
	private InterventionEngine engine;
	private boolean doRun = true;
	
	public UpdateInterventionThread(InterventionEngine engine, AgetacClient client) {
		this.engine = engine;
		this.client = client;
	}
	
	@Override
	public void run() {	
		while (doRun) {

			InterventionDTO intervention = client.getIntervention(engine
					.getInterventionId());
			engine.updateIntervention(intervention);

			// TODO permettre de modifier l'intervalle de MAJ dans les prefs
			try {
				sleep(5000);
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
