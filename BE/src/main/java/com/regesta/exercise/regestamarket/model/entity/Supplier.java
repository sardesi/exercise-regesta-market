package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents the single supplier from wich a product can be ordered.
 * @author ars
 *
 */
@Entity
@Table(name = "SUPPLIER")
@Getter
@Setter
public class Supplier extends AbstractEntity {
	
	private static final long serialVersionUID = -4638361481640067599L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "SHIPPING_DAYS", nullable = false)
	private Integer shippingDays;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
