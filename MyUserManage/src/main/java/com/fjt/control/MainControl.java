package com.fjt.control;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fjt.pojo.User;
import com.fjt.service.UserService;

/**
 * 
     * @ClassName: 主界面控制器
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月1日
     *
 */
@Controller
public class MainControl {
	private Logger logger = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	     * @Title: 跳转到主界面
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param map
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("logMain")
	public String logMain(@RequestParam Map<String, String> map,
			HttpServletRequest request, HttpServletResponse response) {
		//如果前端是以表单的形式发送的请求，那么这边的参数名称就是表单中的name名称
		String username = map.get("user");
		String password = map.get("password");
		String isKeep = map.get("keepName");

		User user = userService.findUser(username, password);
		try {
			if (user == null) {
				request.setAttribute("err", "用户名或密码不正确!");
				//使用cookie保存用户名
				if ("keep".equals(isKeep)) {
					//创建cookie.
					Cookie cookie = new Cookie("userName", user.getName());
					//设置生命周期
					cookie.setMaxAge(3600 * 24 * 2 * 7);
					//下面代码会 回送到浏览器，并且把cookie存放在浏览器的临时文件夹
					response.addCookie(cookie);
				}
				return "Longin";

			} else {
				//使用cookie保存登录用户名
				if ("keep".equals(isKeep)) {
					Cookie cke = new Cookie("userName", user.getName());
					cke.setMaxAge(3600 * 24 * 2 * 7);
					response.addCookie(cke);
				}

				//使用cookie保存上次登录时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = simpleDateFormat.format(new Date());
				//获取cookie
				Cookie[] cookieArray = request.getCookies();
				boolean isExit = false;//是否存在名称为lastTime的cooKie
				if (cookieArray != null) {
					for (Cookie ck : cookieArray) {
						if ("lastTime".equals(ck.getName())) {
							request.setAttribute("lastTime", ck.getValue());
							//注意：cookie内容值如果存在中文就会报错，
							//所以得把它用URLEncoder进行编码.然后从Cookie中取出内容的时候要用URLDecoder进行解码
							ck.setValue(URLEncoder.encode("上次登录时间:", "UTF-8")
									+ date);
							ck.setMaxAge(3600 * 24 * 2 * 7);
							response.addCookie(ck);
							isExit = true;
							break;
						}
					}
				}

				//第一次登录情况
				if (!isExit) {
					//创建cookie
					Cookie cookie = new Cookie("lastTime",
							URLEncoder.encode("上次登录时间:", "UTF-8") + date);
					//设置生命周期，生命周期是以秒为单位
					cookie.setMaxAge(3600 * 24 * 2 * 7);
					//回送cookie到浏览器端（cookie是存放在浏览器的临时文件夹中的）
					response.addCookie(cookie);
					request.setAttribute("lastTime", "欢迎你第一次登录");
				}
				//返回登录用户信息
				request.getSession().setAttribute("user", user);
				return "main";
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("登录出错!", e.getMessage());
			return "Longin";
		}

	}

	/**
	 * 
	     * @Title: 安全退出
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("retnLongin")
	public String retnLongin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();//注销invalidate.
		return "Longin";

	}
}
