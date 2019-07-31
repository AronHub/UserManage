package com.fjt.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.pojo.Role;
import com.fjt.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleControl {
	@Autowired
	private RoleService roleService;
    private Logger logger=LoggerFactory.getLogger(RoleControl.class);
    //角色管理界面的访问
	@RequestMapping("/roleinfo")
	public ModelAndView runRoleinfo(){
		String view="RoleInfo";
		ModelAndView modelAndView=new ModelAndView(view);
		return modelAndView;
	}
	//报表上传
	@RequestMapping("reportUp")
	@ResponseBody
	public void reportUp(@RequestParam("file") MultipartFile file){
		 System.out.println("file="+file);
		 logger.info("报表上传");
	     String result=roleService.reportUp(file);
	}
	
	
	//报表下载
	@RequestMapping("reportDowmn")
	@ResponseBody
	public void reportDowmn(HttpServletRequest request, HttpServletResponse response){
		 //创建workbook
		XSSFWorkbook workbook=new XSSFWorkbook();
		//创建sheet
		XSSFSheet sheet=workbook.createSheet("角色表");
		//创建row
	    XSSFRow row=sheet.createRow(0);
		//创建cell
		XSSFCell cell=row.createCell(0);
		cell.setCellValue("id");
		cell=row.createCell(1);
		cell.setCellValue("角色名称");
		cell=row.createCell(2);
		cell.setCellValue("级别");
		List<Role> roleList=roleService.serchAll();
	    int size=roleList.size();
		//循环添加内容
		for(int r=1;r<=size;r++){
			Role role=roleList.get(r-1);
			
			row=sheet.createRow(r);
			cell=row.createCell(0);
			cell.setCellValue(role.getId());
			cell=row.createCell(1);
			cell.setCellValue(role.getName());
			cell=row.createCell(2);
			cell.setCellValue(role.getLevl());
		}
		
		//下载
		OutputStream out=null;
		try {
			 out=response.getOutputStream();
			 String fileName="角色表.xlsx";
			 response.setContentType("application/x-msdownload");
			 //获取浏览器的
			 String clienInfo=request.getHeader("User-agent");
			 if(clienInfo!=null&&clienInfo.indexOf("MSIE")>0){
				 if(clienInfo.indexOf("MSIE 6")>0
						 ||clienInfo.indexOf("MSIE 5")>0){
					  
					 fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
				 }else{
					 fileName=URLEncoder.encode(fileName,"utf-8");
				 }
				 
			 }else if(clienInfo.indexOf("Trident")>0){
				 
				 fileName=URLEncoder.encode(fileName,"utf-8");
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
	
	//查询、分页
	@RequestMapping("/serfind")
	@ResponseBody
	public Map<String,Object> serfind(HttpServletRequest request,@RequestParam Map<String,String> map){
		String rolename = map.get("rolename");
		String levl = map.get("levl");
		try {
			if(rolename!=null&&levl!=null){
				rolename = new String(rolename.getBytes("ISO-8859-1"),"UTF-8");
				levl=new String(levl.getBytes("ISO-8859-1"),"UTF-8");
			} 
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("lec="+levl+"rolename="+rolename);
		int pageNow=Integer.parseInt(map.get("pageNow"))-1;
		int pageCount=Integer.parseInt(map.get("pageCount"));
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=new PageRequest(pageNow,pageCount,sort);
		
		Map<String, Object> resultMap=roleService.serfind(pageable,rolename,levl);
		return resultMap;
		
	}
	
	//添加
	@RequestMapping("addRole")
	@ResponseBody
	public String addRole(Role role){
		roleService.add(role);
		return "success";
		
	}
	
	//删除
	@RequestMapping("delRole")
	@ResponseBody
	public String delRole(HttpServletRequest request){
		String id=request.getParameter("id");
		System.out.println("id="+id);
		if(id!=null&&!id.equals("")){
			//一个记录
			int idn;
			if(!id.contains(",")){
			    idn=Integer.parseInt(id);
				roleService.deltRole(idn);
			}else{
				//多条记录
				for(String ids:id.split(",")){
					System.out.println("ids="+ids);
					idn=Integer.parseInt(ids);
					roleService.deltRole(idn);
				}
			}
			
			
		}
		return "success";
		
	}
	
	//删除所有
	@RequestMapping("delAll")
	@ResponseBody
	public String delAll(){
		System.out.println("xx");
		roleService.deletAll();
		return "success";
	}
	
	//根据id查询
	@RequestMapping("SerchByid")
	@ResponseBody
	public Map<String, Object> SerchByid(HttpServletRequest request){
		Map<String, Object> result =new HashMap<String, Object>();
		Role role=null;
		String id=request.getParameter("id");
		if(id!=null&&!id.equals("")){
		    int idn=Integer.parseInt(id);
		  	role=roleService.serchRole(idn);
		}
		result.put("content", role);
		return  result;
		
	}
	
	//修改
	@RequestMapping("updatRole")
	@ResponseBody
	public String updatRole(Role role){
		try {
			String levl=new String(role.getLevl().getBytes("ISO-8859-1"),"UTF-8");
			String name=new String(role.getName().getBytes("ISO-8859-1"),"UTF-8");
			role.setLevl(levl);
			role.setName(name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		roleService.add(role);
		return "success";
	}
	

}
