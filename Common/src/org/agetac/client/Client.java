package org.agetac.client;

import java.util.Collection;
import java.util.Date;

import org.agetac.common.dto.*;
import org.agetac.common.dto.VehicleDemand.DemandState;
import org.agetac.common.dto.Vehicle.VehiculeType;

public class Client {

	public static void main(String[] args) {

		AgetacClient c = new AgetacClient("localhost", 8888);

		// Create new intervention.
		Intervention intervention = c.createIntervention();
		long interId = intervention.getId();

		// How many do we have?
		Collection<Intervention> interventions = c.getInterventions();
		System.out.println("Interventions: " + interventions.size());

		// Add a vehicle demand.
		VehicleDemand vehicleDemand = new VehicleDemand("demand", DemandState.ASKED, VehiculeType.VSAV, new Position(), new Date());
		c.addVehicleDemand(interId, vehicleDemand);

		// Print the number of vehicle demands for this intervention.
		Collection<VehicleDemand> vehicleDemands = c.getVehicleDemands(interId);
		System.out.println("Vehicle demands: " + vehicleDemands.size());

		// Add messages to intervention.
		Message message = new Message();
		c.addMessage(interId, message);

		// List messages.
		Collection<Message> messages = c.getMessages(interId);
		System.out.println("Messages: " + messages.size());

		// Add sources to intervention.
		Source source = new Source();
		c.addSource(interId, source);

		// List sources.
		Collection<Source> sources = c.getSources(interId);
		System.out.println("Sources: " + sources.size());

		// Add targets to intervention.
		Target target = new Target();
		c.addTarget(interId, target);

		// List targets.
		Collection<Target> targets = c.getTargets(interId);
		System.out.println("Targets: " + targets.size());

		// Add victim to intervention.
		Victim victim = new Victim();
		c.addVictim(interId, victim);

		// List messages.
		Collection<Victim> victims = c.getVictims(interId);
		System.out.println("Victims: " + victims.size());
	}
}
