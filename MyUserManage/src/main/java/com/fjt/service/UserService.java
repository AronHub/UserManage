package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.User;

public interface UserService {

	/**
	 * 	
	 * @Title: 查询
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param pageable
	 * @param @param userName
	 * @param @param telp
	 * @param @return 参数
	 * @author fujiantao
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	Map<String, Object> getUserInfo(Pageable pageable, String userName,
			String telp);

	/**
	 * 
	     * @Title: 根据用户名和密码查询用户
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param username
	     * @param @param passwd
	     * @param @return 参数
	     * @author fujiantao
	     * @return User 返回类型
	     * @throws
	 */
	User findUser(String username, String passwd);

	/**
	 * 
	     * @Title: 文件上传
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param file 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	void reportUpload(MultipartFile file);

	/**
	 * 
	     * @Title: 保存功能
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param user 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	void save(User user);

	/**
	 * 
	     * @Title: 删除
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param id 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	void delet(int id);

	/**
	 * 
	 * @Title: 根据Id查询数据
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param id
	 * @param @return 参数
	 * @author fujiantao
	 * @return User 返回类型
	 * @throws
	 */
	User finOne(int id);

	/**
	 * 
	     * @Title: 查询所有数据
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return List<User> 返回类型
	     * @throws
	 */
	List<User> findAllUser();

	/**
	 * 
	     * @Title: 根据用户名和电话查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param userName
	     * @param @param telep
	     * @param @return 参数
	     * @author fujiantao
	     * @return List<User> 返回类型
	     * @throws
	 */
	List<User> getUserBynameAndTelp(String userName, String telep);

	Map<String, Object> findPage(Pageable pageable);

	void count();
}
