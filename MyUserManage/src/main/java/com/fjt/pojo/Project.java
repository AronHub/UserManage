package com.fjt.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
     * @ClassName: 项目表对于的domain类
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月19日
     *
 */
@Entity
@Table(name = "projects")
@SequenceGenerator(name = "seq_project", sequenceName = "seq_project", allocationSize = 1)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_project")
	private Integer id;

	private String name;

	private String manager;

	private String members;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

}
