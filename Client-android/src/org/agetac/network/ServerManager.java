package org.agetac.network;

import java.util.Collection;

import org.agetac.common.dto.Intervention;
import org.agetac.common.dto.Message;
import org.agetac.common.dto.Source;
import org.agetac.common.dto.Target;
import org.agetac.common.dto.VehicleDemand;
import org.agetac.common.dto.Victim;
import org.agetac.common.resources.InterventionResource;
import org.agetac.common.resources.InterventionsResource;
import org.agetac.common.resources.MessageResource;
import org.agetac.common.resources.MessagesResource;
import org.agetac.common.resources.SourceResource;
import org.agetac.common.resources.SourcesResource;
import org.agetac.common.resources.TargetResource;
import org.agetac.common.resources.TargetsResource;
import org.agetac.common.resources.VehicleDemandResource;
import org.agetac.common.resources.VehicleDemandsResource;
import org.agetac.common.resources.VictimResource;
import org.agetac.common.resources.VictimsResource;
import org.restlet.resource.ClientResource;

public class ServerManager {

	public ServerManager(String host, int port) {
		this.host = host;
		this.port = port;
	}

	private final String host;
	private final int port;

	public Collection<VehicleDemand> getVehicleDemands(long interId) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/vehicledemands");
		try {
			VehicleDemandsResource vehicleDemandsResource = clientResource
					.wrap(VehicleDemandsResource.class);

			return vehicleDemandsResource.retrieve();
		} finally {
			clientResource.release();
		}

	}

	public void addVehicleDemand(long interId, VehicleDemand vehicleDemand) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/vehicledemand");
		try {
			VehicleDemandResource vehicleDemandResource = clientResource
					.wrap(VehicleDemandResource.class);

			vehicleDemandResource.add(vehicleDemand);
		} finally {
			clientResource.release();
		}

	}

	public Collection<Intervention> getInterventions() {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/interventions");
		try {
			InterventionsResource interventionsResource = clientResource
					.wrap(InterventionsResource.class);

			return interventionsResource.retrieve();
		} finally {
			clientResource.release();
		}

	}

	public Intervention createIntervention() {
		Intervention intervention = new Intervention();

		// Add it.
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention");
		try {
			InterventionResource interventionResource = clientResource
					.wrap(InterventionResource.class);

			interventionResource.add(intervention);
			return intervention;
		} finally {
			clientResource.release();
		}
	}

	public void addMessage(long interId, Message message) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/message");
		try {
			MessageResource messageResource = clientResource
					.wrap(MessageResource.class);

			messageResource.add(message);
		} finally {
			clientResource.release();
		}

	}

	public void addSource(long interId, Source source) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/source");
		try {
			SourceResource sourceResource = clientResource
					.wrap(SourceResource.class);

			sourceResource.add(source);
		} finally {
			clientResource.release();
		}

	}

	public void addTarget(long interId, Target target) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/target");
		try {
			TargetResource targetResource = clientResource
					.wrap(TargetResource.class);

			targetResource.add(target);
		} finally {
			clientResource.release();
		}

	}

	public void addVictim(long interId, Victim victim) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/victim");
		try {
			VictimResource resource = clientResource.wrap(VictimResource.class);

			resource.add(victim);
		} finally {
			clientResource.release();
		}

	}

	public Collection<Message> getMessages(long interId) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/messages");
		try {
			MessagesResource resource = clientResource
					.wrap(MessagesResource.class);

			return resource.retrieve();
		} finally {
			clientResource.release();
		}
	}

	public Collection<Source> getSources(long interId) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/sources");
		try {
			SourcesResource resource = clientResource
					.wrap(SourcesResource.class);

			return resource.retrieve();
		} finally {
			clientResource.release();
		}
	}

	public Collection<Target> getTargets(long interId) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/targets");
		try {
			TargetsResource resource = clientResource
					.wrap(TargetsResource.class);

			return resource.retrieve();
		} finally {
			clientResource.release();
		}
	}

	public Collection<Victim> getVictims(long interId) {
		ClientResource clientResource = new ClientResource(getBaseAddress()
				+ "/intervention/" + interId + "/victims");
		try {
			VictimsResource resource = clientResource
					.wrap(VictimsResource.class);

			return resource.retrieve();
		} finally {
			clientResource.release();
		}
	}

	private String getBaseAddress() {
		return "http://" + host + ":" + port;
	}
}

