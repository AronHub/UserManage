package com.fjt.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "emp_roles")
@SequenceGenerator(name = "emrole_squence", sequenceName = "emrole_squence", allocationSize = 1)
public class Emp_Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emrole_squence")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
