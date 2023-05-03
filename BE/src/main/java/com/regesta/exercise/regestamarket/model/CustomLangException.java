package com.regesta.exercise.regestamarket.model;

import com.regesta.exercise.regestamarket.constant.LangException;

/**
 * A custom exception that accepts only a LangException enum for it's creation. Based on this enum code and the selected language the FE will then show the appropriate message.
 * @author ars
 *
 */
public class CustomLangException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String code;
	
	public CustomLangException(LangException langException) {
		super(langException.getMessage());
		this.code = langException.toString();
	}

	public String getCode() {
		return code;
	}
	
}
