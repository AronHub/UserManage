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

import com.fjt.bussiness.ProjctBiz;
import com.fjt.pojo.Project;
import com.fjt.repositery.custom.ProjRrepCustom;

public class ProjRrepImpl implements ProjRrepCustom {

	@PersistenceContext
	private EntityManager entitymange;

	@Autowired
	private ProjctBiz projctBiz;

	@Override
	public Page<Project> serch(Pageable pageb, Map<String, Object> map,
			String hql) {
		// TODO Auto-generated method stub
		Query query = entitymange.createQuery(hql);
		query.setFirstResult(pageb.getOffset());
		query.setMaxResults(pageb.getPageSize());

		for (Map.Entry<String, Object> enty : map.entrySet()) {
			String key = enty.getKey();
			Object value = enty.getValue();
			query.setParameter(key, value);
		}

		List<Project> list = query.getResultList();
		int total = projctBiz.serchAll().size();
		Page<Project> pages = new PageImpl<Project>(list, pageb, total);

		return pages;
	}

}
