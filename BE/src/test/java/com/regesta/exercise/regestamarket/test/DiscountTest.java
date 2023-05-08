package com.regesta.exercise.regestamarket.test;

import java.math.BigDecimal;

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
import com.regesta.exercise.regestamarket.service.LoginService;
import com.regesta.exercise.regestamarket.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class DiscountTest {
	
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
	void getDiscountsByDateOk() throws Exception {
		
		String date = "2023-05-08";
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/discount/listByDate")
    		.param("date", date)
    		.header("api_key", token)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(8))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].supplierId").value(1))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].percentage").value("5.0"))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceFrom").value("799.99"));
	    
	    date = "2023-12-24";
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/discount/listByDate")
    		.param("date", date)
    		.header("api_key", token)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(11))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].supplierId").value(1))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].percentage").value("5.0"))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].priceFrom").value("799.99"));
	    
	}
	
	@Test
	void getDiscountsByDateEmpty() throws Exception {
		
		String date = "2000-01-01";
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/discount/listByDate")
    		.param("date", date)
    		.header("api_key", token)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
	    
	}

}
