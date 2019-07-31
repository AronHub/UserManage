package com.fjt.buszi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fjt.pojo.Emplye;
import com.fjt.repositery.EmplyRepos;

@Component
public class EmplyBuzii {
	@Autowired
	private EmplyRepos emplyRepos;
	
	public Page<Emplye> serch(Pageable pageable,String empName,String email) {
		Map<String , Object> map =new HashMap<String, Object>();
		String jpql="select em from Emplye em where 1=1 ";
		if(empName!=null&&!empName.equals("")){
			jpql+="and name like :empName ";
			map.put("empName","%"+empName+"%");
		}
		if(email!=null&&!email.equals("")){
			jpql+="and email like :email ";
			map.put("email","%"+email+"%");
		}
		jpql+="order by em.id";
		Page<Emplye> page=emplyRepos.pageSerch(pageable, map, jpql);
		
		return page;
	}
	
	public List<Emplye> empList() {
		return (List<Emplye>) emplyRepos.findAll();
	}
	
	public void add(Emplye emp) {
		emplyRepos.save(emp); 
	}
	
	public void delt(int id) { 
		emplyRepos.delete(id);
	}
	
	public void delAll(){
		emplyRepos.deltAll();
	}
	
	public Emplye findByid(int id){
		
	   return emplyRepos.serchByid(id);
	}
}
