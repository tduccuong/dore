package com.deeodynamics.dore.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface for an implementation of the Nested Set Model.
 * @author Cuong Truong
 */
public interface INestedSetModelDAO<T, ID extends Serializable> {
	/**
	 * List the full tree using preorder tree traversal algorithm
	 * @return Full list of tree's nodes
	 */
	List<T> preorderTreeTraverse(String rootNodeName);
	
	/**
	 * @return All leaf nodes
	 */
	List<T> selectLeafNodes();
	
	/**
	 * @return A single path to a leaf node with leafNodeName
	 */
	List<T> selectSinglePathTo(String leafNodeName);
	
	/**
	 * @param 
	 * @return The depths of all nodes of the entire tree as a list of tuples (T, Integer) for (Node, Depth)
	 */
	List<Object[]> depthOfNodesOfTree();
	
	/**
	 * @param name of the root node of the sub-tree
	 * @return Depth of the sub-tree as list of tuples (T, Integer) for (Node, Depth)
	 */
	List<Object[]> depthOfNodesOfSubTree(String subTreeRootName);
	
	/**
	 * @param A node N with nodeName
	 * @return The immediate sub-ordinates of node N
	 */
	List<T> selectImmediateSubordinateOf(String nodeName);
	
	/**
	 * Insert node between nodeL and nodeR whose names are nodeLName and nodeRName, respectively
	 * @return ID of the newly inserted node
	 */
	ID insertBetween(String nodeName, String nodeLName, String nodeRName);
	
	/**
	 * Insert node to the left of nodeAnchor
	 * @return ID of the newly inserted node
	 */
	ID insertLeft(String nodeName, String nodeAnchorName);
	
	/**
	 * Insert node to the right of nodeAnchor
	 * @return ID of the newly inserted node
	 */
	ID insertRight(String nodeName, String nodeAnchorName);
}
