package com.fjt.repositery.custom;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjt.pojo.User;

/**
 * 这边是自定义方法的接口
 * 有些方法的功能需要自己去实现，所以需要把方法抽取出来，放在自定义方法的接口上，
 * 然后在创建一个实现类（UserReposImpl），在UserReposImpl实现自己想要实现的功能
 * @author posdev
 *
 */
//自定义分页查询实现
public interface PageCustom {

	public Page<User> HiberPage(Map<String, Object> map, Pageable pageable,
			String jpql);

}
