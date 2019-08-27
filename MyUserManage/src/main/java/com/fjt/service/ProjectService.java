package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Project;

public interface ProjectService {

	/**
	 * 
	     * @Title: 根据项目名称和ID进行查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param projectName
	     * @param @param projectId
	     * @param @return 参数
	     * @author fujiantao
	     * @return List<Project> 返回类型
	     * @throws
	 */
	List<Project> getProjectByNameAndID(String projectName, String projectId);

	/**
	 * 
	 * @Title: 报表上传
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param file 参数
	 * @author fujiantao
	 * @return void 返回类型
	 * @throws
	 */
	void reportUp(MultipartFile file);

	/**
	 * 
	     * @Title: 查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param pageb
	     * @param @param map
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	Map<String, Object> serch(Pageable pageb, Map<String, String> map);

}
