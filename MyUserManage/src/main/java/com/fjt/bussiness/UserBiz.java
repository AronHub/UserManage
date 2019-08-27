package com.fjt.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fjt.pojo.User;
import com.fjt.repositery.UserRepos;

@Component
public class UserBiz {
	@Autowired
	private UserRepos userRepos;

	public User findUser(String username, String passwd) {
		// TODO Auto-generated method stub
		return userRepos.findUser(username, passwd);
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		userRepos.save(user);
	}

	public void count() {
		// TODO Auto-generated method stub
		userRepos.contAll();
	}

	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userRepos.findAllUser();
	}

	public Page<User> findPage(Pageable pageable) {
		// TODO Auto-generated method stub

		Page<User> page = userRepos.findAll(pageable);

		return page;
	}

	public Page<User> getUserInfo(Pageable pageable, String userName,
			String telp) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jpql = "select us  from User us where 1=1 ";
		if (null != userName && !"".equals(userName)) {
			jpql += "and us.name like :userName";
			map.put("userName", "%" + userName + "%");
		}
		if (null != telp && !"".equals(telp)) {
			jpql += " and us.telep like :telp";
			map.put("telp", "%" + telp + "%");
		}
		Page<User> page = userRepos.HiberPage(map, pageable, jpql);
		return page;
	}

	public void delet(int id) {
		userRepos.delete(id);
	}

	public User finOne(int id) {
		// TODO Auto-generated method stub
		return userRepos.findOne(id);
	}

	public List<User> getUserBynameAndTelp(String userName, String telep) {
		return userRepos.getUserBynameAndTelp(userName, telep);
	}
}
