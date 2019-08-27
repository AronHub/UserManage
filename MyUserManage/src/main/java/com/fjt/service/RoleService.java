package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Role;

public interface RoleService {
	/**
	 *
	     * @Title:  �����ϴ�
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param file
	     * @param @return ����
	     * @author fujiantao
	     * @return String ��������
	     * @throws
	 */
	public String reportUpload(MultipartFile file);

	/**
	 * 
	     * @Title: ���ݽ�ɫ���ƺͼ�����в�ѯ
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param rolename
	     * @param @param levl
	     * @param @return ����
	     * @author fujiantao
	     * @return List<Role> ��������
	     * @throws
	 */
	List<Role> getRoleByNameAndLevl(String rolename, String levl);

	//��ѯ����
	public List<Role> serchAll();

	/**
	 * 
	     * @Title: ����id��ѯ
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param id
	     * @param @return ����
	     * @author fujiantao
	     * @return Role ��������
	     * @throws
	 */
	public Role getRoleByID(int id);

	/**
	 * 
	     * @Title: ���
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param role ����
	     * @author fujiantao
	     * @return void ��������
	     * @throws
	 */
	public void add(Role role);

	/**
	 * 
	     * @Title: ɾ��
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param id ����
	     * @author fujiantao
	     * @return void ��������
	     * @throws
	 */
	public void deltRole(int id);

	/**
	 * 
	     * @Title: ɾ������
	     * @Description: TODO(������һ�仰�����������������)
	     * @param  ����
	     * @author fujiantao
	     * @return void ��������
	     * @throws
	 */
	public void deletAll();

	/**
	 * 
	     * @Title: ��ѯ
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param pageable
	     * @param @param rolename
	     * @param @param levl
	     * @param @return ����
	     * @author fujiantao
	     * @return Map<String,Object> ��������
	     * @throws
	 */
	public Map<String, Object> findRoleInfo(Pageable pageable, String rolename,
			String levl);

	//�������ѯ
	public Role serch(int id);
}
