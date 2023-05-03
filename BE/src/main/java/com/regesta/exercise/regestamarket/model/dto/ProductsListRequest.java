package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsListRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pagination pagination;
	private String productName;
	
}
