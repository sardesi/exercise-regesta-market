package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.Supplier;

/**
 * The dao/repository layer of the Supplier entity.
 * @author ars
 *
 */
public class SupplierDao extends AbstractDao<Supplier> implements EntityDao<Supplier> {

	public SupplierDao(Class<Supplier> entityClass) {
		super(entityClass);
	}

}