package org.agetac.server.db;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.agetac.common.dto.*;


public class InterventionDAO {

	public void add(long interId, VehicleDemand vehicleDemand) {
		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			inter.getVehicleDemands().add(vehicleDemand);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void add(long interId, Message message) {
		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			inter.getMessages().add(message);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void add(long interId, Source source) {
		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			inter.getSources().add(source);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public void add(long interId, Target target) {
		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			inter.getTargets().add(target);
			pm.makePersistent(inter);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

	}

	public Collection<VehicleDemand> retrieveVehicleDemands(long interId) {
		PersistenceManager pm = SimpleDAO.getPM();
		try {

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			return inter.getVehicleDemands();
		} finally {

			pm.close();
		}

	}

	public Collection<Target> retrieveTargets(long interId) {
		PersistenceManager pm = SimpleDAO.getPM();
		try {

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			return inter.getTargets();
		} finally {

			pm.close();
		}
	}

	public Collection<Message> retrieveMessages(long interId) {
		PersistenceManager pm = SimpleDAO.getPM();
		try {

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			return inter.getMessages();
		} finally {

			pm.close();
		}
	}

	public Collection<Source> retrieveSources(long interId) {
		PersistenceManager pm = SimpleDAO.getPM();
		try {

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			return inter.getSources();
		} finally {

			pm.close();
		}
	}

	public void add(long interId, Victim victim) {
		PersistenceManager pm = SimpleDAO.getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			inter.getVictims().add(victim);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public Collection<Victim> retrieveVictims(long interId) {
		PersistenceManager pm = SimpleDAO.getPM();
		try {

			Object interKey = pm.newObjectIdInstance(Intervention.class,
					interId);

			Intervention inter = (Intervention) pm.getObjectById(interKey);
			Collection<Victim> victims = inter.getVictims();
			return victims;
		} finally {

			pm.close();
		}
	}
}
