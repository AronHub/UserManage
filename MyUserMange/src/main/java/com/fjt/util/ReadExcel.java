package com.fjt.util;

import java.io.FileInputStream;
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

import com.fjt.pojo.Emplye;

//����excel�ϴ�����
public class ReadExcel {
	//������
	private int totalRow;
	
	//������
	private int totalCell;
	
	//������Ϣ
	private String errMsg;
	
	public ReadExcel(){}
	
	//��ȡ������
	public int getTotalRow(){
		return totalRow;
	}
	
	//��ȡ������
	public int getTotalCell(){
		return totalCell;
	}
	
	//��ȡ������Ϣ
	public String getErrMsg(){
		return errMsg;
	}
	
	//�ж��ļ���
	public List<Emplye> readFile(MultipartFile file){
		String fileName=file.getOriginalFilename();
		if(validateExcel(fileName)){
			return null ;
		}
		
		boolean is2003Exl=true;
		if(is2007Excel(fileName)){
			is2003Exl=false;
		}
		List<Emplye> empList=new ArrayList<Emplye>();
		try {
			empList=creteWork(file.getInputStream(),is2003Exl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return empList;
		
	}
	
	//����is2003����work��
	public List<Emplye> creteWork(InputStream is,boolean is2003){
		List<Emplye> empList=new ArrayList<Emplye>();
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
		empList=this.getEmpList(work);
		
		
		
		return empList;
		
	}
	//��ȡ��Ϣ
	public List<Emplye> getEmpList(Workbook work){
		//�õ���һ��shell
		Sheet  sheet=work.getSheetAt(0);
		//�õ�������
		totalRow=sheet.getPhysicalNumberOfRows();
		System.out.println("totalRow="+totalRow);
		//��ȡ��������ǰ������������
		if(totalRow>1&&sheet.getRow(0)!=null){
			this.totalCell=sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("totalCell="+totalCell);
		}
		
		//��ȡ����
		List<Emplye> empList=new ArrayList<Emplye>();
		for(int r=1;r<totalRow;r++){
			Row row=sheet.getRow(r);
			if(row==null){
				continue;
			}
			
			Emplye emp=new Emplye();
			//ѭ����
			for(int c=0;c<totalCell;c++){
				Cell cell=row.getCell(c);
				if(cell!=null){
					if(c==0){
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						  String id=String.valueOf(cell.getNumericCellValue());
						  id=id.substring(0, id.length()-2>0?id.length()-2:1);
						  int idn=Integer.parseInt(id);
						  emp.setId(idn);
						}
						
					}else if(c==1){
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							String name=String.valueOf(cell.getNumericCellValue());
							emp.setName(name.substring(0,name.length()-2>0?name.length()-2:1));
						}else{
							emp.setName(cell.getStringCellValue());
						}
						
						
					}else if(c==2){
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							String email=String.valueOf(cell.getNumericCellValue());
							emp.setEmail(email.substring(0,email.length()-2>0?email.length()-2:1));
						}else{
							emp.setEmail(cell.getStringCellValue());
						}
						
					}
					
				}
			}
			empList.add(emp);
		
		}
		
		return empList;
		
		
		
		
	}
	
	
	
	//�ж��Ƿ���excel
	public boolean validateExcel(String fileName){
		if(fileName==null||!is2003Excel(fileName)||!is2007Excel(fileName)){
			errMsg="�ļ�������excel��ʽ";
			return false;
		}
		return true;
		
	}
	
	
	public static boolean is2003Excel(String  fileName){
		return fileName.matches("^.+\\.(?i)(xls)$");
	}
	
	public static boolean is2007Excel(String fileName){
	
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}
	

}
