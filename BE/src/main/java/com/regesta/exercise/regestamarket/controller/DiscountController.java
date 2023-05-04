package com.regesta.exercise.regestamarket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.regesta.exercise.regestamarket.model.entity.Discount;
import com.regesta.exercise.regestamarket.service.DiscountService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Discount")
@RequestMapping("api/discount")
public class DiscountController extends BaseRestController {

	@Autowired(required=true)
	private DiscountService discountService;
	
	/**
	 * Returns the list of all the discounts available on a specific date.
	 * @param date The date to use while extracting the discounts. The format is yyyy/MM/dd.
	 * @return The list of the active discounts.
	 */
	@GetMapping(value = "listByDate", produces="application/json")
	public List<Discount> getDiscountByDate(@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return discountService.getDiscountByDate(date);	
	}
	
}
