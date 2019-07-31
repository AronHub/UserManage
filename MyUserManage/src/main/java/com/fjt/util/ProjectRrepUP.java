package com.fjt.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.Project;

public class ProjectRrepUP {
	private int totalRow;
	private int totalCell;
	private String errMsg;
	public ProjectRrepUP(){}
	
	public int getTotalRow(){
		return totalRow;
	}
	
	public int getTotalCell(){
		return totalCell;
		
	}
	public String getErrMsg(){
		return errMsg;
	}
	
//获取文件文件名,检查是否是正的格式
	public List<Project> check(MultipartFile file){
		String fileName=file.getOriginalFilename();
		boolean is2003=true;
		InputStream out=null;
		try {
				if(!chekExcel(fileName)){
		    		return null;
		    	}
			    if(isExcel2007(fileName)){
							is2003=false;
				}
				out=file.getInputStream();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 e.printStackTrace();
			}
		return this.createWork(is2003,out);
		
	}
//创建work.
	public List<Project> createWork(boolean is2003,InputStream is){
		Workbook work=null;
			try {
				if(is2003){
				     work=new HSSFWorkbook(is);
				}else{
					 work=new XSSFWorkbook(is);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return this.getExcelinfo(work);
		
	}
	
//获取excel中的信息。
	public List<Project> getExcelinfo(Workbook work){
		if(work==null){
			return null;
		}else{
			Sheet sht=work.getSheetAt(0);
			totalRow=sht.getPhysicalNumberOfRows();
			Row row=sht.getRow(0);
			List<Project> list=new ArrayList<Project>();
			if(totalRow>0&&row!=null){
				this.totalCell=row.getPhysicalNumberOfCells();
				
				for(int i=0;i<totalRow;i++){
					Project project=new Project();
					row=sht.getRow(i+1);
					for(int j=0;j<totalCell;j++){
						Cell cell=row.getCell(j);
						if(j==0){
						
							
						}else if(j==1){
							
						}else if(j==2){
							
						}else if(j==3){
							
						}
						
					}
					
					list.add(project);
					
				}
			}
			return list;
		}
		
	}
	
	
	public boolean chekExcel(String fileName){
		if(fileName!=null&&(isExcel2003(fileName)||isExcel2007(fileName))){
			
			return true;
		}
		errMsg="文件格式不正确";
		return false;
		
	}
	
	public static boolean isExcel2003(String fileName){
	    return	fileName.matches("^.+//.(i?)(.xls)$");
	}
	
	public static boolean isExcel2007(String fileName){
		return  fileName.matches("^.+//.(i?)(.xlsx)$");
	}
	
	



}