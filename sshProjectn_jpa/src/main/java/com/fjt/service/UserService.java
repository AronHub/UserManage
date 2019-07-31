package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.User;


//用户表的业务层
public interface UserService {
	//报表上传
	public void reportUp(MultipartFile file);
	//根据用户名查询
	public User findUser(String  username);
	
	//根据id查询用户
	public User finOne(int id);
	
	//分页查询(使用系统支持的查询方法)
	public Map<String,Object> findPage(Pageable pageable);
	
	//自定义的分页查询实现
	public Map<String,Object> findPage2(Pageable pageable,String userName,String telp);
	
	//查询所有用户
	public List<User> findAllUser();
	
	//添加
	public void save(User user);
	
	//删除
	public void delet(int id);
	
	//测试自定义方法接口
	public void count();
}
