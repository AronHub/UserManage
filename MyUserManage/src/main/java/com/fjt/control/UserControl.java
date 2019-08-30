package com.fjt.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.pojo.User;
import com.fjt.service.UserService;
import com.fjt.util.PageUtil;

/**
 * 
     * @ClassName: 用户控制器
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月1日
     *
 */
@Controller
public class UserControl {
	private Logger logger = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private UserService userservice;

	@Autowired
	private ServletContext servletContext;

	/**
	 * 
	     * @Title: 访问用户管理界面
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return ModelAndView 返回类型
	     * @throws
	 */
	@RequestMapping("goUserView")
	public ModelAndView goUserView() {
		String view = "UserMange";
		ModelAndView modelAndView = new ModelAndView(view);
		return modelAndView;
	}

	/**
	 * 
	 * 
	     * @Title: 查询/分页实现
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @param recevis
	     * @param @return
	     * @param @throws UnsupportedEncodingException 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public Map<String, Object> getUserInfo(HttpServletRequest request,
			@RequestParam Map<String, Object> params) {
		//解决中文乱码问题。
		//String username=new String( request.getParameter("usernames").getBytes("ISO-8859-1"),"utf-8");

		//获取查询信息
		String username = (String) params.get("usernames");
		String telep = (String) params.get("telep");

		Pageable pageable = PageUtil.getPageAble(params);

		Map<String, Object> resultMap = userservice.getUserInfo(pageable,
				username, telep);

		return resultMap;
	}

	/**
	 * 
	 * @Title: 报表上传
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param file
	 * @param @param request 参数
	 * @author fujiantao
	 * @return void 返回类型
	 * @throws
	 */

	@RequestMapping("reportUpload")
	public void reportUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		userservice.reportUpload(file);

	}

	/**
	 * 
	     * @Title: 报表导出
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param response
	     * @param @param request 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	@RequestMapping("reportDownLoad")
	public void reportDownLoad(HttpServletResponse response,
			HttpServletRequest request) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("用户信息");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("用户ID");
		cell = row.createCell(1);
		cell.setCellValue("用户名");
		cell = row.createCell(2);
		cell.setCellValue("密码");
		cell = row.createCell(3);
		cell.setCellValue("电话");
		cell = row.createCell(4);
		cell.setCellValue("地址");

		List<User> users = new ArrayList<User>();
		logger.info("报表导出开始。。。。");
		//下载
		try (OutputStream out = response.getOutputStream()) {
			//获取参数
			String userName = request.getParameter("usernames");
			//使用解码，解决中文乱码问题。
			if (userName != null) {
				userName = URLDecoder.decode(userName, "utf-8");
			}
			String telep = request.getParameter("telep");

			users = userservice.getUserBynameAndTelp(userName, telep);
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(user.getId());
				row.createCell(1).setCellValue(user.getName());
				row.createCell(2).setCellValue(user.getPssword());
				row.createCell(3).setCellValue(user.getTelep());
				row.createCell(4).setCellValue(user.getAddr());
			}

			String fileName = "用户信息表.xlsx";
			response.setContentType("application/x-msdownload");
			//获取浏览器内核信息
			String clientInfo = request.getHeader("User-agent");
			//判断内核
			if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {
				if (clientInfo.indexOf("MSIE 5") > 0
						|| clientInfo.indexOf("MSIE 6") > 0) {
					fileName = new String(fileName.getBytes("UTF-8"),
							"ISO-8859-1");
				} else {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
			} else if (clientInfo != null
					&& clientInfo.indexOf("Trident") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setHeader("Content-Disposition",
					"attachment;fileName=" + fileName);
			workbook.write(out);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("导出出错!", e.getMessage());
		}

	}

	//	//添加用户:方法一
	//	@RequestMapping("adduser")
	//	@ResponseBody
	//	public String  add(@RequestParam Map<String,String> map) throws UnsupportedEncodingException{
	//		String username=map.get("name");
	//		String passw=map.get("pssword");
	//		String telep=map.get("telep");
	//		String addr=new String(map.get("addr").getBytes("ISO-8859-1"),"utf-8");
	//		User user=new User();
	//		user.setName(username);
	//		user.setPssword(passw);
	//		user.setTelep(telep);
	//		user.setAddr(addr);
	//		userservice.save(user);
	//		
	//		return "success";
	//	}

	/**
	 * 
	     * @Title: 添加用户  :方法二
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param user
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("adduser")
	@ResponseBody
	//如果按照pojo对象来映射，请求参数必须要和pojo对象的属性名一致。
	public String addUser(User user) {
		try {
			String address = user.getAddr();
			if (null != address) {
				address = new String(user.getAddr().getBytes("ISO-8859-1"),
						"utf-8");
			}
			user.setAddr(address);
			userservice.save(user);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("保存出错!", e.getMessage());
			return "error";
		}

	}

	/**
	 * 
	     * @Title: 删除用户
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		String userID = request.getParameter("id");
		//删除一个
		if (!userID.contains(",")) {
			userservice.delet(Integer.parseInt(userID));
		} else {
			//删除多个
			String idArray[] = userID.split(",");
			for (String id : idArray) {
				userservice.delet(Integer.parseInt(id));
			}
		}

		return "success";
	}

	/**
	 * 
	     * @Title: 修改-回归数据
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	@RequestMapping("updateReturn")
	@ResponseBody
	public Map<String, Object> updateReturn(HttpServletRequest request) {
		String id = request.getParameter("id");
		User user = userservice.finOne(Integer.parseInt(id));

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("user", user);
		return result;
	}

	//修改操作
	@RequestMapping("updat")
	@ResponseBody
	public void updat(User user) {
		User usr = userservice.finOne(user.getId());
		usr.setId(user.getId());
		usr.setAddr(user.getAddr());
		usr.setName(user.getName());
		usr.setPssword(user.getPssword());
		usr.setTelep(user.getTelep());
		userservice.save(usr);
	}

	/**
	 * 
	     * @Title: 下载资源(可以下载mp3,jpg,等资源文件)
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param  参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	@ResponseBody
	@RequestMapping("/downResource")
	public void downResource(HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "dream.mp3";
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + fileName);
		//1.获取要下载的文件的全路径
		String path = servletContext.getRealPath("/imgs/dream.mp3");
		try (OutputStream os = response.getOutputStream();
				FileInputStream fis = new FileInputStream(path);) {

			//做一个缓存字节数组
			byte[] buff = new byte[1024];
			int len = 0;//表示实际每次读取了多少字节。
			while ((len = fis.read(buff)) > 0) {
				os.write(buff, 0, len);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
