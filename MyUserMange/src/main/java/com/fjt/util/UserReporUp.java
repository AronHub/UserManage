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
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.pojo.User;

public class UserReporUp {
	private int totalRow;
	private int totalCell;
	private String errMsg;
	
	public UserReporUp(){}
	
	public int getTotalRow(){
		return totalRow;
	}
	public int getTotalCell(){
		return totalCell;
	}
	public String getErrMsg(){
		return errMsg;
	}
	
	public List<User> exreport(MultipartFile file){
		String fileName=file.getOriginalFilename();
		if(!isExcel(fileName)){
			return null;
		}
		boolean is2003=true;
		if(is2007Excel(fileName)){
			is2003=false;
		}
		List<User> users = null;
		try {
			users = creteWork(file.getInputStream(),is2003);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	
	}
	
	public List<User> creteWork(InputStream is,boolean is2003){
		Workbook workbook;
		List<User> users=null;
	  try {
			if(is2003){
				workbook=new HSSFWorkbook(is);
			}else{
			     workbook=new XSSFWorkbook(is);
			}
			 users=getUserInfo(workbook);
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return users;
	}
	
	public List<User> getUserInfo(Workbook workbook){
	    Sheet sheet=workbook.getSheetAt(0);
	    totalRow=sheet.getPhysicalNumberOfRows();
	    if(totalRow>0&&sheet.getRow(0)!=null){
	    	totalCell=sheet.getRow(0).getPhysicalNumberOfCells();
	    }
	    List<User> users=new ArrayList<User>();
	   
	    for(int r=1;r<totalRow;r++){
	    	Row row=sheet.getRow(r);
	    	if(row==null){
	    		continue;
	    	}
	    	User user=new User();
	    	for(int c=0;c<totalCell;c++){
	    		
	    		Cell cell=row.getCell(c);
	    		if(cell!=null){
	    			if(c==0){
		    			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		    				String id=String.valueOf(cell.getNumericCellValue());
		    				id=id.substring(0,id.length()-2>0?id.length()-2:1);
		    				if(id!=null&&!id.equals("")){
		    					int idn=Integer.parseInt(id);
		    					user.setId(idn);
		    				}
		    			
		    			}else{
			    			String id=cell.getStringCellValue();
			    			if(id!=null&&id.equals("")){
			    				int idn=Integer.parseInt(id);
			    				user.setId(idn);
			    			}
			    			
			    		}
		    			
		    		}else if(c==1){
		    			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		    				String name=String.valueOf(cell.getNumericCellValue());
		    				name=name.substring(0,name.length()-2>0?name.length()-2:1);
		    				user.setName(name);
		    			}else{
		    				user.setName(cell.getStringCellValue());
		    			}
		    		}else if(c==2){
		    			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		    				String pass=String.valueOf(cell.getNumericCellValue());
		    				pass=pass.substring(0,pass.length()-2>0?pass.length()-2:1);
		    				user.setPssword(pass);
		    			}else{
		    				user.setPssword(cell.getStringCellValue());
		    			}
		    		}else if(c==3){
		    			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		    				String telep=String.valueOf(cell.getNumericCellValue());
		    			    telep=telep.substring(0,telep.length()-2>0?telep.length()-2:1);
		    			    user.setTelep(telep);
		    			}else{
		    				user.setTelep(cell.getStringCellValue());
		    			}
		    		
		    		}else if(c==4){
		    			if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
		    				String addr=String.valueOf(cell.getNumericCellValue());
		    				addr=addr.substring(0,addr.length()-2>0?addr.length()-2:1);
		    				user.setAddr(addr);
		    			}else{
		    				user.setAddr(cell.getStringCellValue());
		    			}
		    		}	
	    		}
	    		
	    		
	    		
	    	}
	    	users.add(user);
	    }
		
		return users;
		
	}
	
	
	public boolean isExcel(String fileName){
		if(fileName!=null&&(is2003Excel(fileName)||is2007Excel(fileName))){
			return true;
		}
		errMsg="上传的文件格式不正确";
		return false;
		
	}
	
	public static boolean is2003Excel(String fileName){
		return fileName.matches("^.+\\.(?i)(xls)$");
	}
	
	public static boolean is2007Excel(String fileName){
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}
	
	
	
	
	

}
