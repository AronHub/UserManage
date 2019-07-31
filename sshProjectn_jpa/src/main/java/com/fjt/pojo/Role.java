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

@Entity
@Table(name="role")
@SequenceGenerator(name = "emp_role",sequenceName="emp_role",allocationSize=1,initialValue=1)
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_role")
	@Column(name="rol_id")
    private int id;
    private String name; 
    private String levl;
    @OneToMany(cascade=CascadeType.ALL,mappedBy="role")
    private List<User_role> user_role;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="roln")
    private List<Emp_Role> emp_role;
    
    
	public List<User_role> getUser_role() {
		return user_role;
	}
	public void setUser_role(List<User_role> user_role) {
		this.user_role = user_role;
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
	public String getLevl() {
		return levl;
	}
	public void setLevl(String levl) {
		this.levl = levl;
	}

}
