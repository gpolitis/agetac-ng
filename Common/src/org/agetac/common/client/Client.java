package org.agetac.common.client;

import java.util.Collection;
import java.util.Date;

import org.agetac.common.dto.InterventionDTO;
import org.agetac.common.dto.MessageDTO;
import org.agetac.common.dto.PositionDTO;
import org.agetac.common.dto.SourceDTO;
import org.agetac.common.dto.TargetDTO;
import org.agetac.common.dto.VehicleDTO.VehicleType;
import org.agetac.common.dto.VehicleDemandDTO;
import org.agetac.common.dto.VehicleDemandDTO.DemandState;
import org.agetac.common.dto.VictimDTO;

public class Client {

	public static void main(String[] args) {

		AgetacClient c = new AgetacClient("localhost", 8888);
		
		// Create new intervention.
		InterventionDTO intervention = c.createIntervention();
		long interId = intervention.getId();

		// How many do we have?
		Collection<InterventionDTO> interventions = c.getInterventions();
		System.out.println("Interventions: " + interventions.size());

		playWithVehicleDemands(c, interId);

		playWithMessages(c, interId);

		playWithSources(c, interId);

		playWithTargets(c, interId);

		playWithVictims(c, interId);
		
		testVehicleDemandListSizes(c, interId);

		intervention = c.getIntervention(intervention.getId());
		System.out.println("Victims: " + intervention.getVictims().size());
	}

	private static void playWithVictims(AgetacClient c, long interId) {
		// Add victim to intervention.
		VictimDTO victim = c.addVictim(interId, new VictimDTO());

		// Add second victim.
		victim = c.addVictim(interId, new VictimDTO());

		// List victims.
		Collection<VictimDTO> victims = c.getVictims(interId);
		System.out.println("Victims: " + victims.size());

		// Update the victim we just added.
		victim.setName("George");
		c.updateVictim(victim);

		// Delete the victim.
		c.deleteVictim(victim.getId());

		// List victims.
		victims = c.getVictims(interId);
		System.out.println("Victims: " + victims.size());
	}

	private static void playWithTargets(AgetacClient c, long interId) {
		// Add targets to intervention.
		TargetDTO target = new TargetDTO();
		c.addTarget(interId, target);

		// List targets.
		Collection<TargetDTO> targets = c.getTargets(interId);
		System.out.println("Targets: " + targets.size());

		// Update the target we just added.
		target.setName("Building A");
		c.updateTarget(target);

		// Delete the target.
		c.deleteTarget(target.getId());

		// List targets.
		targets = c.getTargets(interId);
		System.out.println("Targets: " + targets.size());
	}

	private static void playWithSources(AgetacClient c, long interId) {
		// Add sources to intervention.
		SourceDTO source = new SourceDTO();
		c.addSource(interId, source);

		// List sources.
		Collection<SourceDTO> sources = c.getSources(interId);
		System.out.println("Sources: " + sources.size());

		// Update the source we just added.
		source.setPosition(new PositionDTO(0, 0));
		c.updateSource(source);

		// Delete the source.
		c.deleteSource(source.getId());

		// List sources.
		sources = c.getSources(interId);
		System.out.println("Sources: " + sources.size());
	}

	private static void playWithMessages(AgetacClient c, long interId) {
		// Add messages to intervention.
		MessageDTO message = new MessageDTO();
		message.setText("Hello, world!");
		c.addMessage(interId, message);

		// List messages.
		Collection<MessageDTO> messages = c.getMessages(interId);
		System.out.println("Messages: " + messages.size());

		// NOTE I would say that a message is an immutable object. Once sent to
		// the server, it cannot be changed, nor deleted.
		// Max: And I totally agree
	}

	private static void playWithVehicleDemands(AgetacClient c, long interId) {
		// Add a vehicle demand.
		VehicleDemandDTO vehicleDemand = new VehicleDemandDTO(
				DemandState.ASKED, VehicleType.VSAV, new PositionDTO(),
				new Date());
		c.addVehicleDemand(interId, vehicleDemand);

		// Print the number of vehicle demands for this intervention.
		Collection<VehicleDemandDTO> vehicleDemands = c
				.getVehicleDemands(interId);
		System.out.println("Vehicle demands: " + vehicleDemands.size());
	}
	
	private static void testVehicleDemandListSizes(AgetacClient c, long interId) {
		InterventionDTO i = c.getIntervention(interId);
		
		VehicleDemandDTO vd = new VehicleDemandDTO();
		c.addVehicleDemand(interId, vd);
		
		Collection<VehicleDemandDTO> vListFromInter = i.getDemands();
		Collection<VehicleDemandDTO> vListFromClient = c.getVehicleDemands(interId);
		
		assert (vListFromInter.size() != vListFromClient.size());
	}
}
