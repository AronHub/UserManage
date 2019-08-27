package com.fjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.bussiness.ProjctBiz;
import com.fjt.pojo.Project;
import com.fjt.service.ProjectService;
import com.fjt.util.ProjectRrepUP;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjctBiz projctBiz;

	@Override
	public void reportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		ProjectRrepUP projectRrepUp = new ProjectRrepUP();
		List<Project> lists = projectRrepUp.check(file);
		if (lists != null) {
			for (Project project : lists) {
				projctBiz.add(project);
			}
		}

	}

	@Override
	public Map<String, Object> serch(Pageable pageble,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		if (pageble == null) {
			return null;
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Page<Project> page = projctBiz.serch(pageble, map);
		if (page != null) {
			List<Project> list = page.getContent();
			int pageSize = page.getTotalPages();
			resultMap.put("content", list);
			resultMap.put("rows", pageSize);

			return resultMap;
		}

		return null;

	}

	/* (非 Javadoc)
	 * 
	 * 
	 * @param projectName
	 * @param projectId
	 * @return
	 * @see com.fjt.service.ProjectService#getProjectByNameAndID(java.lang.String, java.lang.String)
	 */

	@Override
	public List<Project> getProjectByNameAndID(String projectName,
			String projectId) {
		// TODO Auto-generated method stub
		return projctBiz.getProjectByNameAndID(projectName, projectId);
	}

}
