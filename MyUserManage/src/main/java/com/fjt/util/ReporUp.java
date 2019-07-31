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

import com.fjt.pojo.Role;

public class ReporUp {
	private int totalRow;
	private int totalCell;
	private String errMsg;
	public ReporUp(){}
	
	public int getTotalRow(){
		return totalRow;
	}
	
	public int getTotalCell(){
		return totalCell;
	}
	
	public String getErrmsg(){
		return errMsg;
	}
	
	public List<Role> judgeFile(MultipartFile file){
		String fileName=file.getOriginalFilename();
		if(!judgeExcel(fileName)){
			return null;
		}
		boolean is2003=true;
		if(exe2007(fileName)){
			is2003=false;
		}
		List<Role> roles=null;
		try {
		    roles=createWork(file.getInputStream(),is2003);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roles;
		 
	}
	
	public List<Role> createWork(InputStream is,Boolean is2003){
		   Workbook workbook;
		   List<Role> roles=null;
			try {
				if(is2003){
				    workbook=new HSSFWorkbook(is);
				}else{
					workbook=new XSSFWorkbook(is);
				}
				 roles=readExcl(workbook);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return roles;
		
	}
	
	public List<Role> readExcl(Workbook workbook){
	
		Sheet shet=workbook.getSheetAt(0);
		//获取总行数
		totalRow=shet.getPhysicalNumberOfRows();
		//获取总列数
		if(totalRow>1&&shet.getRow(0)!=null){
			totalCell=shet.getRow(0).getPhysicalNumberOfCells();
		}
		
		List<Role> roles=new ArrayList<Role>();
		for(int r=1;r<totalRow;r++){
			Row row=shet.getRow(r);
			if(row==null){
				continue;
			}
			Role role=new Role();
			for(int c=0;c<totalCell;c++){
				Cell cell=row.getCell(c);
				if(cell!=null){
					if(c==0){
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							String roleId=String.valueOf(cell.getNumericCellValue());
							roleId=roleId.substring(0,roleId.length()-2>0?roleId.length()-2:1);
							if(roleId!=null){
								int rolId=Integer.parseInt(roleId);
								role.setId(rolId);
							}
							
							
									
						}
			
					}else if(c==1){
						if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
							String roleName=String.valueOf(cell.getNumericCellValue());
							roleName=roleName.substring(0,roleName.length()-2>0?roleName.length()-2:1);
							role.setName(roleName);
						}else{
							role.setName(cell.getStringCellValue());
						}
						
					}else if(c==2){
						
						if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
							String roleLvl=String.valueOf(cell.getNumericCellValue());
							roleLvl=roleLvl.substring(0,roleLvl.length()-2>0?roleLvl.length()-2:1);
							role.setLevl(roleLvl);
						}else{
							role.setLevl(cell.getStringCellValue());
						}
					}
				}
				
		
			}
			   roles.add(role);
		}
		return roles;
		
	}
	
	public boolean judgeExcel(String fileName){
		if(fileName==null&&!exe2003(fileName)&&!exe2007(fileName)){
			errMsg+="文件格式不正确";
			return false;
			
		}
		return true;
	}
	
	public static boolean exe2003(String file){
		return file.matches("^.+\\.(?i)(xls)$");
	}
	public static boolean exe2007(String file){
		return file.matches("^.+\\.(?i)(xlsx)$");
	}
	

}
