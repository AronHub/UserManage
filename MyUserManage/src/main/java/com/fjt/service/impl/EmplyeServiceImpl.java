package com.fjt.service.impl;

import java.io.FileOutputStream;
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

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.bussiness.EmplyBuzii;
import com.fjt.pojo.Emplye;
import com.fjt.service.EmplyeService;
import com.fjt.util.ReadExcel;

@Service
public class EmplyeServiceImpl implements EmplyeService{

	@Autowired
	private EmplyBuzii emplybuzi;
	
	@Override
	public Map<String, Object> serch(Pageable pageable,String empName,String email) {
		// TODO Auto-generated method stub
	   Page<Emplye> page=emplybuzi.serch(pageable, empName, email);

	   Map<String ,Object> result=new HashMap<String, Object>();
	   if(page!=null){
		   result.put("content",page.getContent());
		   result.put("total", page.getTotalPages()); 
	   }
		
		return result;
	}

	@Override
	public List<Emplye> empList() {
		// TODO Auto-generated method stub
		return emplybuzi.empList();
	}


	@Override
	public void add(Emplye emp) {
		// TODO Auto-generated method stub
		emplybuzi.add(emp);
	}

	@Override
	@Transactional(propagation=Propagation.NEVER)
    public void delt(int id) {
		// TODO Auto-generated method stub
		emplybuzi.delt(id);
	}

	@Override
	public String exportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		String result="";
		//����������
		ReadExcel readExcel=new ReadExcel();
		//��ȡ��excel����Ϣ
		List<Emplye> empList=new ArrayList<Emplye>();
		empList=readExcel.readFile(file);
		//�ж�
	     if(empList!=null){
	    	 result+="�ϴ��ɹ�";
	    	 for(Emplye emp:empList){
	    		 this.add(emp);
	    	 }
	    	
	     }else{
	    	 result+="�ϴ�ʧ��";
	    	 
	     }
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deltAll() {
		// TODO Auto-generated method stub
		emplybuzi.delAll();
	}

	@Override
	public Emplye findByid(int id) {
		// TODO Auto-generated method stub
		return emplybuzi.findByid(id);
	}
	
	
	
}
