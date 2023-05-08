package com.regesta.exercise.regestamarket.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.regesta.exercise.regestamarket.model.dto.ProductsListRequest;
import com.regesta.exercise.regestamarket.model.dto.TranslatedProduct;
import com.regesta.exercise.regestamarket.model.entity.Product;

/**
 * The dao/repository layer of the Product entity.
 * @author ars
 *
 */
public class ProductDao extends AbstractDao<Product> implements EntityDao<Product> {

	public ProductDao(Class<Product> entityClass) {
		super(entityClass);
	}

	public List<TranslatedProduct> listTranslatedProducts(ProductsListRequest request, String language) {
		
		Session session = null;
			
		session = takeSession();
		String queryString = "SELECT new com.regesta.exercise.regestamarket.model.dto.TranslatedProduct(p.id, p.code, pd.name, pd.description, p.image)"
						   + " FROM Product p LEFT OUTER JOIN ProductDictionary pd ON p.id = pd.product.id AND (pd.language = :language OR pd.language IS NULL)"
						   + " WHERE 1=1";
		
		if(!StringUtils.isEmpty(request.getCode())) {
			queryString += " AND LOWER(p.code) LIKE(:code)";
		}
		if(!StringUtils.isEmpty(request.getName())) {
			queryString += " AND LOWER(pd.name) LIKE(:name)";
		}
		
		if(request.getPagination() != null && !StringUtils.isEmpty(request.getPagination().getFieldOrder())) {
			queryString += " ORDER BY " + request.getPagination().getFieldOrder();
			if(request.getPagination().isDesc()) queryString += " DESC";
		}
		
		Query<TranslatedProduct> query = session.createQuery(queryString, TranslatedProduct.class);
		query.setParameter("language", language);

		if(!StringUtils.isEmpty(request.getCode())) {
			query.setParameter("code", "%" + request.getCode().toLowerCase() + "%");
		}
		if(!StringUtils.isEmpty(request.getName())) {
			query.setParameter("name", "%" + request.getName().toLowerCase() + "%");
		}
		
		if(request.getPagination() != null) {
			if(request.getPagination().getLimit() != null) query.setMaxResults(request.getPagination().getLimit());
			if(request.getPagination().getOffset() != null) query.setFirstResult(request.getPagination().getOffset());
		}
		
		return query.list();
		
	}

	public Long countTranslatedProducts(ProductsListRequest request, String language) {
		
		Session session = null;
			
		session = takeSession();
		String queryString = "SELECT COUNT(p)"
						   + " FROM Product p LEFT OUTER JOIN ProductDictionary pd ON p.id = pd.product.id AND (pd.language = :language OR pd.language IS NULL)"
						   + " WHERE 1=1";
		
		if(!StringUtils.isEmpty(request.getCode())) {
			queryString += " AND LOWER(p.code) LIKE(:code)";
		}
		if(!StringUtils.isEmpty(request.getName())) {
			queryString += " AND LOWER(pd.name) LIKE(:name)";
		}
		
		Query<Long> query = session.createQuery(queryString, Long.class);
		query.setParameter("language", language);

		if(!StringUtils.isEmpty(request.getCode())) {
			query.setParameter("code", "%" + request.getCode().toLowerCase() + "%");
		}
		if(!StringUtils.isEmpty(request.getName())) {
			query.setParameter("name", "%" + request.getName().toLowerCase() + "%");
		}
		
		return query.getSingleResult();
		
	}
	
}