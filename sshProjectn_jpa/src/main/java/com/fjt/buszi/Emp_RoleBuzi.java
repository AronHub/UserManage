package com.fjt.buszi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fjt.pojo.Emp_Role;
import com.fjt.pojo.Role;
import com.fjt.repositery.Emp_RoleRepos;
import com.fjt.service.Emp_RoleService;

@Component
public class Emp_RoleBuzi {
	@Autowired
	private Emp_RoleRepos emp_UseRepos;
	
	public void add(Emp_Role em_role) {
		emp_UseRepos.save(em_role);
	}
	
	public Emp_Role find(int idn, int role_id) {
		return emp_UseRepos.find(idn,role_id);
	}
	
}
