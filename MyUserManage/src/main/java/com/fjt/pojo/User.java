package com.fjt.pojo;

import java.io.Serializable;
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

//使用序列，必须添加@SequenceGenerator注解

@Entity
@Table(name="users")
//allocationSize=1属性使序列从1开始.
@SequenceGenerator(name="emp_usersq",sequenceName="emp_usersq",allocationSize=1,initialValue=1)
public class User implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_usersq")
    @Column(name="usr_id")
	private int id;
	private String name;
	private String pssword;
	private String telep;
	private String addr;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<User_role> user_role;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	private List<ProjUser> projUser;
	
	
	
	public List<ProjUser> getProjUser() {
		return projUser;
	}
	public void setProjUser(List<ProjUser> projUser) {
		this.projUser = projUser;
	}
	public List<User_role> getUser_role() {
		return user_role;
	}
	public void setUser_role(List<User_role> user_role) {
		this.user_role = user_role;
	}
	public String getPssword() {
		return pssword;
	}
	public void setPssword(String pssword) {
		this.pssword = pssword;
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
	public String getTelep() {
		return telep;
	}
	public void setTelep(String telep) {
		this.telep = telep;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
