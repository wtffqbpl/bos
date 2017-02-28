package com.feng.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.tomcat.jni.File;
import org.junit.Test;

public class POITest {
	@Test
	public void Demo1() throws FileNotFoundException, IOException{
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new java.io.File("d:\\bcd.xls")));
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) {
			row.getCell(0).setCellType(1);
			String v1 = row.getCell(0).getStringCellValue();
			System.out.println(v1);
		}
	}

}
