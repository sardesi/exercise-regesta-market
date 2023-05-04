package com.regesta.exercise.regestamarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.regesta.exercise.regestamarket.model.dto.ListResponse;
import com.regesta.exercise.regestamarket.model.dto.PricedProduct;
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
	 * @param request The body of the request, it contains the pagination info and the name filter.
	 * @param language The language of the translated product. It's mandatory.
	 * @return
	 */
	@PostMapping(value = "listTranslatedProducts", produces="application/json", consumes="application/json")
	public ListResponse<TranslatedProduct> listTranslatedProducts(@RequestBody ProductsListRequest request, @RequestParam(required = true) String language) {
		request.check(language);
		return productService.listTranslatedProducts(request, language);
	}
	
	/**
	 * Returns all the possibile variants of product/supplier for the requested product id.
	 * @param productId The id of the product of wich to recover the variants.
	 * @return
	 */
	@GetMapping(value = "listPricedProducts/{productId}", produces="application/json")
	public List<PricedProduct> listPricedProducts(@PathVariable Integer productId) {
		return productSupplierService.listPricedProducts(productId);
	}
	
}
