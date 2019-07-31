package com.fjt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Emplye;

public interface EmplyeService {
	
	//��ҳ��ѯ
	public Map<String, Object> serch(Pageable pageable,String empName,String email);
	
	//��ѯ����
	public List<Emplye> empList();
	
	//����id��ѯ
	public Emplye findByid(int id);
	
	//���
	public void add(Emplye emp);
	
	//ɾ��
	public void delt(int id);
	
	//ɾ������
	public void deltAll();
	
	//�ϴ�����
	public String exportUp(MultipartFile file);
	
}
