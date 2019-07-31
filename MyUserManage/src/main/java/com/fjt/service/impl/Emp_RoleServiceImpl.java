package com.fjt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjt.buszi.Emp_RoleBuzi;
import com.fjt.pojo.Emp_Role;
import com.fjt.pojo.Role;
import com.fjt.service.Emp_RoleService;

@Service
public class Emp_RoleServiceImpl implements Emp_RoleService{

	@Autowired
	private Emp_RoleBuzi emp_UseBuzi;
	
	@Override
	public void add(Emp_Role em_role) {
		emp_UseBuzi.add(em_role);	
	}

	@Override
	public Emp_Role find(int idn, int role_id) {
		// TODO Auto-generated method stub
		return emp_UseBuzi.find(idn,role_id);
	}



}
