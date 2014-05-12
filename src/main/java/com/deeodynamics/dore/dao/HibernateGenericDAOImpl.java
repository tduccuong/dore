package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateGenericDAOImpl<T, ID extends Serializable> 
										implements IHibernateGenericDAO<T, ID> {
	
	private Class<T> classT;
  protected String table; // Name of the table that T represents
  
  public Class<T> classType() { 
  	return classT; 
  }
  
  public void setClassT(Class<T> classT) { 
  	this.classT = classT;
  	table = classT.getSimpleName();
  }
  
  private SessionFactory sessionFactory;
  
  @Autowired
  public void setSessionFactory(SessionFactory sessionFactory) { 
  	this.sessionFactory = sessionFactory; 
  }
  
  public SessionFactory getSessionFactory() { 
  	return sessionFactory; 
  }
  
  public Session session() { 
  	return sessionFactory.getCurrentSession(); 
  }
   
  @Override
  @SuppressWarnings("unchecked")
  public ID makePersist(T o) { 
  	return (ID) session().save(o); 
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public T get(ID id) { 
  	return (T) session().get(classT, id); 
  }
  
  @Override
  public boolean delete(T o) { 
  	session().delete(o);
  	return true;
  }

  @Override
  public boolean delete(ID id) { 
  	session().delete(get(id));
  	return true;
  }
    
  @SuppressWarnings("unchecked")
	public List<T> selectHQL(String hqlQuery) { 
  	return session().createQuery(hqlQuery).list(); 
  }
  
  @SuppressWarnings("unchecked")
	public List<T> selectSQL(String sqlQuery) { 
  	return session().createSQLQuery(sqlQuery).list(); 
  }

  @SuppressWarnings("unchecked")
  public List<T> selectCriteria(Criterion... criterion) {
      Criteria crit =  session().createCriteria(classType());
      
      for (Criterion c : criterion) 
      	crit.add(c);

      return crit.list();
  }

  @Override
  public List<T> selectAll() { 
  	return selectCriteria(); 
  }
    
  @SuppressWarnings("unchecked")
  public List<T> selectExample(T example, String... excludeProperty) {
      Criteria crit = session().createCriteria(classType());
      Example ex = Example.create(example);
      for (String exclude : excludeProperty) 
          ex.excludeProperty(exclude);
      crit.add(ex);
      
      return crit.list();
  }

	@Override
	public void evict(Object obj) {
		session().evict(obj);
	}

	@Override
	public void flush() {
		session().flush();
	}
}