package com.regesta.exercise.regestamarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.regesta.exercise.regestamarket.model.CustomLangException;
import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.security.JWTToken;

public class BaseRestController {

	private static final Logger logger = LoggerFactory.getLogger(BaseRestController.class);
	
	protected MarketUser getUser() {
		JWTToken jwtToken = (JWTToken) SecurityContextHolder.getContext().getAuthentication();
		if(jwtToken != null) {
			String jsonContext = (String) jwtToken.getClaims().getClaim("user");
			return MarketUser.fromJson(jsonContext);
		}
		return null;
	}

	/**
	 * Manages the response in case of the throw of a CustomLangException.
	 * @param ex The lang exception.
	 * @return
	 */
	@ExceptionHandler({ CustomLangException.class })
	public ResponseEntity<CustomLangException> basicExceptionHandler(CustomLangException ex) {
		return new ResponseEntity<CustomLangException>(ex, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
