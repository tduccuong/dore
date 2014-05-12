package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.deeodynamics.dore.domain.Category;

/**
 * @author Cuong Truong
 * This implementation is for Hibernate
 * See http://mikehillyer.com/articles/managing-hierarchical-data-in-mysql/ for more.
 * Assumption: The nested set model table ALWAYS has the columns "name", "left", and "right" 
 */
@SuppressWarnings("unchecked")
public class NestedSetModelDAOImpl<T, ID extends Serializable> 
							extends HibernateGenericDAOImpl<T, ID> 
							implements INestedSetModelDAO<T, ID> {

	@SuppressWarnings("unchecked")
	public ID addRoot(String rootName) {
		ID id = null;
		
		// Only add the root node when the table is empty
		if (session().createQuery("from Category").list().size() == 0) {
			Category cat = new Category();
			cat.setName(rootName);
			cat.setParent(0);
			cat.setLft(0);
			cat.setRgt(1);
			id = (ID)session().save(cat);
		}
		
		return id;
	}
	
	public ID addUnder(String nodeName, String newNodeName) {
		ID id = null;
		
		// if nodeName is found then add new node under it
		List<T> list = session().createQuery("from "+table+" where name = :nodeName")
																	.setParameter("nodeName", nodeName).list();
		if (list.size() == 1) {
			T obj = list.get(0); 
			int right = obj.getRgt();
			int parent = cat.getId();
			Query hql = session.createQuery("update Category set lft = lft + 2 where lft >= :right")
													.setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set rgt = rgt + 2 where rgt >= :right")
													.setParameter("right", right);
			hql.executeUpdate();
			id = (Integer)session.save(new Category(newNodeName, parent, right, right+1));
		}
		
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public static Integer addLeft(Session session, String nodeName, String newNodeName) {
		Integer id = null;
		
		// if nodeName is found then add new node left to it
		List<Category> cats = session.createQuery("from Category where name = :nodeName")
																	.setParameter("nodeName", nodeName).list();
		if (cats.size() == 1) {
			Category cat = cats.get(0);
			int left = cat.getLft();
			int parent = cat.getParent();
			Query hql = session.createQuery("update Category set lft = lft + 2 where lft >= :left")
													.setParameter("left", left);
			hql.executeUpdate();
			hql = session.createQuery("update Category set rgt = rgt + 2 where rgt > :left")
													.setParameter("left", left);
			hql.executeUpdate();
			id = (Integer)session.save(new Category(newNodeName, parent, left, left+1));
		}
		
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public static Integer addRight(Session session, String nodeName, String newNodeName) {
		Integer id = null;
		
		// if nodeName is found then add new node left to it
		List<Category> cats = session.createQuery("from Category where name = :nodeName")
																	.setParameter("nodeName", nodeName).list();
		if (cats.size() == 1) {
			Category cat = cats.get(0);
			int right = cat.getRgt();
			int parent = cat.getParent();
			Query hql = session.createQuery("update Category set lft = lft + 2 where lft > :right")
													.setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set rgt = rgt + 2 where rgt > :right")
													.setParameter("right", right);
			hql.executeUpdate();
			id = (Integer)session.save(new Category(newNodeName, parent, right+1, right+2));
		}
		
		return id;
	}
	
	@SuppressWarnings("unchecked")
	public static void deleteTree(Session session, String treeRootName) {
		Integer id = null;
		
		// if treeRootName is found then delete it
		List<Category> cats = session.createQuery("from Category where name = :nodeName")
																	.setParameter("nodeName", treeRootName).list();
		if (cats.size() == 1) {
			Category cat = cats.get(0);
			int left = cat.getLft();
			int right = cat.getRgt();
			Query hql = session.createQuery("delete Category where lft >= :left and rgt <= :right")
													.setParameter("left", left).setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set rgt = rgt - :width where rgt > :right")
										.setParameter("width", right-left+1).setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set lft = lft - :width where lft > :right")
					.setParameter("width", right-left+1).setParameter("right", right);
			hql.executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void deleteChildrenOf(Session session, String nodeName) {
		Integer id = null;
		
		// if treeRootName is found then delete it
		List<Category> cats = session.createQuery("from Category where name = :nodeName")
																	.setParameter("nodeName", nodeName).list();
		if (cats.size() == 1) {
			Category cat = cats.get(0);
			int left = cat.getLft();
			int right = cat.getRgt();
			Query hql = session.createQuery("delete Category where lft > :left and rgt < :right")
													.setParameter("left", left).setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set rgt = rgt - :width where rgt >= :right")
										.setParameter("width", right-left-1).setParameter("right", right);
			hql.executeUpdate();
			hql = session.createQuery("update Category set lft = lft - :width where lft > :right")
					.setParameter("width", right-left-1).setParameter("right", right);
			hql.executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void updateName(Session session, String nodeName, String newName) {
		Integer id = null;
		
		// if treeRootName is found then delete it
		List<Category> cats = session.createQuery("from Category where name = :nodeName")
																	.setParameter("nodeName", nodeName).list();
		if (cats.size() == 1) {
			Query hql = session.createQuery("update Category set name = :newName where name = :nodeName")
										.setParameter("newName", newName).setParameter("nodeName", nodeName);
			hql.executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Category> listTree(Session session, String rootName) {
		String hqlStr = "select new Category(node.id, node.name, node.photo, node.parent, node.lft, node.rgt) "
				+ "from Category node, Category ancestor "
				+ "where node.lft between ancestor.lft and ancestor.rgt and ancestor.name = :rootName "
				+ "order by node.lft";
		
		return session.createQuery(hqlStr).setParameter("rootName", rootName).list();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Category> listChildrenOf(Session session, String nodeName) {
		String hqlStr = "select new Category(node.id, node.name, node.photo, node.parent, node.lft, node.rgt) "
				+ "from Category node, Category ancestor "
				+ "where node.lft between ancestor.lft and ancestor.rgt and ancestor.name = :nodeName and node.parent = ancestor.id "
				+ "order by node.lft";
		
		return session.createQuery(hqlStr).setParameter("nodeName", nodeName).list();
	}
}