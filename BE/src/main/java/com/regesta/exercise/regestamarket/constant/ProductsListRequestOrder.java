package com.regesta.exercise.regestamarket.constant;

/**
 * The enum used to define the possible orders of a ProductListRequest.
 * @author ars
 *
 */
public enum ProductsListRequestOrder {

	CODE("p.code"),
	NAME("pd.name");
	
	private final String orderField;

	private ProductsListRequestOrder(String orderField) {
		this.orderField = orderField;
	}

	public String getMessage() {
		return orderField;
	}

}
