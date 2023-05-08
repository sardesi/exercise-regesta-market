package com.regesta.exercise.regestamarket.model.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO of the User entity. It's also used to save informations in the JWT token claims to 
 * @author ars
 *
 */
@Getter
@Setter
public class MarketUser implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(MarketUser.class);

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String mail;
	private String password;
	private String name;
	private String surname;
	private String language;
	
	public static MarketUser fromJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, MarketUser.class);
		} catch (Exception var3) {
			logger.error("Error deserializing user", var3);
			return null;
		}
	}
	
	public String toJson() {		
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		}
		catch(Exception ex) {
			logger.error("Error serializing user", ex);
		}
		return ""; 
	}
	
}
