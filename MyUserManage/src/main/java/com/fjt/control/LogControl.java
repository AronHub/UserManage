package com.fjt.control;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjt.pojo.User;
import com.fjt.service.UserService;
//访问登录控制器
@Controller
public class LogControl{

	@Autowired
	private UserService userService;
	
	//测试自定义方法的接口
	@RequestMapping("autorep")
	public String autoReposity(){
		userService.count();
		return "success";
	}
	
	//访问index.jsp界面
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	

	
	
	
}
