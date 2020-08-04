package com.Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtils {
	
	public static FileInputStream fileinput;
	public static FileOutputStream fileoutput;
	public static HSSFWorkbook workbook;
	public static HSSFSheet sheet;
	public static HSSFRow row;
	public static HSSFCell cell;
	
	public static int gerRowCount(String xlfile, String xlsheet) throws IOException
	{
		fileinput = new FileInputStream(xlfile);
		workbook = new HSSFWorkbook(fileinput);
		sheet = workbook.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fileinput.close();
		return rowcount;
	}
	
	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException
	{
		fileinput = new FileInputStream(xlfile);
		workbook = new HSSFWorkbook(fileinput);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fileinput.close();
		return cellcount;
		
	}
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fileinput=new FileInputStream(xlfile);
		workbook=new HSSFWorkbook(fileinput);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		workbook.close();
		fileinput.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum, String data) throws IOException
	{
		fileinput=new FileInputStream(xlfile);
		workbook=new HSSFWorkbook(fileinput);
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell.setCellValue(data);
		fileoutput = new FileOutputStream(xlfile);
		workbook.write(fileoutput);
		workbook.close();
		fileinput.close();
		fileoutput.close();
	}

}
