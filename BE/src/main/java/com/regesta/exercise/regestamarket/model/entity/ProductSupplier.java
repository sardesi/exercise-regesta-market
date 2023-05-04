package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents the association between a product and a supplier.
 * @author ars
 *
 */
@Entity
@Table(name = "PRODUCT_SUPPLIER")
@Getter
@Setter
public class ProductSupplier extends AbstractEntity {
	
	private static final long serialVersionUID = -3232911202975689991L;

	@EmbeddedId
	private ProductSupplierPK id;
	
	@Column(name = "PRICE", nullable = false)
	private BigDecimal price;
	
	@Column(name = "AVAILABILITY", nullable = false)
	private Integer availability;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
