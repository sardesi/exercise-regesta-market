package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.ProductDictionaryDao;
import com.regesta.exercise.regestamarket.model.entity.ProductDictionary;

/**
 * The service layer of the ProductDictionary entity.
 * @author ars
 *
 */
public class ProductDictionaryService {

	private static final Logger logger = LoggerFactory.getLogger(ProductDictionaryService.class);
	
	@Autowired
	private ProductDictionaryDao dao;
	
	public void setDao(ProductDictionaryDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public ProductDictionary getById(Serializable id) {
		ProductDictionary entity = new ProductDictionary();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public ProductDictionary addOrUpdate(ProductDictionary entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		ProductDictionary entity = new ProductDictionary();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
}
