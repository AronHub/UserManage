package com.fjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.buszi.RoleBuzzi;
import com.fjt.pojo.Role;
import com.fjt.service.RoleService;
import com.fjt.util.ReporUp;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleBuzzi roleBuzzi;

	@Override
	public Map<String, Object> serfind(Pageable pageable, String rolename,
			String levl) {
		// TODO Auto-generated method stub
	  
	   Page<Role> page=roleBuzzi.serfind(pageable,rolename,levl);
	   System.out.println("TotalPages="+page.getTotalPages());
	  
	   Map<String, Object> result=new HashMap<String, Object>();
	   List<Role> roles=this.serchAll();
	   result.put("content", page.getContent());
	   result.put("pagecount", page.getTotalPages());
	   
		return result;
	}

	@Override
	public List<Role> serchAll() {
		// TODO Auto-generated method stub
		return roleBuzzi.serchAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void add(Role role) {
		// TODO Auto-generated method stub
		 roleBuzzi.add(role);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deltRole(int id) {
		// TODO Auto-generated method stub
		roleBuzzi.deltRole(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deletAll() {
		// TODO Auto-generated method stub
		roleBuzzi.deletAll();
	}

	@Override
	public Role serchRole(int id) {
		// TODO Auto-generated method stub
		return roleBuzzi.serchRole(id);
	}

	@Override
	public String reportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		String result="";
		ReporUp repor=new ReporUp();
		List<Role> roles=repor.judgeFile(file);
		System.out.println("rol="+roles);
		if(roles!=null){
			result+="上传成功";
			for(Role role:roles){
				this.add(role);
			}
		}else{
			result+="上传失败";
		}

		return result;
	}

	@Override
	public Role serch(int id) {
		// TODO Auto-generated method stub
		return roleBuzzi.serch(id);
	}


}
