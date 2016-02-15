package io.sunyi.cases.excelreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PoiTest
{
	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException
	{
		long time = System.currentTimeMillis();
		
		String filepath = "src/main/resource/io/sunyi/cases/excelreader/test.xlsx";
//		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filepath));
		
		Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath));
		
		System.out.println("init consume: " + (System.currentTimeMillis() - time));
		
		int cellCount = 0;

		
		
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = sheet.getFirstRowNum(), rowConut = sheet.getLastRowNum(); i <= rowConut; i++)
		{

			Row row = sheet.getRow(i);

			for (int j = row.getFirstCellNum(), columnCount = row.getLastCellNum(); j < columnCount; j++)
			{
				Cell cell = row.getCell(j);
				switch (cell.getCellType())
				{
				case Cell.CELL_TYPE_STRING:
					break;
				case Cell.CELL_TYPE_NUMERIC:
				default:
					break;
				}
				cellCount += 1;
			}
		}
		System.out.println(cellCount);
		System.out.println(System.currentTimeMillis() - time);

	}
	
	public static String numericToString(double numeric)
	{
		String str = String.valueOf(numeric);
		if(str.endsWith(".0"))
		{
			str = str.replace(".0", "");
		}
		return str;
	}
}
