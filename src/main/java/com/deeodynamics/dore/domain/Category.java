package com.deeodynamics.dore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Category {
	
	public Category() {}
	
	public Category(String name) { this.name = name; }
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	@Column(name="name")
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name="left")
	private Integer left;
	public Integer getLeft() { return left; }
	public void setLeft(Integer left) { this.left = left; }
	
	@Column(name="right")
	private Integer right;
	public Integer getRight() { return right; }
	public void setRight(Integer right) { this.right = right; }
}