package com.fjt.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.search.Direction;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.pojo.Project;
import com.fjt.pojo.Role;
import com.fjt.service.ProjectService;
import com.fjt.service.RoleService;

@Controller
@RequestMapping("projeView")
public class ProjMangeContrl {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProjectService projectService;
    
	//访问界面
	@RequestMapping("visitProjView")
	public ModelAndView visitProjView(){
		ModelAndView model=new ModelAndView("ProjectMange");
		return model;
	}
	
	//查询
	@RequestMapping("serch")
	@ResponseBody
	public Map<String, Object> serch(HttpServletRequest request){
		String pageNow=request.getParameter("pageNow");
		String pageCount=request.getParameter("pageCount");
		String projeName=request.getParameter("projeName");
		String projeNo=request.getParameter("projeNo");
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		
		try {
				if(projeName!=null&&!projeName.equals("")){
					
				   projeName=new String(projeName.getBytes("ISO-8859-1"),"UTF-8");
				
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("projeName", projeName);
		map.put("projeNo", projeNo);
		
		Pageable pageb=null;
		if(pageCount!=null&&!pageCount.equals("")&&(pageNow!=null&&!pageNow.equals(""))){
			int pageCounts=Integer.parseInt(pageCount);
			int pageNows=Integer.parseInt(pageNow)-1;
		    pageb=new PageRequest(pageNows, pageCounts, sort);
		}
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap=projectService.serch(pageb,map);
		
		return resultMap;
		
	}
	
	
	
	//报表上传
	@RequestMapping("fileName")
	@ResponseBody
	public void reportUp(@RequestParam("file")MultipartFile file,HttpServletRequest request){
		//String fileName=file.getOriginalFilename();
		projectService.reportUp(file);
		
		
	}
	
	
	
	//报表下载
	@RequestMapping("reportDown")
	public void reportDown(HttpServletResponse response,HttpServletRequest request){
		
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("项目信息");
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(0);
		cell.setCellValue("项目ID");
		cell=row.createCell(1);
		cell.setCellValue("项目名称");
		cell=row.createCell(2);
		cell.setCellValue("项目经理");
	/*	cell=row.createCell(3);
		cell.setCellValue("测试经理");*/
		
		List<Role> roles=roleService.serchAll();
		if(roles!=null){
			for(int i=0;i<roles.size();i++){
				Role role=roles.get(i);
				row=sheet.createRow(i+1);
				row.createCell(0).setCellValue(role.getId());
				row.createCell(1).setCellValue(role.getName());
				row.createCell(2).setCellValue(role.getLevl());
		    }
		}
		
		//下载
		OutputStream out=null;
		try {
			out=response.getOutputStream();
			response.setContentType("application/x-msdownload");
			String fileName="项目信息表.xlsx";
			String clientInfo=request.getHeader("User-agent");
			if(clientInfo!=null&&clientInfo.indexOf("MSIE")>0){
				if(clientInfo.indexOf("MSIE 5")>0&&clientInfo.indexOf("MSIE 6")>0){
					fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
				}else{
					URLEncoder.encode(fileName,"utf-8");
				}
			}else if(clientInfo.indexOf("Trident")>0){
				URLEncoder.encode(fileName,"utf-8");
			}else{
				fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
			workbook.write(out);
			out.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	       		
		
	}
	
}
