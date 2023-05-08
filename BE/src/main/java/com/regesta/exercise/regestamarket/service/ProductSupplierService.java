package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.ProductSupplierDao;
import com.regesta.exercise.regestamarket.model.dto.PricedProduct;
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
	
	/**
	 * Returns a paginated list of translated products. It can be ordered and filtered by name.
	 * It starts from the product and not directly from the product dictionary so that if a product doesn't have a predefined translation is still visible but without any name, only the code.
	 * @param request The body of the request, it contains the pagination info and the name filter.
	 * @param language The language of the translated product. It's mandatory.
	 * @return
	 */
	@Transactional
	public List<PricedProduct> listPricedProducts(Integer productId) {
		
		logger.debug("ProductService | listPricedProducts | START  | productId: {}.", productId);
		
		return dao.listPricedProducts(productId);
		
	}
	
}
