package com.fjt.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fjt.pojo.Emp_Role;
import com.fjt.pojo.Emplye;
import com.fjt.pojo.Member;
import com.fjt.pojo.Role;
import com.fjt.service.Emp_RoleService;
import com.fjt.service.EmplyeService;
import com.fjt.service.RoleService;
import com.fjt.service.impl.EmplyeServiceImpl;
import com.google.gson.Gson;



@Controller
@RequestMapping("emply")
public class EmplyControl {
	@Autowired
	private EmplyeService emplyeService;
	
	@Autowired
	private Emp_RoleService emp_RoleService;
	
	@Autowired
	private RoleService roleService;
	
	//访问雇员界面
	@RequestMapping("/retrnEmply")
	public ModelAndView retrnEmply(){
		String view="emplyInfo";
		ModelAndView modelandview=new ModelAndView(view);
		return modelandview;
	}
	
	//报表上传
	@RequestMapping("exportUp")
	@ResponseBody
	public void exportUp(HttpServletRequest request,@RequestParam("file")MultipartFile file){
		
		String desc=request.getParameter("desc");
		//String fileName=file.getOriginalFilename();
		String result=emplyeService.exportUp(file);
		
	}
	

	//报表下载
	@RequestMapping("exportDown")
	public void exportDown(HttpServletResponse response,HttpServletRequest request){
		System.out.println("报表下载");
		// TODO Auto-generated method stub
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("雇员信息");
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(0);
		cell.setCellValue("id");
		cell=row.createCell(1);
		cell.setCellValue("雇员名");
		cell=row.createCell(2);
		cell.setCellValue("email");
		cell=row.createCell(3);
		cell.setCellValue("角色");
		cell=row.createCell(4);
		cell.setCellValue("项目");
		
		List<Emplye> emList=emplyeService.empList();
		int size=emList.size();
		System.out.println("sizennn="+size);
		for(int i=0;i<size;i++){
			row=sheet.createRow(i+1);
			Emplye emply=emList.get(i); 
			row.createCell(0).setCellValue(emply.getId());;
			row.createCell(1).setCellValue(emply.getName());
			row.createCell(2).setCellValue(emply.getEmail());
	
		}
		// 第六步：下载excel 
	       OutputStream out = null;    
	       try {        
	           out = response.getOutputStream();    
	           String filename = "我的雇员表.xls";// 文件名
	           //  response.setContentType("multipart/form-data");
	           response.setContentType("application/x-msdownload");
	           //获取到浏览器的内核
	           String clientInfo = request.getHeader("User-agent");
				//System.out.println("neihe="+clientInfo);
				//根据获取的内核来相应的解决中文乱码问题。
				if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {
					if (clientInfo.indexOf("MSIE 6") > 0
							|| clientInfo.indexOf("MSIE 5") > 0) {
						filename = new String(filename.getBytes("UTF-8"),
								"ISO-8859-1");
					} else {
						filename = java.net.URLEncoder.encode(filename, "UTF-8");
					}
				} else if (clientInfo.indexOf("Trident") > 0) {
					filename = java.net.URLEncoder.encode(filename, "UTF-8");
				} else {
					filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
				}
	           
	           
	           response.setHeader("Content-Disposition", "attachment; filename="    
	                                                   + filename);    
	           
	       
	           workbook.write(out);    
	       } catch (Exception e) {    
	           e.printStackTrace();    
	       }
	       

	}
	//删除所有
	@RequestMapping("deltAll")
	@ResponseBody
	public void deltAll(){
		System.out.println("laaaaaaaaaaaaaaaaaaaaaaaa");
		emplyeService.deltAll();
		
	}
	
	//删除
	@RequestMapping("delet")
	@ResponseBody
	public void delet(HttpServletRequest request){
		String id=request.getParameter("id");
		
		if(!id.contains(",")){
			if(id!=null&&!id.equals(""))
			{
				int idn=Integer.parseInt(id);
				
				emplyeService.delt(idn);
			}
			
		}else{
			String ids[]=id.split(",");
			for(String idx:ids){
				if(idx!=null&&!idx.equals("")){
					int idn=Integer.parseInt(idx);
					emplyeService.delt(idn);
				} 
			} 
			
		}
		
	}
	
