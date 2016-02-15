package io.sunyi.cases.excelreader;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JxlTest
{
	public static void main(String[] args) throws BiffException, IOException
	{
		String filepath = "src/main/resource/io/sunyi/cases/excelreader/test.xls";
		Workbook workbook = Workbook.getWorkbook(new File(filepath)); 
		
		Sheet sheet = workbook.getSheets()[0];
		
		for(int  i = 0 , rowCount = sheet.getRows(); i < rowCount ; i++)
		{
			Cell[] row = sheet.getRow(i);
			for(Cell cell : row)
			{
				System.out.println(cell.getContents());
			}
		}
	}
}
