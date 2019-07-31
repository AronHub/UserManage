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
import org.springframework.web.multipart.MultipartFile;

import com.fjt.buszi.UserBuzi;
import com.fjt.pojo.User;
import com.fjt.repositery.UserRepos;
import com.fjt.service.UserService;
import com.fjt.util.UserReporUp;


@Service
public class UserSeviceImpl implements UserService{

	@Autowired
	private UserBuzi userbuzi;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User findUser(String  username) {
		// TODO Auto-generated method stub
		return userbuzi.findUser(username);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(User user) {
		// TODO Auto-generated method stub
		userbuzi.save(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void count() {
		// TODO Auto-generated method stub
		userbuzi.count();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userbuzi.findAllUser();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> findPage(Pageable pageable) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map=new HashMap<String, Object>();
        //使用 了JpaRepository接口提供的分页实现的方法findAll(Pageable pageable)
		Page<User> page=userbuzi.findPage(pageable);
		
		List<User> content=page.getContent();
		//查询的是总的记录数
		//Long total=page.getTotalElements();
//		//每页有几条记录。
//		int number=page.getSize();
		//共有几页
		int pagecount=page.getTotalPages();
		map.put("content", content);
		map.put("total", pagecount);
		return map;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> findPage2(Pageable pageable, String userName,
			String telp) {
		// TODO Auto-generated method stub
	    
		Page<User> page=userbuzi.findPage2(pageable, userName, telp);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("content",page.getContent());
		result.put("total", page.getTotalElements());
		
		//System.out.println("total="+page.getTotalElements());
		return result;
	}

	@Override
	public void delet(int id) {
		// TODO Auto-generated method stub
		userbuzi.delet(id);
	}

	@Override
	public User finOne(int id) {
		// TODO Auto-generated method stub
		return userbuzi.finOne(id);
	}

	@Override
	public void reportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		UserReporUp userReporUp=new UserReporUp();
		List<User> users=userReporUp.exreport(file);
	
	    if(users!=null){
	    	
	    	for(User user:users){
	    		this.save(user);
	    	}
	    }
	    
		
	}

	



}
