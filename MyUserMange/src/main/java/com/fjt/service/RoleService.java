package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Role;

public interface RoleService {
	//报表上传
	public String reportUp(MultipartFile file);
	
	//查询
	public Map<String,Object> serfind(Pageable pageable,String rolename,String levl);
	
	//查询所有
	public List<Role> serchAll();
	
	//根据id查询
	public Role serchRole(int id);
	
	//添加
	public void add(Role role);

	//删除
	public void deltRole(int id);
	
	//删除所有
	public void deletAll();
	
	//关联表查询
	public Role serch(int id);
}
