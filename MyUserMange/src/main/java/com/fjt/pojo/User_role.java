package com.fjt.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="user_role")
@SequenceGenerator(name="emp_usroleseq",sequenceName="emp_usroleseq",allocationSize=1)
public class User_role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_usroleseq")
	@Column(name="usrol_id")
	private int id;
	
	@JoinColumn(name="role_id",referencedColumnName="rol_id")
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Role role;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User user;
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	
	
}
