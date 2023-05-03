package com.regesta.exercise.regestamarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.regesta.exercise.regestamarket.model.dto.ListResponse;
import com.regesta.exercise.regestamarket.model.dto.ProductsListRequest;
import com.regesta.exercise.regestamarket.model.dto.TranslatedProduct;
import com.regesta.exercise.regestamarket.service.ProductService;
import com.regesta.exercise.regestamarket.service.ProductSupplierService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Product")
@RequestMapping("api/product")
public class ProductRestController extends BaseRestController {

	@Autowired(required=true)
	private ProductService productService;

	@Autowired(required=true)
	private ProductSupplierService productSupplierService;
	
	/**
	 * Returns a paginated list of translated products. It can be ordered and filtered by name.
	 * @param request
	 * @return
	 */
	@PostMapping(value = "listProducts", produces="application/json", consumes="application/json")
	public ListResponse<TranslatedProduct> listProducts(@RequestBody ProductsListRequest request) {
		return productService.listProducts(request);
	}
	
}
