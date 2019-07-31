package com.fjt.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="emp_roles")
@SequenceGenerator(name="emrole_squence",sequenceName="emrole_squence" ,allocationSize=1)
public class Emp_Role {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emrole_squence")
	private int id;
	
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="emp_id")
	private Emplye emp;
	
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="rol_id")
	private Role roln;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Emplye getEmp() {
		return emp;
	}

	public void setEmp(Emplye emp) {
		this.emp = emp;
	}

	public Role getRoln() {
		return roln;
	}

	public void setRoln(Role roln) {
		this.roln = roln;
	}

	
}
