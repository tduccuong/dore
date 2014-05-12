package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.deeodynamics.dore.domain.Category;

/**
 * Generic interface for an implementation of the Nested Set Model.
 * @author Cuong Truong
 */
public interface INestedSetModelDAO<T, ID extends Serializable> {
	ID addRoot(String rootName);
	
	ID addUnder(String nodeName, String newNodeName);
	
	ID addLeft(String nodeName, String newNodeName);
	
	ID addRight(String nodeName, String newNodeName);
	
	void deleteTree(String treeRootName);
	
	void deleteChildrenOf(String nodeName);
	
	void updateName(String nodeName, String newName);
	
	List<T> listTree(String rootName);
	
	List<T> listChildrenOf(String nodeName);
}
