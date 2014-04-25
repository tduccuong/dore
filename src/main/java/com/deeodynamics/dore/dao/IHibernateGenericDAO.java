package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * Generic DAO class.
 * @author Cuong Truong
 * @param <T> 
 * @param <ID> 
 */
public interface IHibernateGenericDAO<T, ID extends Serializable> 
					extends IGenericDAO<T, ID> {

    List<T> selectHQL(String hqlQuery);
    
    List<T> selectSQL(String sqlQuery);

    List<T> selectExample(T example, String... excludeProperty);
    
    List<T> selectCriteria(Criterion... criteria);

    void evict(Object obj);
    
    void flush();
}