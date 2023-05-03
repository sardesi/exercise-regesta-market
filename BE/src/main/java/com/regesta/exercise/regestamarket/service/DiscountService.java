package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.DiscountDao;
import com.regesta.exercise.regestamarket.model.entity.Discount;

/**
 * The service layer of the Discount entity.
 * @author ars
 *
 */
public class DiscountService {

	private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
	
	@Autowired
	private DiscountDao dao;
	
	public void setDao(DiscountDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public Discount getById(Serializable id) {
		Discount entity = new Discount();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public Discount addOrUpdate(Discount entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		Discount entity = new Discount();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
}
