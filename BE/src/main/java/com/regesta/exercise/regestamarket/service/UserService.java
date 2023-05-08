package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.UserDao;
import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.model.entity.User;

/**
 * The service layer of the User entity.
 * @author ars
 *
 */
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao dao;
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public User getById(Serializable id) {
		User entity = new User();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public User addOrUpdate(User entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		User entity = new User();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */

	@Transactional(readOnly = true)
	public User getByMail(String mail) {
		
		logger.debug("UserService | getByMail | START | mail: {}.", mail);
		
		return dao.getByMail(mail);
		
	}
	
}
