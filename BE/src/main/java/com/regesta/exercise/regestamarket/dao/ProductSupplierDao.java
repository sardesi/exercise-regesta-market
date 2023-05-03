package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.ProductSupplier;

/**
 * The dao/repository layer of the ProductSupplier entity.
 * @author ars
 *
 */
public class ProductSupplierDao extends AbstractDao<ProductSupplier> implements EntityDao<ProductSupplier> {

	public ProductSupplierDao(Class<ProductSupplier> entityClass) {
		super(entityClass);
	}

}