package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents the translations of the products informations in different languages.
 * @author ars
 *
 */
@Entity
@Table(name = "PRODUCT_DICTIONARY")
@Getter
@Setter
public class ProductDictionary extends AbstractEntity {
	
	private static final long serialVersionUID = -4638361481640067599L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product product;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@Column(name = "LANGUAGE", nullable = false)
	private String language;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
