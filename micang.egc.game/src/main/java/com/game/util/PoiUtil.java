package com.game.util;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class PoiUtil {
	public static Byte getByte(XSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		Byte result = cellValue.byteValue();
		return result;
	}
	
	public static Integer getInt(XSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		Integer result = cellValue.intValue();
		return result;
	}
	
	public static Short getShort(XSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		Short result = cellValue.shortValue();
		return result;
	}
	
	public static Long getLong(XSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		Long result = cellValue.longValue();
		return result;
	}
	
	public static Float getFloat(XSSFCell cell) {
		Double cellValue = cell.getNumericCellValue();
		Float result = cellValue.floatValue();
		return result;
	}
	
	public static String getString(XSSFCell cell) {
		if (cell == null) {
			return null;
		} else {

			cell.setCellType(CellType.STRING);
			String result = cell.getStringCellValue();
			return result;
		}
	}
}
