package com.fjt.control;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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

import com.fjt.pojo.User;
import com.fjt.service.UserService;


//�û����������
@Controller
public class UserControl {
	private Logger logger=LoggerFactory.getLogger(UserControl.class);
	
	@Autowired
	private UserService userservice;
	
	//�����û��������
	@RequestMapping("userInfo")
	public ModelAndView runUserInfo(){
			String view="showInfo";
			ModelAndView modelAndView=new ModelAndView(view);
			return modelAndView;
	}
	
	//�����ϴ�
	@RequestMapping("reportUp")
	public void reportUp(@RequestParam("file")MultipartFile file,HttpServletRequest request){
		String fileName=file.getOriginalFilename();
		userservice.reportUp(file);
		
		
	}
	
	//��������
	@RequestMapping("reporDown")
	public void reporDown(HttpServletResponse response,HttpServletRequest request){
		System.out.println("hellp");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("�û���Ϣ");
		XSSFRow row=sheet.createRow(0);
		XSSFCell cell=row.createCell(0);
		cell.setCellValue("�û�ID");
		cell=row.createCell(1);
		cell.setCellValue("�û���");
		cell=row.createCell(2);
		cell.setCellValue("����");
		cell=row.createCell(3);
		cell.setCellValue("�绰");
		cell=row.createCell(4);
		cell.setCellValue("��ַ");
		
		List<User> users=new ArrayList<User>();
		users=userservice.findAllUser();
		for(int i=0;i<users.size();i++){
			User user=users.get(i);
			row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getPssword());
			row.createCell(3).setCellValue(user.getTelep());
			row.createCell(4).setCellValue(user.getAddr());
		}
		
		//����
		OutputStream out=null;
		try {
			out=response.getOutputStream();
			String fileName="�û���Ϣ��.xlsx";
			response.setContentType("application/x-msdownload");
			//��ȡ������ں���Ϣ
			String clientInfo=request.getHeader("User-agent");
			//�ж��ں�
			if(clientInfo!=null&&clientInfo.indexOf("MSIE")>0){
				if(clientInfo.indexOf("MSIE 5")>0||clientInfo.indexOf("MSIE 6")>0){
					 fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
				 }else{
					 fileName=URLEncoder.encode(fileName,"UTF-8");
				 }
			}else if(clientInfo.indexOf("Trident")>0){
				fileName=URLEncoder.encode(fileName,"UTF-8");
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
	
	
	//��ѯ/��ҳʵ��
	@RequestMapping("serchInfo")
	@ResponseBody
	public Map<String,Object> serchInfo(HttpServletRequest request,@RequestParam Map<String,String> recevis) throws UnsupportedEncodingException
	{
		//��������������⡣
		//String username=new String( request.getParameter("usernames").getBytes("ISO-8859-1"),"utf-8");
		//get�ύ��������Ľ������
		//String username=new String(recevis.get("usernames").getBytes("ISO-8859-1"),"utf-8");
		
		//��ȡ��ѯ��Ϣ
		String username=recevis.get("usernames");
		String telep=recevis.get("telep");
		
	    //��ȡһҳ�м��С�
	    int pageRows=Integer.parseInt(request.getParameter("Rows"));
	    //��ȡ��ǰҳ��
	    int pageNow=Integer.parseInt(request.getParameter("pageNow"))-1 ;
	    //����ʽ
	    Sort sort=new Sort(Sort.Direction.ASC,"id");
	    Pageable pageable=new PageRequest(pageNow,pageRows,sort);
	    
	    Map<String,Object> map=userservice.findPage2(pageable,username,telep);
	    //Map<String,Object> map=userservice.findPage(pageable);
        
	    HashMap<String, Object> resultMap=new HashMap<String, Object>();
	    resultMap.put("content", map.get("content"));
	    resultMap.put("total", map.get("total"));
		return resultMap;
	}
	
//	//����û�:����һ
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
	 
    //����û�  :������
	@RequestMapping("adduser")
	@ResponseBody
	//�������pojo������ӳ�䣬�����������Ҫ��pojo�����������һ�¡�
	public String  add(User user) {
		String addr = null;
		try {
			System.out.println("xxx="+user.getAddr());
			addr = new String(user.getAddr().getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("�������쳣");
			e.printStackTrace();
		}
		user.setAddr(addr);
		userservice.save(user);
		return "success";
	}
	
	//ɾ���û�
	@RequestMapping("delt")
	public String delete(HttpServletRequest request){
		int id = 0;
		String idn=request.getParameter("id");
		if(idn!=null&&!idn.equals("")){
			//ɾ��һ��
			if(!idn.contains(",")){ 
				id=Integer.parseInt(idn);
				userservice.delet(id);
				//System.out.println("idn===="+idn);
			}else{
			//ɾ�����
			    String idArr[]=idn.split(",");
				for(String sip_id:idArr){
					
					id=Integer.parseInt(sip_id);
					userservice.delet(id);
				}
			}
		}
		
		
		return "success";
	}
	
	//�޸�-�ع�����
	@RequestMapping("upretn")
	@ResponseBody
	public Map<String,Object> updRendata(HttpServletRequest request){
		String id=request.getParameter("id");
		int idn=1;
		if(id!=null&&!id.equals("")){
		   idn=Integer.parseInt(id);
		}
		User user=userservice.finOne(idn);
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("user", user);
		return result;
	}
	
	//�޸Ĳ���
	@RequestMapping("updat")
	@ResponseBody
	public void updat(User user){
	 //System.out.println("sdxx="+user.getId());
	 
	 User usr=userservice.finOne(user.getId());
	 usr.setId(user.getId());
	 usr.setAddr(user.getAddr());
	 usr.setName(user.getName());
	 usr.setPssword(user.getPssword());
	 usr.setTelep(user.getTelep());
	 userservice.save(usr);
	}

}
