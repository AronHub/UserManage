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
     * @ClassName: ��ɫ���������
     * @Description: TODO(������һ�仰��������������)
     * @author fujiantao
     * @date 2019��8��15��
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
	     * @Title: ��ɫ�������ķ���
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @return ����
	     * @author fujiantao
	     * @return ModelAndView ��������
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
	     * @Title: �����ϴ�
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param file ����
	     * @author fujiantao
	     * @return void ��������
	     * @throws
	 */
	@RequestMapping("reportUp")
	@ResponseBody
	public void reportUpload(@RequestParam("file") MultipartFile file) {
		logger.info("�����ϴ�");
		String result = roleService.reportUpload(file);
	}

	/**
	 * 
	     * @Title: ��������
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param request
	     * @param @param response ����
	     * @author fujiantao
	     * @return void ��������
	     * @throws
	 */
	@RequestMapping("reportDownLoad")
	@ResponseBody
	public void reportDownLoad(HttpServletRequest request,
			HttpServletResponse response) {
		//����workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		//����sheet
		XSSFSheet sheet = workbook.createSheet("��ɫ��");
		//����row
		XSSFRow row = sheet.createRow(0);
		//����cell
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("id");
		cell = row.createCell(1);
		cell.setCellValue("��ɫ����");
		cell = row.createCell(2);
		cell.setCellValue("����");

		//����
		try (OutputStream out = response.getOutputStream()) {
			String rolename = request.getParameter("rolename");
			if (rolename != null) {
				rolename = URLDecoder.decode(rolename, "utf-8");
			}
			String levl = request.getParameter("levl");
			List<Role> roleList = roleService.getRoleByNameAndLevl(rolename,
					levl);
			//ѭ���������
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

			String fileName = "��ɫ��.xlsx";
			response.setContentType("application/x-msdownload");
			//��ȡ�������
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
			logger.error("�������س���!", e.getMessage());
		}

	}

	/**
	 * 
	     * @Title: ��ѯ����ҳ
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param request
	     * @param @param map
	     * @param @return ����
	     * @author fujiantao
	     * @return Map<String,Object> ��������
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
	     * @Title: ���
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param role
	     * @param @return ����
	     * @author fujiantao
	     * @return String ��������
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
	     * @Title: ɾ��
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param request
	     * @param @return ����
	     * @author fujiantao
	     * @return String ��������
	     * @throws
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public String deleteRole(HttpServletRequest request) {
		String rid = request.getParameter("id");
		//һ����¼
		if (!rid.contains(",")) {
			roleService.deltRole(Integer.parseInt(rid));
		} else {
			//������¼
			for (String id : rid.split(",")) {
				roleService.deltRole(Integer.parseInt(id));
			}
		}

		return "success";

	}

	/**
	 * 
	     * @Title: ɾ������
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @return ����
	     * @author fujiantao
	     * @return String ��������
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
	     * @Title: ����rid��ѯ
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param request
	     * @param @return ����
	     * @author fujiantao
	     * @return Map<String,Object> ��������
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
	     * @Title: �޸�
	     * @Description: TODO(������һ�仰�����������������)
	     * @param @param role
	     * @param @return ����
	     * @author fujiantao
	     * @return String ��������
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
			logger.error("�޸ĳ���!", e.getMessage());
			return "error";
		}

	}

}
