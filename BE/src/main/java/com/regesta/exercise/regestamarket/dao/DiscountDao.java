package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.Discount;

/**
 * The dao/repository layer of the Discount entity.
 * @author ars
 *
 */
public class DiscountDao extends AbstractDao<Discount> implements EntityDao<Discount> {

	public DiscountDao(Class<Discount> entityClass) {
		super(entityClass);
	}

}