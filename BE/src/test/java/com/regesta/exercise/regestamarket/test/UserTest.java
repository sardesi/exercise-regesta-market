package com.regesta.exercise.regestamarket.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regesta.exercise.regestamarket.model.dto.MarketUser;
import com.regesta.exercise.regestamarket.service.LoginService;
import com.regesta.exercise.regestamarket.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class UserTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LoginService loginService;
	
	private String token;
	
	@BeforeAll
	public void getToken() {
	    this.token = loginService.buildHexToken(TestUtils.getDefaultUser(), TestUtils.getDefaultMarketUser(), false);
	}
	
	@Test
	void loginOk() throws Exception {
		
	    MarketUser user = TestUtils.getDefaultMarketUser();

	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/login/doLogin")
    		.param("rememberMe", "true")
    		.content(objectMapper.writeValueAsString(user))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty());
	    
	}
	
	@Test
	void loginKo() throws Exception {
		
	    MarketUser user = new MarketUser();
	    user.setMail("simoneardesi@outlook.it");
	    user.setPassword("wrongPassword");

	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/login/doLogin")
    		.content(objectMapper.writeValueAsString(user))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isUnauthorized());
	    
	}
	
	@Test
	void contextOk() throws Exception {
		
	    MarketUser user = TestUtils.getDefaultMarketUser();

	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/user/context")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(user))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.mail").value("simoneardesi@outlook.it"));
	    
	}

}
