package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.ProductSupplierDao;
import com.regesta.exercise.regestamarket.model.entity.ProductSupplier;
import com.regesta.exercise.regestamarket.model.entity.ProductSupplierPK;

/**
 * The service layer of the ProductSupplier entity.
 * @author ars
 *
 */
public class ProductSupplierService {

	private static final Logger logger = LoggerFactory.getLogger(ProductSupplierService.class);
	
	@Autowired
	private ProductSupplierDao dao;
	
	public void setDao(ProductSupplierDao dao) {
		this.dao = dao;
	}
		
/* Base methods */

	@Transactional(readOnly=true)
	public ProductSupplier getById(Serializable id) {
		ProductSupplier entity = new ProductSupplier();
		entity.setId((ProductSupplierPK)id);
		return dao.get(entity);
	}

	@Transactional
	public ProductSupplier addOrUpdate(ProductSupplier entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		ProductSupplier entity = new ProductSupplier();
		entity.setId((ProductSupplierPK)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
}
