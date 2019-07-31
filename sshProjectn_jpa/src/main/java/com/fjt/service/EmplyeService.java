package com.fjt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Emplye;

public interface EmplyeService {
	
	//分页查询
	public Map<String, Object> serch(Pageable pageable,String empName,String email);
	
	//查询所有
	public List<Emplye> empList();
	
	//根据id查询
	public Emplye findByid(int id);
	
	//添加
	public void add(Emplye emp);
	
	//删除
	public void delt(int id);
	
	//删除所有
	public void deltAll();
	
	//上传报表
	public String exportUp(MultipartFile file);
	
}
