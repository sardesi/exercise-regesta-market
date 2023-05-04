package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import com.regesta.exercise.regestamarket.constant.LangException;
import com.regesta.exercise.regestamarket.constant.Language;
import com.regesta.exercise.regestamarket.constant.ProductsListRequestOrder;
import com.regesta.exercise.regestamarket.model.CustomLangException;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO used for the API of the paginated list of translated products.
 * It contains the basic needed fields and a check method to validate the incoming data.
 * @author ars
 *
 */
@Getter
@Setter
public class ProductsListRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Pagination pagination;
	
	private String code;
	private String name;
	
	/**
	 * Validates the data of the object, checking the presence and the presence and the correctness of the language and the order.
	 * The order is checked here on the BE side to prevent the possibility of possible FE or malicious attempts at a SQL Injection.
	 * @param language The language used for the request.
	 * @throws CustomLangException
	 */
	public void check(String language) throws CustomLangException {
		if(StringUtils.isEmpty(language)) throw new CustomLangException(LangException.MISSING_LANGUAGE);
		if(!EnumUtils.isValidEnum(Language.class, language)) throw new CustomLangException(LangException.UNKNOWN_LANGUAGE);
		if(pagination != null && !StringUtils.isEmpty(pagination.getOrder())) {
			if(!EnumUtils.isValidEnum(ProductsListRequestOrder.class, pagination.getOrder())) throw new CustomLangException(LangException.UNKNOWN_ORDER);
			pagination.setOrder(ProductsListRequestOrder.valueOf(pagination.getOrder()).getMessage());
		}
	}
	
}
