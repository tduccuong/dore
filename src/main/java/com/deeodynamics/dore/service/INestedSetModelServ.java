package com.deeodynamics.dore.service;

import java.io.Serializable;
import java.util.List;

public interface INestedSetModelServ<T, ID extends Serializable> {
	public List<T> preorderTreeTraverse();
	
	public List<T> selectLeafNodes();

	public List<T> selectSinglePath();

	public int depthOfNode(T node);

	public int depthOfSubTree(T subTree);

	public int insertBetween(T node, T nodeL, T nodeR);

	public int insertLeft(T node, T nodeAnchor);

	public int insertRight(T node, T nodeAnchor);
}
