package com.fjt.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fjt.pojo.Role;
import com.fjt.repositery.RoleRepos;

@Component
public class RoleBiz {
	@Autowired
	private RoleRepos reRoleRepos;

	public Page<Role> findRoleInfo(Pageable pageable, String rolename,
			String levl) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jpql = "select rn from Role rn where 1=1 ";
		if (rolename != null && !rolename.equals("")) {
			jpql += "and rn.name like :rolename";
			map.put("rolename", "%" + rolename + "%");

		}
		if (levl != null && !levl.equals("")) {
			jpql += " and rn.levl like :levl";
			map.put("levl", "%" + levl + "%");
		}
		jpql += " order by rn.id";
		return reRoleRepos.findRoleInfo(pageable, map, jpql);
	}

	public List<Role> serchAll() {
		return (List<Role>) reRoleRepos.findAll();
	}

	public void add(Role role) {
		reRoleRepos.save(role);
	}

	public void deltRole(int id) {
		/*reRoleRepos.deltRole(id);*/
		reRoleRepos.delteRole(id);
	}

	public void deletAll() {
		reRoleRepos.deltAll();
	}

	public Role getRoleByID(int id) {
		return reRoleRepos.getRoleByID(id);
	}

	public Role serch(int id) {
		return reRoleRepos.serch(id);
	}

	public List<Role> getRoleByNameAndLevl(String rolename, String levl) {
		//这个调用有问题，需要改进，不能使用注解的方式去查。？？？？？
		return reRoleRepos.getRoleByNameAndLevl(rolename, levl);
	}
}
