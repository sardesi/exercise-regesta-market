package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate dateFrom;
	private LocalDate dateTo;
	private Integer minPieceDiscount;
	private BigDecimal percentage;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;
	private Integer supplierId;
	
	public DiscountInfo() { }

	public DiscountInfo(LocalDate dateFrom, LocalDate dateTo, Integer minPieceDiscount, BigDecimal percentage, BigDecimal priceFrom, BigDecimal priceTo, Integer supplierId) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.minPieceDiscount = minPieceDiscount;
		this.percentage = percentage;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.supplierId = supplierId;
	}
	
	
	
}
