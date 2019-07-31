package com.fjt.buszi;

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
public class RoleBuzzi {
	@Autowired
	private RoleRepos reRoleRepos;
	
	public Page<Role> serfind(Pageable pageable, String rolename,
			String levl){
		Map<String, Object> map=new HashMap<String,Object>();
		String jpql="select rn from Role rn where 1=1 ";
		if(rolename!=null&&!rolename.equals("")){
			jpql+="and rn.name like :rolename";
			map.put("rolename","%"+rolename+"%");
			
		}
		if(levl!=null&&!levl.equals("")){
			jpql+=" and rn.levl like :levl";
			map.put("levl","%"+levl+"%");
		}
		jpql+=" order by rn.id";
		return reRoleRepos.serPage(pageable,map,jpql);
	}
	
	public List<Role> serchAll(){
	 return (List<Role>) reRoleRepos.findAll();
	}
	
	public void add(Role role) {
		reRoleRepos.save(role);
	}

	public void deltRole(int id){
		/*reRoleRepos.deltRole(id);*/
		reRoleRepos.delteRole(id);
	}
	
	public void deletAll() {
		reRoleRepos.deltAll();
	}
	
	public Role serchRole(int id){
		return reRoleRepos.selcByid(id);
	}
	
	public Role serch(int id) {
		return reRoleRepos.serch(id);
	}
}
