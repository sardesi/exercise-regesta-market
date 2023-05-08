package com.regesta.exercise.regestamarket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.regesta.exercise.regestamarket.model.dto.PricedProduct;
import com.regesta.exercise.regestamarket.model.entity.ProductSupplier;

/**
 * The dao/repository layer of the ProductSupplier entity.
 * @author ars
 *
 */
public class ProductSupplierDao extends AbstractDao<ProductSupplier> implements EntityDao<ProductSupplier> {

	public ProductSupplierDao(Class<ProductSupplier> entityClass) {
		super(entityClass);
	}
	
	public List<PricedProduct> listPricedProducts(Integer productId) {
		
		Session session = null;
			
		session = takeSession();
		String queryString = "SELECT new com.regesta.exercise.regestamarket.model.dto.PricedProduct(ps.id.productId, ps.id.supplierId, ps.availability, ps.price, s.name, s.shippingDays)"
						   + " FROM ProductSupplier ps JOIN Supplier s ON ps.id.supplierId = s.id"
						   + " WHERE ps.id.productId = :productId";
		
		
		Query<PricedProduct> query = session.createQuery(queryString, PricedProduct.class);
		query.setParameter("productId", productId);
		
		return query.list();
		
	}

}