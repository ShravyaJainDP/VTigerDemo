package com.autodeshcrm.genericutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Shravya 
 *
 */
public class ExcelUtil {
	String filepath="./testData/testScriptData.xlsx";
	public  Workbook book;
	/**
	 * This method is used to read data from excel
	 * @param excelFile
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws Throwable
	 */
	public String readData(String sheetname,int row,int cell) throws Throwable
	{
		book=WorkbookFactory.create(new FileInputStream(filepath));
		String data=book.getSheet(sheetname).getRow(row).getCell(cell).getStringCellValue();
		book.close();
		return data;
		
	}
	/**
	 * This method is used to write data in excel sheeet
	 * @param output
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @param data
	 * @throws Throwable
	 */
	public void writeData(String sheetname,int row,int cell,String data) throws Throwable
	{
		book=WorkbookFactory.create(new FileInputStream(filepath));
		book.getSheet(sheetname).getRow(row).getCell(cell).setCellValue(data);
		book.close();
	}
	/**
	 * This method is used to get row count in excel sheet
	 * @param excelfile
	 * @param sheet
	 * @return
	 * @throws Throwable
	 */
	public int getRoeCount(String excelfile,String sheet) throws Throwable
	{
		book=WorkbookFactory.create(new FileInputStream(filepath));
		int i= book.getSheet(sheet).getLastRowNum();
		book.close();
		return i;
	}
}
