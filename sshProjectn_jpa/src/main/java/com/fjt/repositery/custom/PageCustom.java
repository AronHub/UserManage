package com.fjt.repositery.custom;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjt.pojo.User;

/**
 * ������Զ��巽���Ľӿ�
 * ��Щ�����Ĺ�����Ҫ�Լ�ȥʵ�֣�������Ҫ�ѷ�����ȡ�����������Զ��巽���Ľӿ��ϣ�
 * Ȼ���ڴ���һ��ʵ���ࣨUserReposImpl������UserReposImplʵ���Լ���Ҫʵ�ֵĹ���
 * @author posdev
 *
 */
//�Զ����ҳ��ѯʵ��
public interface PageCustom {
	
	public Page<User> HiberPage(Map<String,Object> map,Pageable pageable,String jpql);

}
