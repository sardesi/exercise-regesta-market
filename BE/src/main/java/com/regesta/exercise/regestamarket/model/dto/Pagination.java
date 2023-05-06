package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean desc;
	private Integer limit;
	private String fieldOrder;
	private String order;
	private Integer offset;
	private Long totalRecords;
	
}
