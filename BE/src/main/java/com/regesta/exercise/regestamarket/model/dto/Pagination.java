package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean desc;
	private String language;
	private Integer limit;
	private Integer offset;
	private String order;
	private Integer pageIndex;
	private Integer totalRecords;
	
}
