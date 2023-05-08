package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranslatedProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String description;
	private Integer id;
	private String imageUrl;
	private String name;
	
	public TranslatedProduct() { }
	
	public TranslatedProduct(Integer id, String code, String name, String description, String imageUrl) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
}
