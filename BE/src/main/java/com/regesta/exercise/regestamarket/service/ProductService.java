package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.ProductDao;
import com.regesta.exercise.regestamarket.model.dto.ListResponse;
import com.regesta.exercise.regestamarket.model.dto.ProductsListRequest;
import com.regesta.exercise.regestamarket.model.dto.TranslatedProduct;
import com.regesta.exercise.regestamarket.model.entity.Product;
import com.regesta.exercise.regestamarket.model.entity.ProductDictionary;

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
	
	/**
	 * Returns a paginated list of translated products. It can be ordered and filtered by name.
	 * It starts from the product and not directly from the product dictionary so that if a product doesn't have a predefined translation is still visible but without any name, only the code.
	 * @param request The body of the request, it contains the pagination info and the name filter.
	 * @param language The language of the translated product. It's mandatory.
	 * @return
	 */
	@Transactional
	public ListResponse<TranslatedProduct> listTranslatedProducts(ProductsListRequest request, String language) {
		
		logger.debug("ProductService | listProducts | START ");
		
		ListResponse<TranslatedProduct> results = new ListResponse<>();
		results.setResults(dao.listTranslatedProducts(request, language));
		
		return results;
		
	}
	
}
