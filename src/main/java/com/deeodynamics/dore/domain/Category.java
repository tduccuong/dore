package com.deeodynamics.dore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category extends NestedSet {
	
	public Category() {}
	
	public Category(String name, Integer parent, Integer lft, Integer rgt) {
		/*this.name = name;
		this.parent = parent;
		this.lft = lft;
		this.rgt = rgt;*/
		super(name, parent, lft, rgt);
	}
	
	public Category(Integer id, String name, String photo, Integer parent, Integer lft, Integer rgt) {
		/*this.id = id;
		this.name = name;
		this.photo = photo;
		this.parent = parent;
		this.lft = lft;
		this.rgt = rgt;*/
		super(id, name, parent, lft, rgt);
		this.photo = photo;
	}
	
	/*@Id
	@Column(name="id")
	@GeneratedValue
	private Integer id;
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	@Column(name="name")
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }*/
	
	@Column(name="photo")
	private String photo;
	public String getPhoto() { return photo; }
	public void setPhoto(String photo) { this.photo = photo; }
	
	/*@Column(name="parent", nullable=false)
	private Integer parent;
	public Integer getParent() { return parent; }
	public void setParent(Integer parent) { this.parent = parent; }
	
	@Column(name="lft", nullable=false)
	private Integer lft;
	public Integer getLft() { return lft; }
	public void setLft(Integer lft) { this.lft = lft; }
	
	@Column(name="rgt", nullable=false)
	private Integer rgt;
	public Integer getRgt() { return rgt; }
	public void setRgt(Integer rgt) { this.rgt = rgt; }*/
	
	public String toString() {
		return id.toString()+", "+name+", "+photo+", "+parent.toString()+", "+lft.toString()+", "+rgt.toString();
	}
}