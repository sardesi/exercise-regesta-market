package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.SupplierDao;
import com.regesta.exercise.regestamarket.model.entity.Supplier;

/**
 * The service layer of the Supplier entity.
 * @author ars
 *
 */
public class SupplierService {

	private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);
	
	@Autowired
	private SupplierDao dao;
	
	public void setDao(SupplierDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public Supplier getById(Serializable id) {
		Supplier entity = new Supplier();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public Supplier addOrUpdate(Supplier entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		Supplier entity = new Supplier();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
}
