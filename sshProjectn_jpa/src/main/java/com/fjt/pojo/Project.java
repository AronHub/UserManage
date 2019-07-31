package com.fjt.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//ÏîÄ¿±í
@Entity
@Table(name="projet")
@SequenceGenerator(name="sq_proj",sequenceName="sq_proj",allocationSize=1)
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sq_proj")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="project")
	private List<ProjUser> list;
	

	public List<ProjUser> getList() {
		return list;
	}

	public void setList(List<ProjUser> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
