package com.regesta.exercise.regestamarket.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * This entity represents the single user that can access the platform. 
 * The table has been called MARKET_USER to avoids possible errors as USER can be a configuration table in some systems.
 * @author ars
 *
 */
@Entity
@Table(name = "MARKET_USER")
@Getter
@Setter
public class User extends AbstractEntity {
	
	private static final long serialVersionUID = -4638361481640067599L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "MAIL", unique = true, nullable = false)
	private String mail;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "SURNAME", nullable = false)
	private String surname;

	@Column(name = "LANGUAGE", nullable = false)
	private String language;
	
	@Override
	public Serializable getKey() {
		return id;
	}

}
