package com.fjt.control;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.pojo.User;
import com.fjt.service.UserService;
//�����������
@Controller
public class MainControl {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("logMain")
	public String logMain(@RequestParam Map<String, String > map)
	{
		String username=map.get("user");
		String password=map.get("password");
//		String username=map.get("j_username");
//		String password=map.get("j_password"); 
		System.out.println("username="+username);
		
	    User user=userService.findUser(username);
	    System.out.println("us="+user);
		if(user==null){
			return "index";
		}
		
		return "main";
	}
	
	
 
	//����������
	@RequestMapping("retrn")
	public String retrn(){
		return "index";
	}
}
