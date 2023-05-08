package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResponse<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pagination pagination;
	private List<T> results;
	
}
