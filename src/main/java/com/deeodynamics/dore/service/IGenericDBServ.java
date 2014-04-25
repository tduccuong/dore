package com.deeodynamics.dore.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericDBServ<T, ID extends Serializable> {
	public ID makePersist(T o);
	public T get(ID id);
	public boolean delete(ID id);
	public List<T> selectAll();
}
