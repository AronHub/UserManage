package com.fjt.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="emplyee")
@SequenceGenerator(name="emp_sequece",sequenceName="emp_sequece",allocationSize=1)
public class Emplye {

	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_sequece")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="emp")
	private List<Emp_Role> list; 

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Emp_Role> getList() {
		return list;
	}

	public void setList(List<Emp_Role> list) {
		this.list = list;
	}
	
	
	
}
