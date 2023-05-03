package com.regesta.exercise.regestamarket.dao;

import com.regesta.exercise.regestamarket.model.entity.AbstractEntity;

public interface EntityDao<T extends AbstractEntity> {

	public T get(T entity);
	public T addOrUpdate(T entity);
	public void remove(T entity);
	
}
