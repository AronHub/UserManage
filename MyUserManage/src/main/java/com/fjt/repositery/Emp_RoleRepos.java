package com.fjt.repositery;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fjt.pojo.Emp_Role;
import com.fjt.pojo.Role;

public interface Emp_RoleRepos extends CrudRepository<Emp_Role,Integer>{

	@Query(value="select * from  emp_roles where emp_id=:id and rol_id=:rol_id",nativeQuery=true)
	public Emp_Role find(@Param("id") int idn, @Param("rol_id") int role_id);
}
