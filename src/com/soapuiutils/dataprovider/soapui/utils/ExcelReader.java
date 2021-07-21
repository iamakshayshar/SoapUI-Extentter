package com.soapuiutils.dataprovider.soapui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	private static Workbook workBook = null;
	private static Sheet workSheet = null;
	private static String[][] data;
	private static Sheet sheet;

	private static Sheet getSheet(String filePath, String sheetName) {
		File file = new File(filePath);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			workBook = WorkbookFactory.create(fileInputStream);
			workSheet = workBook.getSheet(sheetName);
		} catch (Exception e) {
			System.out.println("Not able to retrieve sheetName");
		}
		return workSheet;
	}

	public static String[][] getExcelData(String filePath, String sheetName) {
		try {
			sheet = getSheet(filePath, sheetName);
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			data = new String[totalRows + 1][totalColumns];

			for (int rowCount = 0; rowCount <= totalRows; rowCount++) {
				for (int columnCount = 0; columnCount < totalColumns; columnCount++) {
					data[rowCount][columnCount] = getCellValue(sheet.getRow(rowCount).getCell(columnCount));
				}
			}
			return data;
		} catch (Exception e) {
			System.out.println("Not able to retrieve sheetName");
			return data;
		}
	}

	public static Object readExcelData(String excelPath, String excelSheet, Integer count) {
		String[][] arrayObject = getExcelData(excelPath, excelSheet);
		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		for (int i = 0; i < arrayObject[count].length; i++) {
			hm.put(i, arrayObject[count][i]);
		}
		return hm;
	}

	private static String getCellValue(Cell cell) {
		String strCellValue = "";
		try {
			if (cell == null) {
				strCellValue = "";
			} else {
				switch (cell.getCellTypeEnum()) {
				case BOOLEAN:
					strCellValue = new String(new Boolean(cell.getBooleanCellValue()).toString());
					break;
				case STRING:
					strCellValue = cell.getRichStringCellValue().getString().toString();
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						strCellValue = dateFormat.format(cell.getDateCellValue());
					} else {
						Double value = cell.getNumericCellValue();
						Long longValue = value.longValue();
						strCellValue = new String(longValue.toString());
					}
					break;
				case BLANK:
					strCellValue = "";
					break;
				default:
					strCellValue = "";
				}
			}
			return strCellValue.trim();
		} catch (Exception e) {
			System.out.println("Error while parcing cell value");
			return "";
		}
	}

	public static HashMap<Integer, String> readExcelHeader(String filePath, String sheetName) {
		HashMap<Integer, String> columnHeaders = new HashMap<Integer, String>();
		sheet = getSheet(filePath, sheetName);
		int totalColumns = sheet.getRow(0).getLastCellNum();
		for (int headerCount = 0; headerCount < totalColumns; headerCount++) {
			columnHeaders.put(headerCount, getCellValue(sheet.getRow(0).getCell(headerCount)));
		}
		return columnHeaders;
	}

	public static int getTotalExcelRowsWithoutHeader(String filePath, String sheetName) {
		int totalRows = 0;
		try {
			sheet = getSheet(filePath, sheetName);
			totalRows = (sheet.getLastRowNum());
		} catch (Exception e) {
			System.out.println("Not able to retrieve sheetName");
		}
		return totalRows;
	}

}
