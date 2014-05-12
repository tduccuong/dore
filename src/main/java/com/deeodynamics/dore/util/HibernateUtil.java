package com.deeodynamics.dore.util;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.deeodynamics.dore.domain.Category;
import com.deeodynamics.dore.domain.Customer;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			Properties properties = new Properties();
			properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	    properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/dore");
	    properties.setProperty("hibernate.connection.username", "tduccuong");
	    properties.setProperty("hibernate.connection.password", "cd26091983");
	    properties.setProperty("hibernate.show_sql", "com.mysql.jdbc.Driver");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			
			Configuration configuration = new AnnotationConfiguration()
																				.setProperties(properties)
																				.addPackage("com.deeodynamics.dore.domain")
																				.addAnnotatedClass(Category.class)
																				.addAnnotatedClass(Customer.class);
	    StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	    
	    sessionFactory = configuration.buildSessionFactory(ssrb.build());
		} catch (HibernateException he) {
			throw new ExceptionInInitializerError(he);
		}
	}
	
	public static SessionFactory sessionFactory() {
		return sessionFactory;
	}
}
