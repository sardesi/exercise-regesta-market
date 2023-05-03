package com.regesta.exercise.regestamarket.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.regesta.exercise.regestamarket.model.entity.AbstractEntity;

/**
 * This class is used as a basis for creating more specific daos, in order to already have some basic functionality every time you create a new entity.
 * It requires that the newly entity extends the AbstractEntity class.
 * 
 * @author ars
 *
 */

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AbstractDao<T extends AbstractEntity> {
	
	public static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
	private SessionFactory sessionFactory;
	private final Class<T> entityClass;
	
	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityName() {
		return this.entityClass.getName();
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	/**
	 * Return the current active transaction session. It expects a transaction to be already active, working in a transactional context the methods calling this function must be at some
	 * point marked as transactional.
	 * @return The current active session.
	 */
	public Session takeSession() {
		logger.debug("Taking current session");
		Session session = this.getSessionFactory().getCurrentSession();
		logger.debug("Session taken -> " + (session.isConnected() ? "CONNECTED" : "NOT CONNECTED") + "->" + (session.isOpen() ? "OPENED" : "CLOSED") + " (" + session.hashCode() + ")");
		return session;
	}
	
	/**
	 * Given an input entity return it's corrispective on the database. The entity key field must be popoulated for the method to work correctly.
	 * @param The entity the entity to get.
	 * @return The entity if found, otherwise null.
	 */
	public T get(T entity) {
		Session session = null;
		T entityDb = null;
		try {
			session = this.takeSession();
			entityDb = session.get(this.entityClass, entity.getKey());
			logger.debug(String.format("%s loaded successfully, %s Details: %s", this.getEntityName(), this.getEntityName(), entityDb));
			return entityDb;
		} catch (Exception e) {
			logger.error("Error on get entity", e);
		}
		return entityDb;
	}
	
	/**
	 * Adds or updates an entity based on it's presence on the database.
	 * @param entity The entity to be added or updated.
	 * @return Returns the entity linked to the database.
	 */
	@SuppressWarnings("unchecked")
	public T addOrUpdate(T entity) {
		Session session = null;
		session = this.takeSession();
		T entityDb = (T) session.merge(entity);
		logger.debug(String.format("%s updated successfully, %s Details: %s", this.getEntityName(), this.getEntityName(), entityDb));
		return entityDb;
	}
	
	/**
	 * Removes the input entity from the database. The entity key field must be popoulated for the method to work correctly.
	 * @param entity The entity to be removed.
	 */
	public void remove(T entity) {
		Session session = null;
		session = this.takeSession();
		session.remove(entity);
		logger.info(String.format("%s deleted successfully, %s Details: %s", this.getEntityName(), this.getEntityName(), entity));
	}

}
