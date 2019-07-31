package com.fjt.repositery.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fjt.pojo.Emplye;
import com.fjt.repositery.custom.EmplyCustom;
import com.fjt.service.EmplyeService;

public class EmplyReposImpl implements EmplyCustom{
	
	@PersistenceContext
	private EntityManager entitymange;
	@Autowired
	private EmplyeService emplyeService;
	

	@Override
	public Page<Emplye> pageSerch(Pageable pageable, Map<String, Object> map,
			String jpql) {
		// TODO Auto-generated method stub
		Query query=entitymange.createQuery(jpql);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
	
			System.out.println("map="+map);
		
		
		if(map!=null){
			for(Map.Entry<String, Object> entry:map.entrySet()){
			    String key=entry.getKey();
			    Object value=entry.getValue();
			    query.setParameter(key, value);
			}
		}
		
	    List<Emplye> userList=query.getResultList();
	    int total=emplyeService.empList().size();
	    System.out.println("get="+total);
		Page<Emplye> page=new PageImpl<Emplye>(userList, pageable, total);
		return page;
		
	}

}
