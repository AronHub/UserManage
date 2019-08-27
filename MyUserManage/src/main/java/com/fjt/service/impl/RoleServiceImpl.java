package com.fjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.bussiness.RoleBiz;
import com.fjt.pojo.Role;
import com.fjt.service.RoleService;
import com.fjt.util.ReporUp;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleBiz roleBiz;

	@Override
	public Map<String, Object> findRoleInfo(Pageable pageable, String rolename,
			String levl) {
		// TODO Auto-generated method stub

		Page<Role> page = roleBiz.findRoleInfo(pageable, rolename, levl);

		Map<String, Object> result = new HashMap<String, Object>();
		List<Role> roles = this.serchAll();
		result.put("content", page.getContent());
		result.put("pagecount", page.getTotalPages());

		return result;
	}

	@Override
	public List<Role> serchAll() {
		// TODO Auto-generated method stub
		return roleBiz.serchAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void add(Role role) {
		// TODO Auto-generated method stub
		roleBiz.add(role);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deltRole(int id) {
		// TODO Auto-generated method stub
		roleBiz.deltRole(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletAll() {
		// TODO Auto-generated method stub
		roleBiz.deletAll();
	}

	@Override
	public Role getRoleByID(int id) {
		// TODO Auto-generated method stub
		return roleBiz.getRoleByID(id);
	}

	@Override
	public String reportUpload(MultipartFile file) {
		// TODO Auto-generated method stub
		String result = "";
		ReporUp repor = new ReporUp();
		List<Role> roles = repor.judgeFile(file);
		if (roles != null) {
			result += "上传成功";
			for (Role role : roles) {
				roleBiz.add(role);
			}
		} else {
			result += "上传失败";
		}

		return result;
	}

	@Override
	public Role serch(int id) {
		// TODO Auto-generated method stub
		return roleBiz.serch(id);
	}

	/* (非 Javadoc)
	 * 
	 * 
	 * @param rolename
	 * @param levl
	 * @return
	 * @see com.fjt.service.RoleService#getRoleByNameAndLevl(java.lang.String, java.lang.String)
	 */

	@Override
	public List<Role> getRoleByNameAndLevl(String rolename, String levl) {
		// TODO Auto-generated method stub
		return roleBiz.getRoleByNameAndLevl(rolename, levl);
	}

}
