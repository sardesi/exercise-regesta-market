package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents a single discount that a customer might have meeting some predefined conditions.
 * It needs a time range, and a price range is optional.
 * @author ars
 *
 */
@Entity
@Table(name = "DISCOUNT")
@Getter
@Setter
public class Discount extends AbstractEntity {
	
	private static final long serialVersionUID = -4638361481640067599L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID", nullable = false)
	private Supplier supplier;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FROM", nullable = false)
	private LocalDate dateFrom;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_TO", nullable = false)
	private LocalDate dateTo;

	@Column(name = "PRICE_RANGE_FROM", nullable = true)
	private BigDecimal priceRangeFrom;

	@Column(name = "PRICE_RANGE_TO", nullable = true)
	private BigDecimal priceRangeTo;

	@Column(name = "MIN_PIECE_DISCOUNT", nullable = true)
	private Integer minPieceDiscount;

	@Column(name = "PERCENTAGE", nullable = true)
	private BigDecimal percentage;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
