package com.game.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.template.ConstantTemplate;
import com.game.util.PoiUtil;

public class ConstantConfig {
	private final static String sheetName = "Constant";
	private static final Logger logger = LoggerFactory.getLogger(ConstantConfig.class);
	public static Map<Integer, ConstantTemplate> map = new HashMap<Integer, ConstantTemplate>();
	
	public static void init() {
		InputStream in = ConstantConfig.class.getClassLoader().getResourceAsStream("template/Constant.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer value = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					
					ConstantTemplate constant = new ConstantTemplate(id, value);
					map.put(constant.getId(), constant);
					
				}
				i++;
			}
			logger.info(String.format("ConstantTemplate config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}