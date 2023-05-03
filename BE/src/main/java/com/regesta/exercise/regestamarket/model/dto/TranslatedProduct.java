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
	private String name;
	
}
