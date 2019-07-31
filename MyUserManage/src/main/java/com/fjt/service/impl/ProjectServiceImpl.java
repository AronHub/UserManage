package com.fjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.buszi.ProjctBizz;
import com.fjt.pojo.Project;
import com.fjt.service.ProjectService;
import com.fjt.util.ProjectRrepUP;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjctBizz projectBizz; 
	
	@Override
	public void reportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		ProjectRrepUP projectRrepUp=new ProjectRrepUP();
		List<Project> lists=projectRrepUp.check(file);
		if(lists!=null){
			for(Project project:lists){
				//this.add(project);
			}
		}
		
	}

	@Override
	public Map<String, Object> serch(Pageable pageb, Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(pageb==null){
			return null;
		}else{
			Map<String, Object> resultMap=new HashMap<String, Object>();
			Page<Project> page=projectBizz.serch(pageb,map);
			if(page!=null){
				List<Project> list=page.getContent();
				int pageSize=page.getTotalPages();
				resultMap.put("content", list);
				resultMap.put("rows", pageSize);
				
				return resultMap;
			}else{
				return null;
			}
			
			
		}
		
	}

}
