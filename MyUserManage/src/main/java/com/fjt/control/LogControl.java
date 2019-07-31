package com.fjt.control;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjt.pojo.User;
import com.fjt.service.UserService;
//���ʵ�¼������
@Controller
public class LogControl{

	@Autowired
	private UserService userService;
	
	//�����Զ��巽���Ľӿ�
	@RequestMapping("autorep")
	public String autoReposity(){
		userService.count();
		return "success";
	}
	
	//����index.jsp����
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	

	
	
	
}
