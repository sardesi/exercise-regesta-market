package com.regesta.exercise.regestamarket.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.regesta.exercise.regestamarket.model.entity.User;

/**
 * The dao/repository layer of the User entity.
 * @author ars
 *
 */
public class UserDao extends AbstractDao<User> implements EntityDao<User> {

	public UserDao(Class<User> entityClass) {
		super(entityClass);
	}
	
	public User getByMail(String mail) {
		
		Session session = null;
			
		session = takeSession();
		String queryString = "SELECT et FROM User et WHERE et.mail = :mail";
		
		Query<User> query = session.createQuery(queryString, User.class);
		query.setParameter("mail", mail);
		
		return query.uniqueResult();
		
	}

}