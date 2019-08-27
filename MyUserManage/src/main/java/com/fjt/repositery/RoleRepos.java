package com.fjt.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fjt.pojo.Role;
import com.fjt.repositery.custom.RolereportCustom;

public interface RoleRepos
		extends CrudRepository<Role, Integer>, RolereportCustom {

	@Modifying
	@Query("delete from Role rn where rn.id=:id")
	public void deltRole(@Param("id") int id);

	@Query(value = "select rol.* from  emp_roles emp,role  rol where emp.rol_id=rol.rol_id and emp.emp_id=:id ", nativeQuery = true)
	public Role serch(@Param("id") int id);

	@Query(value = "from Role where  name=:rolename and levl=:levl")
	public List<Role> getRoleByNameAndLevl(@Param("rolename") String rolename,
			@Param("levl") String levl);
}
