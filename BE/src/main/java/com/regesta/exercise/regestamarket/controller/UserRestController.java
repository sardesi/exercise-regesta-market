package com.regesta.exercise.regestamarket.controller;

import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.regesta.exercise.regestamarket.model.dto.MarketUser;

import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@Api(value = "User")
@RequestMapping("api/user")
public class UserRestController extends BaseRestController {
	
	/**
	 * Returns the current user detail based on the authentication token.
	 * @param locale
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@GetMapping(value = "context", produces="application/json")
	public MarketUser checkContext(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		return getUser();
	}
	
}
