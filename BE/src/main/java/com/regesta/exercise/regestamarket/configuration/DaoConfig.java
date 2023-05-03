package com.regesta.exercise.regestamarket.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.regesta.exercise.regestamarket.dao.DiscountDao;
import com.regesta.exercise.regestamarket.dao.ProductDao;
import com.regesta.exercise.regestamarket.dao.ProductDictionaryDao;
import com.regesta.exercise.regestamarket.dao.ProductSupplierDao;
import com.regesta.exercise.regestamarket.dao.SupplierDao;
import com.regesta.exercise.regestamarket.dao.UserDao;
import com.regesta.exercise.regestamarket.model.entity.Discount;
import com.regesta.exercise.regestamarket.model.entity.Product;
import com.regesta.exercise.regestamarket.model.entity.ProductDictionary;
import com.regesta.exercise.regestamarket.model.entity.ProductSupplier;
import com.regesta.exercise.regestamarket.model.entity.Supplier;
import com.regesta.exercise.regestamarket.model.entity.User;
import com.regesta.exercise.regestamarket.service.DiscountService;
import com.regesta.exercise.regestamarket.service.LoginService;
import com.regesta.exercise.regestamarket.service.ProductDictionaryService;
import com.regesta.exercise.regestamarket.service.ProductService;
import com.regesta.exercise.regestamarket.service.ProductSupplierService;
import com.regesta.exercise.regestamarket.service.SupplierService;
import com.regesta.exercise.regestamarket.service.UserService;

/**
 * Configuration for the creation of the hibernate session factory and to declare all the beans for the daos and services.
 * @author ars
 *
 */
@Configuration
@EnableTransactionManagement
public class DaoConfig {

	@Autowired
	AppPropertiesConfig appProperties;

	@Autowired
	DataSource dataSource;

	@Value("${hibernate.dialect}")
	private String dialect;

	@Value("${hibernate.format_sql}")
	private String formatSql;

	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.use_sql_comments}")
	private String useSqlComments;
	
	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);

		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.format_sql", formatSql);
		hibernateProperties.put("hibernate.show_sql", false);
		hibernateProperties.put("hibernate.use_sql_comments", useSqlComments);
		sessionFactory.setHibernateProperties(hibernateProperties);
		
		sessionFactory.setAnnotatedClasses(
				com.regesta.exercise.regestamarket.model.entity.User.class
		);

		return sessionFactory;
		
	}

	@Bean
	public LoginService loginService() {
		return new LoginService();
	}

	@Bean
	public DiscountDao discountDao() {
		DiscountDao bean = new DiscountDao(Discount.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public DiscountService discountService() {
		DiscountService bean = new DiscountService();
		bean.setDao(discountDao());
		return bean;
	}

	@Bean
	public ProductDao productDao() {
		ProductDao bean = new ProductDao(Product.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public ProductService productService() {
		ProductService bean = new ProductService();
		bean.setDao(productDao());
		return bean;
	}

	@Bean
	public ProductDictionaryDao productDictionaryDao() {
		ProductDictionaryDao bean = new ProductDictionaryDao(ProductDictionary.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public ProductDictionaryService productDictionaryService() {
		ProductDictionaryService bean = new ProductDictionaryService();
		bean.setDao(productDictionaryDao());
		return bean;
	}

	@Bean
	public ProductSupplierDao productSupplierDao() {
		ProductSupplierDao bean = new ProductSupplierDao(ProductSupplier.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public ProductSupplierService productSupplierService() {
		ProductSupplierService bean = new ProductSupplierService();
		bean.setDao(productSupplierDao());
		return bean;
	}

	@Bean
	public SupplierDao supplierDao() {
		SupplierDao bean = new SupplierDao(Supplier.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public SupplierService supplierService() {
		SupplierService bean = new SupplierService();
		bean.setDao(supplierDao());
		return bean;
	}

	@Bean
	public UserDao userDao() {
		UserDao bean = new UserDao(User.class);
		bean.setSessionFactory(sessionFactory().getObject());
		return bean;
	}
	@Bean
	public UserService userService() {
		UserService bean = new UserService();
		bean.setDao(userDao());
		return bean;
	}
	
}
