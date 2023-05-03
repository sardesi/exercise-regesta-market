package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.ProductDictionary;

/**
 * The dao/repository layer of the ProductDictionary entity.
 * @author ars
 *
 */
public class ProductDictionaryDao extends AbstractDao<ProductDictionary> implements EntityDao<ProductDictionary> {

	public ProductDictionaryDao(Class<ProductDictionary> entityClass) {
		super(entityClass);
	}
	
}