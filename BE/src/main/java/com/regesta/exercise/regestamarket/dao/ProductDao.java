package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.Product;

/**
 * The dao/repository layer of the Product entity.
 * @author ars
 *
 */
public class ProductDao extends AbstractDao<Product> implements EntityDao<Product> {

	public ProductDao(Class<Product> entityClass) {
		super(entityClass);
	}

}