package com.deeodynamics.dore.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deeodynamics.dore.dao.HibernateGenericDAOImpl;

@Service
public class GenericDBServImpl<T, ID extends Serializable> 
				implements IGenericDBServ<T, ID> {
	
	private HibernateGenericDAOImpl<T, ID> hibernateGenericDAO;
	
	public void setHibernateGenericDAO(HibernateGenericDAOImpl<T, ID> hibernateGenericDAO) {
		this.hibernateGenericDAO = hibernateGenericDAO;
	}
	
	@Override
	@Transactional
	public ID makePersist(T o) { 
		return hibernateGenericDAO.makePersist(o); 
	}

	@Override
	@Transactional
	public boolean delete(ID id) { 
		return hibernateGenericDAO.delete(id); 
	}

	@Override
	@Transactional
	public List<T> selectAll() { 
		return hibernateGenericDAO.selectAll(); 
	}

	@Override
	public T get(ID id) {
		return hibernateGenericDAO.get(id);
	}
}