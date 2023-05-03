package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

/**
 * Abstract class used a basis for 
 * 
 * @author ars
 *
 */

public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Serializable getKey();
	
}
