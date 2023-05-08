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
import com.regesta.exercise.regestamarket.model.dto.Pagination;
import com.regesta.exercise.regestamarket.model.dto.ProductsListRequest;
import com.regesta.exercise.regestamarket.service.LoginService;
import com.regesta.exercise.regestamarket.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ProductTest {
	
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
	void getProductsFullListOk() throws Exception {
		
		Pagination pagination = new Pagination();
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.param("language", "IT")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results.size()").value(38));
	    
	}
	
	@Test
	void getProductsPaginatedOk() throws Exception {
		
		Pagination pagination = new Pagination();
		pagination.setLimit(10);
		pagination.setOffset(10);
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.param("language", "IT")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results.size()").value(10))
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].code").value("PR0M4791"));
	    
	}
	
	@Test
	void getProductsOrderedOk() throws Exception {
		
		Pagination pagination = new Pagination();
		pagination.setOrder("NAME");
		pagination.setDesc(false);
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.param("language", "IT")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results.size()").value(38))
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].name").value("Acer 4K Ultrasottile 14\""))
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results[37].name").value("Tastiera HP KB7S"));
	    
	}
	
	@Test
	void getProductsFilteredOk() throws Exception {
		
		Pagination pagination = new Pagination();
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		request.setCode("4781");
		request.setName("Acer");
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.param("language", "IT")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results.size()").value(1))
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].code").value("PR0M4781"))
	    .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].name").value("Acer 4K Ultrasottile 14\""));
	    
	}
	
	@Test
	void getProductsLanguageKo() throws Exception {
		
		Pagination pagination = new Pagination();
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.header("api_key", token)
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.header("api_key", token)
    		.param("language", "WRONG")
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	    
	}
	
	@Test
	void getProductsOrderKo() throws Exception {
		
		Pagination pagination = new Pagination();
		pagination.setOrder(token);
		
		ProductsListRequest request = new ProductsListRequest();
		request.setPagination(pagination);
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.post("/api/product/listTranslatedProducts")
    		.header("api_key", token)
    		.param("language", "IT")
    		.content(objectMapper.writeValueAsString(request))
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	    
	}
	
	@Test
	void listPricedProductsOk() throws Exception {
		
		Integer id = 1;
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/product/listPricedProducts/" + id)
    		.header("api_key", token)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(5))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(150.40))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].shippingDays").value(2))
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].supplierName").value("Tech Store BS"));
	    
	}
	
	@Test
	void listPricedProductsEmpty() throws Exception {
		
		Integer id = 999;
		
	    mockMvc.perform(
    		MockMvcRequestBuilders.get("/api/product/listPricedProducts/" + id)
    		.header("api_key", token)
    		.contentType(MediaType.APPLICATION_JSON)
    		.accept(MediaType.APPLICATION_JSON)
    	)
	    .andDo(MockMvcResultHandlers.print())
	    .andExpect(MockMvcResultMatchers.status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
	    
	}

}
