package com.fjt.repositery.custom;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjt.pojo.Role;

public interface RolereportCustom {

	public Page<Role> findRoleInfo(Pageable pageable, Map<String, Object> map,
			String jpql);

	public void delteRole(int id);

	public void deltAll();

	public Role getRoleByID(int id);
}
