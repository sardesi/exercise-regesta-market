package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents a single product inside the database.
 * It has only the product code as the various possible texts are managed by the dictionary. 
 * @author ars
 *
 */
@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product extends AbstractEntity {
	
	private static final long serialVersionUID = -4638361481640067599L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "CODE", unique = true, nullable = false)
	private String code;

// Defined as transient because it will be recovered by a specific service only when needed.
	@Transient
	private byte[] image;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
