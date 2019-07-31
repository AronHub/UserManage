package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.User;


//�û����ҵ���
public interface UserService {
	//�����ϴ�
	public void reportUp(MultipartFile file);
	//�����û�����ѯ
	public User findUser(String  username);
	
	//����id��ѯ�û�
	public User finOne(int id);
	
	//��ҳ��ѯ(ʹ��ϵͳ֧�ֵĲ�ѯ����)
	public Map<String,Object> findPage(Pageable pageable);
	
	//�Զ���ķ�ҳ��ѯʵ��
	public Map<String,Object> findPage2(Pageable pageable,String userName,String telp);
	
	//��ѯ�����û�
	public List<User> findAllUser();
	
	//���
	public void save(User user);
	
	//ɾ��
	public void delet(int id);
	
	//�����Զ��巽���ӿ�
	public void count();
}
