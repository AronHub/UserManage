package com.fjt.service;

import com.fjt.pojo.Emp_Role;
import com.fjt.pojo.Role;

public interface Emp_RoleService {

	public void add(Emp_Role em_role);
	
	public Emp_Role find(int idn,int role_id);
	
}
