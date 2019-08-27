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

import com.fjt.pojo.Project;
import com.fjt.service.ProjectService;
import com.fjt.util.PageUtil;

/**
 * 
     * @ClassName: 项目管理控制器
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月19日
     *
 */
@Controller
@RequestMapping("projeView")
public class ProjectContrl {
	private Logger logger = LoggerFactory.getLogger(ProjectContrl.class);

	@Autowired
	private ProjectService projectService;

	/**
	 * 
	     * @Title: 访问界面
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @return 参数
	     * @author fujiantao
	     * @return ModelAndView 返回类型
	     * @throws
	 */
	@RequestMapping("GoProjctView")
	public ModelAndView GoProjctView() {
		ModelAndView model = new ModelAndView("project");
		return model;
	}

	/**
	 * 
	     * @Title: 查询
	     * @Description: TODO(这里用一句话描述这个方法的作用)
	     * @param @param request
	     * @param @return 参数
	     * @author fujiantao
	     * @return Map<String,Object> 返回类型
	     * @throws
	 */
	@RequestMapping("getProjectInfo")
	@ResponseBody
	public Map<String, Object> getProjectInfo(HttpServletRequest request,
			@RequestParam Map<String, Object> param) {

		try {
			Pageable pageable = PageUtil.getPageAble(param);
			String projeName = request.getParameter("projeName");
			String projeNo = request.getParameter("projeNo");
			if (projeName != null) {

				projeName = new String(projeName.getBytes("ISO-8859-1"),
						"UTF-8");

			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("projeName", projeName);
			map.put("projeNo", projeNo);
			Map<String, Object> resultMap = projectService.serch(pageable, map);

			return resultMap;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("查询出错！", e.getMessage());
			return null;
		}

	}

	//报表上传
	@RequestMapping("fileName")
	@ResponseBody
	public void reportUp(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		logger.info("报表开始上传");
		projectService.reportUp(file);

	}

	//报表下载
	@RequestMapping("reportDown")
	public void reportDown(HttpServletResponse response,
			HttpServletRequest request) {
		String nsd = "1";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("项目信息");
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("项目ID");
		cell = row.createCell(1);
		cell.setCellValue("项目名称");
		cell = row.createCell(2);
		cell.setCellValue("项目经理");

		//下载
		try (OutputStream out = response.getOutputStream();) {

			String projectName = URLDecoder
					.decode(request.getParameter("projectName"), "utf-8");
			String projectId = request.getParameter("projectId");
			List<Project> projectList = projectService
					.getProjectByNameAndID(projectName, projectId);
			if (projectList != null) {
				for (int i = 0; i < projectList.size(); i++) {
					Project project = projectList.get(i);
					row = sheet.createRow(i + 1);
					row.createCell(0).setCellValue(project.getId());
					row.createCell(1).setCellValue(project.getName());
					row.createCell(2).setCellValue(project.getManager());
				}
			}

			response.setContentType("application/x-msdownload");
			String fileName = "项目信息表.xlsx";
			String clientInfo = request.getHeader("User-agent");
			if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {
				if (clientInfo.indexOf("MSIE 5") > 0
						&& clientInfo.indexOf("MSIE 6") > 0) {
					fileName = new String(fileName.getBytes("UTF-8"),
							"ISO-8859-1");
				} else {
					URLEncoder.encode(fileName, "utf-8");
				}
			} else if (clientInfo != null
					&& clientInfo.indexOf("Trident") > 0) {
				URLEncoder.encode(fileName, "utf-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + fileName);
			workbook.write(out);
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("报表导出失败!", e.getMessage());

		}

	}

}
