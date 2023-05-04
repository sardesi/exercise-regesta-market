package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricedProduct implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	private Integer supplierId;

	private Integer availability;
	private BigDecimal price;
	private Integer shippingDays;
	private String supplierName;
	
	public PricedProduct() { }
	
	public PricedProduct(Integer productId, Integer supplierId, Integer availability, BigDecimal price, String supplierName, Integer shippingDays) {
		this.productId = productId;
		this.supplierId = supplierId;
		this.availability = availability;
		this.price = price;
		this.supplierName = supplierName;
		this.shippingDays = shippingDays;
	}
	
}
