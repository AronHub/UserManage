package com.fjt.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fjt.service.UserService;

/**
 * 
     * @ClassName: 登录控制器
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月1日
     *
 */
@Controller
public class LogControl {

	@Autowired
	private UserService userService;

	/**
	 * 
	     * @Title: 跳转到登录界面
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("/")
	public String index() {
		return "Longin";
	}

}
