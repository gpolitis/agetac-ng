package org.agetac.server.db;

import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class SimpleDAO {

	private static PersistenceManagerFactory pmfInstance;

	private static PersistenceManagerFactory getPMF() {

		if (pmfInstance == null)
			pmfInstance = JDOHelper
					.getPersistenceManagerFactory("jdo.properties");

		return pmfInstance;
	}

	private static SimpleDAO daoInstance;

	public static SimpleDAO getInstance() {
		if (daoInstance == null)
			// TODO Use factories?
			daoInstance = new SimpleDAO();

		return daoInstance;
	}

	public static PersistenceManager getPM() {
		return getPMF().getPersistenceManager();
	}

	public synchronized <T> void add(T objTransient) {
		PersistenceManager pm = getPM();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			pm.makePersistent(objTransient);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public synchronized <T> T getOne(Class<T> cls, String uid) {
		PersistenceManager pm = getPM();

		try {
			Object idInstance = pm.newObjectIdInstance(cls, uid);
			@SuppressWarnings("unchecked")
			T obj = (T) pm.getObjectById(idInstance);
			return obj;
		} catch (Exception ex) {
			return null;
		}

	}

	public synchronized <T> void delete(Class<T> cls, String uniqueID) {
		PersistenceManager pm = SimpleDAO.getPM();

		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();

			Object idInstance = pm.newObjectIdInstance(cls, uniqueID);
			@SuppressWarnings("unchecked")
			T obj = (T) pm.getObjectById(idInstance);
			if (obj == null)
				return;

			pm.deletePersistent(obj);

			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}

	public synchronized <T> Collection<T> getMany(Class<T> cls) {
		PersistenceManager pm = SimpleDAO.getPM();
		Query query = pm.newQuery(cls);

		@SuppressWarnings("unchecked")
		Collection<T> results = (Collection<T>) query.execute();
		return results;
	}

	public <T> List<T> getMany(Class<T> cls, String q) {
		PersistenceManager pm = SimpleDAO.getPM();
		Query query = pm.newQuery(cls, q);

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) query.execute();
		return results;
	}

}