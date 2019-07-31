package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Role;

public interface RoleService {
	//�����ϴ�
	public String reportUp(MultipartFile file);
	
	//��ѯ
	public Map<String,Object> serfind(Pageable pageable,String rolename,String levl);
	
	//��ѯ����
	public List<Role> serchAll();
	
	//����id��ѯ
	public Role serchRole(int id);
	
	//���
	public void add(Role role);

	//ɾ��
	public void deltRole(int id);
	
	//ɾ������
	public void deletAll();
	
	//�������ѯ
	public Role serch(int id);
}
