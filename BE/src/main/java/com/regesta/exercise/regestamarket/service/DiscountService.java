package com.regesta.exercise.regestamarket.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.regesta.exercise.regestamarket.dao.DiscountDao;
import com.regesta.exercise.regestamarket.model.dto.DiscountInfo;
import com.regesta.exercise.regestamarket.model.entity.Discount;

/**
 * The service layer of the Discount entity.
 * @author ars
 *
 */
public class DiscountService {

	private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
	
	@Autowired
	private DiscountDao dao;
	
	public void setDao(DiscountDao dao) {
		this.dao = dao;
	}
	
/* Base methods */

	@Transactional(readOnly=true)
	public Discount getById(Serializable id) {
		Discount entity = new Discount();
		entity.setId((Integer)id);
		return dao.get(entity);
	}

	@Transactional
	public Discount addOrUpdate(Discount entity) {
		return this.dao.addOrUpdate(entity);
	}

	@Transactional
	public void remove(Serializable id) {
		Discount entity = new Discount();
		entity.setId((Integer)id);		
		dao.remove(entity);
	}
	
/* Custom methods */
	
	/**
	 * Returns the list of all the discounts available on a specific date.
	 * @param date The date to use while extracting the discounts. The format is yyyy/MM/dd.
	 * @return The list of the active discounts.
	 */
	@Transactional
	public List<DiscountInfo> getDiscountByDate(LocalDate date) {
		
		logger.debug("DiscountService | getDiscountByDate | START  | date: {}.", date);
		
		return dao.getDiscountByDate(date);
		
	}
		
	
}
