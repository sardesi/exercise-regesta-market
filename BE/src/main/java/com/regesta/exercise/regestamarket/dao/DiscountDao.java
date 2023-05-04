package com.regesta.exercise.regestamarket.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.regesta.exercise.regestamarket.model.entity.Discount;

/**
 * The dao/repository layer of the Discount entity.
 * @author ars
 *
 */
public class DiscountDao extends AbstractDao<Discount> implements EntityDao<Discount> {

	public DiscountDao(Class<Discount> entityClass) {
		super(entityClass);
	}
	
	public List<Discount> getDiscountByDate(LocalDate date) {
		
		Session session = null;
			
		session = takeSession();
		String queryString = "SELECT new com.regesta.exercise.regestamarket.model.dto.DiscountInfo(d.dateFrom, d.dateTo, d.minPieceDiscount, d.percentage, d.priceRangeFrom, d.priceRangeTo, d.supplier.id)"
						   + " FROM Discount d"
						   + " WHERE d.dateFrom <= :date AND d.dateTo >= :date";
		
		
		Query<Discount> query = session.createQuery(queryString, Discount.class);
		query.setParameter("date", date);
		
		return query.list();
		
	}

}