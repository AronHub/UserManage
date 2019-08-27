package com.fjt.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "emplyee")
@SequenceGenerator(name = "emp_sequece", sequenceName = "emp_sequece", allocationSize = 1)
public class Emplye {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_sequece")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

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

}
