package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * DB C.R.U.D operations
 * @author deeo
 *
 */
public interface IGenericDAO<T, ID extends Serializable> {
	ID makePersist(T o);
	
	T get(ID id);
	
	boolean delete(T o);
	
	boolean delete(ID id);
	
	List<T> selectAll();
}
