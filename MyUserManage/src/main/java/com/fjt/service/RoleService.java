package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Role;

public interface RoleService {
	/**
	 *
	     * @Title:  报表上传
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param file
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	public String reportUpload(MultipartFile file);

	/**
	 * 
	     * @Title: 根据角色名称和级别进行查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param rolename
	     * @param @param levl
	     * @param @return 参数
	     * @author fujiantao
	     * @return List<Role> 返回类型
	     * @throws
	 */
	List<Role> getRoleByNameAndLevl(String rolename, String levl);

	//查询所有
	public List<Role> serchAll();

	/**
	 * 
	     * @Title: 根据id查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param id
	     * @param @return 参数
	     * @author fujiantao
	     * @return Role 返回类型
	     * @throws
	 */
	public Role getRoleByID(int id);

	/**
	 * 
	     * @Title: 添加
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param role 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	public void add(Role role);

	/**
	 * 
	     * @Title: 删除
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param id 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	public void deltRole(int id);

	/**
	 * 
	     * @Title: 删除所有
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param  参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	public void deletAll();

	/**
	 * 
	     * @Title: 查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param pageable
	     * @param @param rolename
	     * @param @param levl
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	public Map<String, Object> findRoleInfo(Pageable pageable, String rolename,
			String levl);

	//关联表查询
	public Role serch(int id);
}
