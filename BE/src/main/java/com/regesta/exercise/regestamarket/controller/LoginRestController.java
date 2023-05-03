package com.regesta.exercise.regestamarket.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.service.LoginService;

import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@Api(value = "Login")
@RequestMapping("login")
public class LoginRestController extends BaseRestController {
	
	@Autowired(required=true)
	private LoginService loginService;
	
	/**
	 * Executes the login to the application, returning the user token.
	 * @param login The data of the user.
	 * @param rememberMe If true a token with a ten year lifespan will be generated. If false the lifespan will be of only 8 hours.
	 * @param request The http request.
	 * @param response The http response.
	 * @return
	 */
	@PostMapping(value = "doLogin", produces="application/json", consumes="application/json")
	public Map<String, Object> doLogin(@RequestBody MarketUser login, @RequestParam(required = false, defaultValue = "false") boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
		return loginService.login(login, rememberMe, response);
	}
	
}