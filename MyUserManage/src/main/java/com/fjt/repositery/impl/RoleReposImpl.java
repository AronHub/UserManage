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

import com.fjt.pojo.Role;
import com.fjt.repositery.custom.RolereportCustom;
import com.fjt.service.RoleService;

public class RoleReposImpl implements RolereportCustom {

	@PersistenceContext
	private EntityManager entitymange;

	@Autowired
	private RoleService roleService;

	@Override
	public Page<Role> findRoleInfo(Pageable pageable, Map<String, Object> map,
			String jpql) {
		// TODO Auto-generated method stub
		Query query = entitymange.createQuery(jpql);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());

		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object objec = entry.getValue();
				query.setParameter(key, objec);

			}
		}

		List<Role> roles = query.getResultList();
		List<Role> list = roleService.serchAll();
		int total = list.size();
		System.out.println("total=" + total);
		Page<Role> page = new PageImpl<Role>(roles, pageable, total);
		return page;
	}

	@Override
	public void delteRole(int id) {
		// TODO Auto-generated method stub
		String jpql = "delete from Role rn where rn.id=:id";
		Query query = entitymange.createQuery(jpql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public void deltAll() {
		// TODO Auto-generated method stub
		String jpql = "delete from Role";
		Query query = entitymange.createQuery(jpql);
		query.executeUpdate();

	}

	@Override
	public Role getRoleByID(int id) {
		// TODO Auto-generated method stub
		String jpql = "select rn from Role rn where rn.id=?1";
		Query query = entitymange.createQuery(jpql);
		query.setParameter(1, id);
		Object obj = query.getSingleResult();
		return (Role) obj;

	}

}
