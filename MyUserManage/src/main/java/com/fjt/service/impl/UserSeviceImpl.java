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

import com.fjt.bussiness.UserBiz;
import com.fjt.pojo.User;
import com.fjt.service.UserService;
import com.fjt.util.UserReporUp;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserBiz userBiz;

	@Override
	public User findUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return userBiz.findUser(username, passwd);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(User user) {
		// TODO Auto-generated method stub
		userBiz.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void count() {
		// TODO Auto-generated method stub
		userBiz.count();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userBiz.findAllUser();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> findPage(Pageable pageable) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		//使用 了JpaRepository接口提供的分页实现的方法findAll(Pageable pageable)
		Page<User> page = userBiz.findPage(pageable);

		List<User> content = page.getContent();
		//查询的是总的记录数
		//Long total=page.getTotalElements();
		//		//每页有几条记录。
		//		int number=page.getSize();
		//共有几页
		int pagecount = page.getTotalPages();
		map.put("content", content);
		map.put("total", pagecount);
		return map;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Map<String, Object> getUserInfo(Pageable pageable, String userName,
			String telp) {
		// TODO Auto-generated method stub
		Page<User> userList = userBiz.getUserInfo(pageable, userName, telp);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("content", userList.getContent());
		result.put("total", userList.getTotalElements());

		return result;
	}

	@Override
	public void delet(int id) {
		// TODO Auto-generated method stub
		userBiz.delet(id);
	}

	@Override
	public User finOne(int id) {
		// TODO Auto-generated method stub
		return userBiz.finOne(id);
	}

	@Override
	public void reportUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		UserReporUp userReporUp = new UserReporUp();
		List<User> userList = userReporUp.exreport(file);
		if (userList != null) {
			for (User user : userList) {
				this.save(user);
			}
		}

	}

	@Override
	public List<User> getUserBynameAndTelp(String userName, String telep) {
		// TODO Auto-generated method stub
		return userBiz.getUserBynameAndTelp(userName, telep);
	}

}
