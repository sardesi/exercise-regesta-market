package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.ProductDao;
import com.regesta.exercise.regestamarket.model.dto.ListResponse;
import com.regesta.exercise.regestamarket.model.dto.ProductsListRequest;
import com.regesta.exercise.regestamarket.model.dto.TranslatedProduct;
import com.regesta.exercise.regestamarket.model.entity.Product;

/**
 * The service layer of the Product entity.
 * @author ars
 *
 */
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDao dao;
	
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public Product getById(Serializable id) {
		Product entity = new Product();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public Product addOrUpdate(Product entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		Product entity = new Product();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
	@Transactional
	public ListResponse<TranslatedProduct> listProducts(ProductsListRequest request) {
		
		logger.debug("ProductService | listProducts | START ");
		
		ListResponse<TranslatedProduct> results = new ListResponse<>();
		
		// TODO: Query per recuperare lista di prodotti e relative traduzioni
		
		return results;
		
	}
}