	//添加
	@RequestMapping("addEmp")
	@ResponseBody
	public void addEmp(HttpServletRequest request){
	   
		String data=request.getParameter("dat");
	   // System.out.println("data="+data);
	    JSONObject jsonObject=JSONObject.fromObject(data);
	    try { 
	    	Emplye emp=new Emplye();
	    	String email=jsonObject.getString("email");
		    emp.setEmail(email);
			String name=new String(jsonObject.getString("name").getBytes("ISO-8859-1"),"UTF-8") ;
			emp.setName(name); 
			emplyeService.add(emp);
			
			//查找Role
			String roleId=jsonObject.getString("roleId");
			Role  role = null;
			if(roleId!=null&&!roleId.equals("")){
				int rol_id=Integer.parseInt(roleId);
				role=roleService.serchRole(rol_id);
			}
			//添加Emp_Role
			Emp_Role em_role=new Emp_Role();
			em_role.setEmp(emp);
			em_role.setRoln(role);
			
			emp_RoleService.add(em_role);  
			
	    } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	}
	//更新
	@RequestMapping("updat")
	@ResponseBody
	public void updat(HttpServletRequest request){
		String epname=request.getParameter("epname");
		String email=request.getParameter("email");
		String roleId=request.getParameter("roleId");
		String id=request.getParameter("ind");
		String sour_rolid=request.getParameter("sour_roid");
		
		Emplye emp=new Emplye();
		if(id!=null&&!id.equals("")){
			int idn=Integer.parseInt(id);
			emp=emplyeService.findByid(idn);
	
		emp.setEmail(email);
		emp.setName(epname);
		
		emplyeService.add(emp);
		
		//查找Role
		Role  role = null;
		if(roleId!=null&&!roleId.equals("")&&(sour_rolid!=null&&!sour_rolid.equals(""))){
			int rol_id=Integer.parseInt(roleId);
			role=roleService.serchRole(rol_id);
			int sour_rolidn=Integer.parseInt(sour_rolid);
			//添加Emp_Role
			Emp_Role em_role=emp_RoleService.find(idn,sour_rolidn);
			em_role.setEmp(emp);
			em_role.setRoln(role);
			
			emp_RoleService.add(em_role); 
		}
		 
		}	
		
		
		
		
	}
	
	//根据id查询
	@RequestMapping("serchByid")
	@ResponseBody
    public Map<String, Object> serchByid(HttpServletRequest request,HttpServletResponse response){
		
		String id=request.getParameter("id");
		Emplye emply=new Emplye();
		Map<String, Object> result=new HashMap<String, Object>();
		if(!id.equals("")&&id!=null){
			PrintWriter out;
			
			try {
				response.setContentType("text/html;charset=utf-8");
				int idn=Integer.parseInt(id);
				emply=emplyeService.findByid(idn);
				//System.out.println("emply="+emply.getList());
				Role role=roleService.serch(idn);
				int ro_id=role.getId();
				//System.out.println("角色id="+ro_id);
				
				result.put("content", emply);
				result.put("roleid", ro_id);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		return result;
		
		
		
	}
	
    //查询
	@RequestMapping("serch")
	@ResponseBody
	public Map<String,Object> serch(HttpServletRequest request){
		//获取信息
		String pageNow=request.getParameter("PageNow");
		String pageCount=request.getParameter("pageCount");
		
		String empName = request.getParameter("empName");
		String email = request.getParameter("email");
        //System.out.println("empName="+empName+"===email="+email);
		try {
			if (empName != null && !empName.equals("")) {
				empName = new String(empName.getBytes("ISO-8859-1"), "UTF-8");
			}
			if(email != null && !email.equals("")){
				email = new String(email.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("empName1="+empName+"===email2="+email);
		
		Sort sort=new Sort(Sort.Direction.DESC, "id");
		int pageNown;
		int pageCounts;
		Pageable pageable=null;
		if((pageNow!=null&&!pageNow.equals(""))&&(pageCount!=null&&!pageCount.equals(""))){
			 pageNown=Integer.parseInt(pageNow)-1;
			 pageCounts=Integer.parseInt(pageCount);
			 pageable=new PageRequest(pageNown,pageCounts,sort);
		}
		
		Map<String, Object> result=emplyeService.serch(pageable,empName,email);
		
		
		return result;
	}
	
}
