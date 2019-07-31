package com.fjt.buszi;

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
public class ProjctBizz {
	@Autowired
	private ProjRrep projRrep;
	
	public Page<Project> serch(Pageable pageb,Map<String, Object> map){
		String hql="select pt from Project pt where 1=1 "; 
		HashMap<String, Object> mpt=new HashMap<String, Object>();
		if(map.get("projeName")!=null&&!map.get("projeName").equals("")){
			 hql+=" and pt.name like :projeName ";
			 mpt.put("projeName","%"+map.get("projeName").toString()+"%");
		}
		
		if(map.get("projeNo")!=null&&!map.get("projeNo").equals("")){
			 hql+="and pt.id=:projeNo";
			 mpt.put("projeNo", map.get("projeNo").toString());
		}
		Page<Project> page=projRrep.serch(pageb,mpt,hql);
		
		return page;
		
	}
	
	public List<Project> serchAll(){
	    return 	(List<Project>) projRrep.findAll();
	}

}
