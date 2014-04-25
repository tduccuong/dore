package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

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

	@Override
	public List<T> preorderTreeTraverse(String rootNodeName) {
		String hqlStr = "from "+table+" as parent " + 
										"where "+table+".left between parent.left and parent.right " +
										"and parent.name = :rootNodeName order by " + table + ".left";
		Query hqlQuery = session().createQuery(hqlStr);
		hqlQuery.setParameter("rootNodeName", rootNodeName);
		
		return hqlQuery.list();
	}

	@Override
	public List<T> selectLeafNodes() {
		String hqlStr = "from "+ table +" where right = left + 1";
		Query hqlQuery = session().createQuery(hqlStr);
		
		return hqlQuery.list();
	}

	@Override
	public List<T> selectSinglePathTo(String leafNodeName) {
		String hqlStr = "from "+table+" as node, "+table+" as parent " + 
										"where node.left between parent.left and parent.right " +
										"and node.name = :leafNodeName "+
										"order by node.left";
		Query hqlQuery = session().createQuery(hqlStr);
		hqlQuery.setParameter("leafNodeName", leafNodeName);

		return hqlQuery.list();
	}

	@Override
	public List<Object[]> depthOfNodesOfTree() {
		String hqlStr = "select node.name, (count(parent.name) - 1) as depth " + 
				 						"from "+table+" as node, "+table+" as parent " + 
										"where node.left between parent.left and parent.right " +
										"group by node.name order by node.left";
		Query hqlQuery = session().createQuery(hqlStr);

		return hqlQuery.list();
	}
	
	@Override
	public List<Object[]> depthOfNodesOfSubTree(String subTreeRootName) {
		String hqlStr = "select node.name, (count(parent.name) - (subtree.depth + 1)) as depth " + 
					"from "+table+" as node, "+table+" as parent, "+table+" as subparent, "+
							"(select node.name, (count(parent.name)-1) as depth "+
							"from "+table+" as node, "+table+" as parent "+
							"where node.left between parent.left and parent.right and node.name = :subTreeRootName "+
							"group by node.name order by node.left) as subtree "+
					"where node.left between parent.left and parent.right "+
							"and node.left between subparent.left and subparent.right "+
							"and subparent.name = subtree.name "+
					"group by node.name order by node.left";
		Query hqlQuery = session().createQuery(hqlStr);
		hqlQuery.setParameter("subTreeRootName", subTreeRootName);

		return hqlQuery.list();
	}
	
	@Override
	public List<T> selectImmediateSubordinateOf(String nodeName) {
		String hqlStr = "select node.name, (count(parent.name) - (subtree.depth + 1)) as depth " + 
				"from "+table+" as node, "+table+" as parent, "+table+" as subparent, "+
						"(select node.name, (count(parent.name)-1) as depth "+
						"from "+table+" as node, "+table+" as parent "+
						"where node.left between parent.left and parent.right and node.name = :nodeName "+
						"group by node.name order by node.left) as subtree "+
				"where node.left between parent.left and parent.right "+
						"and node.left between subparent.left and subparent.right "+
						"and subparent.name = subtree.name "+
				"group by node.name "+
				"having depth <= 1 "+
				"order by node.left";
		Query hqlQuery = session().createQuery(hqlStr);
		hqlQuery.setParameter("nodeName", nodeName);
	
		return hqlQuery.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public ID insertBetween(String nodeName, String nodeLName, String nodeRName) {
		Iterator ite = session().createQuery("select right from "+table+" where name = :nodeLName")
																.setParameter("nodeLName", nodeLName)
																.list().iterator();
		Object[] tuple = null;
		Integer nRight = null;
		if (ite.hasNext()) tuple = (Object[])ite.next();
		if (tuple != null)
			nRight = (Integer)tuple[0];
		if (nRight != null) {
			session().createQuery(
					"update "+table+" set right = right + 2 where right > :nRight")
					.setParameter("nRight", nRight.toString())
					.executeUpdate();
			session().createQuery(
					"update "+table+" set left = left + 2 where left > :nRight")
					.setParameter("nRight", nRight.toString())
					.executeUpdate();
			session().createQuery(
					"insert into "+table+"(name, left, right) "+
					"values(:nodeName, :nRight + 1, :nRight + 2)")
					.setParameter("nRight", nRight.toString())
					.executeUpdate();
		}
			
		return null;
	}

	@Override
	public ID insertLeft(String nodeName, String nodeAnchorName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ID insertRight(String nodeName, String nodeAnchorName) {
		// TODO Auto-generated method stub
		return null;
	}
}