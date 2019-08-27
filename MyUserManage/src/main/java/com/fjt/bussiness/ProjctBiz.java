package com.fjt.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.fjt.pojo.Project;
import com.fjt.repositery.ProjRrep;

@Component
public class ProjctBiz {
	@Autowired
	private ProjRrep projRrep;

	public Page<Project> serch(Pageable pageb, Map<String, String> map) {
		String hql = "select pt from Project pt where 1=1 ";
		HashMap<String, Object> param = new HashMap<String, Object>();
		if (map.get("projeName") != null && !map.get("projeName").equals("")) {
			hql += " and pt.name like :projeName ";
			param.put("projeName", "%" + map.get("projeName") + "%");
		}

		if (map.get("projeNo") != null && !map.get("projeNo").equals("")) {
			hql += "and pt.id=:projeNo  ";
			//这边注意要把map.get("projeNo")转换成integer，不然会报类型转换异常
			param.put("projeNo", Integer.valueOf(map.get("projeNo")));

		}
		Page<Project> page = projRrep.serch(pageb, param, hql);

		return page;

	}

	public List<Project> serchAll() {
		return (List<Project>) projRrep.findAll();
	}

	public void add(Project project) {
		projRrep.save(project);
	}

	public List<Project> getProjectByNameAndID(String projectName,
			String projectId) {
		Integer ID = Integer.valueOf(projectId);
		return projRrep.getProjectByNameAndID(projectName, ID);
	}

}
