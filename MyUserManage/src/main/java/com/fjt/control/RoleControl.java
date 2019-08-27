package com.fjt.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fjt.pojo.Role;
import com.fjt.service.RoleService;
import com.fjt.util.PageUtil;

/**
 * 
     * @ClassName: 角色管理控制器
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月15日
     *
 */
@Controller
@RequestMapping("role")
public class RoleControl {
	private Logger logger = LoggerFactory.getLogger(RoleControl.class);

	@Autowired
	private RoleService roleService;

	/**
	 * 
	     * @Title: 角色管理界面的访问
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return ModelAndView 返回类型
	     * @throws
	 */
	@RequestMapping("/goRoleView")
	public ModelAndView goRoleView() {
		String view = "RoleInfo";
		ModelAndView modelAndView = new ModelAndView(view);
		return modelAndView;
	}

	/**
	 * 
	     * @Title: 报表上传
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param file 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	@RequestMapping("reportUp")
	@ResponseBody
	public void reportUpload(@RequestParam("file") MultipartFile file) {
		logger.info("报表上传");
		String result = roleService.reportUpload(file);
	}

	/**
	 * 
	     * @Title: 报表下载
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @param response 参数
	     * @author fujiantao
	     * @return void 返回类型
	     * @throws
	 */
	@RequestMapping("reportDownLoad")
	@ResponseBody
	public void reportDownLoad(HttpServletRequest request,
			HttpServletResponse response) {
		//创建workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建sheet
		XSSFSheet sheet = workbook.createSheet("角色表");
		//创建row
		XSSFRow row = sheet.createRow(0);
		//创建cell
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("id");
		cell = row.createCell(1);
		cell.setCellValue("角色名称");
		cell = row.createCell(2);
		cell.setCellValue("级别");

		//下载
		try (OutputStream out = response.getOutputStream()) {
			String rolename = request.getParameter("rolename");
			if (rolename != null) {
				rolename = URLDecoder.decode(rolename, "utf-8");
			}
			String levl = request.getParameter("levl");
			List<Role> roleList = roleService.getRoleByNameAndLevl(rolename,
					levl);
			//循环添加内容
			if (roleList != null) {
				for (int i = 0; i < roleList.size(); i++) {
					Role role = roleList.get(i);
					row = sheet.createRow(i);
					cell = row.createCell(0);
					cell.setCellValue(role.getId());
					cell = row.createCell(1);
					cell.setCellValue(role.getName());
					cell = row.createCell(2);
					cell.setCellValue(role.getLevl());
				}

			}

			String fileName = "角色表.xlsx";
			response.setContentType("application/x-msdownload");
			//获取浏览器的
			String clienInfo = request.getHeader("User-agent");
			if (clienInfo != null && clienInfo.indexOf("MSIE") > 0) {
				if (clienInfo.indexOf("MSIE 6") > 0
						|| clienInfo.indexOf("MSIE 5") > 0) {

					fileName = new String(fileName.getBytes("UTF-8"),
							"ISO-8859-1");
				} else {
					fileName = URLEncoder.encode(fileName, "utf-8");
				}

			} else if (clienInfo != null && clienInfo.indexOf("Trident") > 0) {

				fileName = URLEncoder.encode(fileName, "utf-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + fileName);
			workbook.write(out);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("报表下载出错!", e.getMessage());
		}

	}

	/**
	 * 
	     * @Title: 查询、分页
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @param map
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	@RequestMapping("/findRoleInfo")
	@ResponseBody
	public Map<String, Object> findRoleInfo(HttpServletRequest request,
			@RequestParam Map<String, Object> params) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			String rolename = (String) params.get("rolename");
			String levl = (String) params.get("levl");
			if (rolename != null) {
				rolename = new String(rolename.getBytes("ISO-8859-1"), "UTF-8");
			}
			if (levl != null) {
				levl = new String(levl.getBytes("ISO-8859-1"), "UTF-8");
			}
			Pageable pageable = PageUtil.getPageAble(params);

			resultMap = roleService.findRoleInfo(pageable, rolename, levl);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * 
	     * @Title: 添加
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param role
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("addRole")
	@ResponseBody
	public String addRole(Role role) {
		roleService.add(role);
		return "success";

	}

	/**
	 * 
	     * @Title: 删除
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public String deleteRole(HttpServletRequest request) {
		String rid = request.getParameter("id");
		//一个记录
		if (!rid.contains(",")) {
			roleService.deltRole(Integer.parseInt(rid));
		} else {
			//多条记录
			for (String id : rid.split(",")) {
				roleService.deltRole(Integer.parseInt(id));
			}
		}

		return "success";

	}

	/**
	 * 
	     * @Title: 删除所有
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("delAll")
	@ResponseBody
	public String delAll() {
		roleService.deletAll();
		return "success";
	}

	/**
	 * 
	     * @Title: 根据rid查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	@RequestMapping("getRoleByID")
	@ResponseBody
	public Map<String, Object> getRoleByID(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String id = request.getParameter("id");
		Role role = roleService.getRoleByID(Integer.parseInt(id));
		result.put("content", role);
		return result;

	}

	/**
	 * 
	     * @Title: 修改
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param role
	     * @param @return 参数
	     * @author fujiantao
	     * @return String 返回类型
	     * @throws
	 */
	@RequestMapping("updatRole")
	@ResponseBody
	public String updatRole(Role role) {
		try {
			String levl = role.getLevl();
			if (null != levl) {
				levl = new String(levl.getBytes("ISO-8859-1"), "UTF-8");
			}
			String name = role.getName();
			if (null != name) {
				name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			}

			role.setLevl(levl);
			role.setName(name);

			roleService.add(role);
			return "success";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("修改出错!", e.getMessage());
			return "error";
		}

	}

}
