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

import com.common.template.AttrConvertTemplate;
import com.game.util.PoiUtil;

public class AttrConvertConfig {
	private final static String sheetName = "AttrConvert";
	private static final Logger logger = LoggerFactory.getLogger(AttrConvertConfig.class);
	public static Map<Integer, AttrConvertTemplate> map = new HashMap<Integer, AttrConvertTemplate>();
	
	public static void init() {
		InputStream in = AttrConvertConfig.class.getClassLoader().getResourceAsStream("template/AttrConvert.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer twoAttrId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer OneAttrId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Float value = PoiUtil.getFloat((XSSFCell)row.getCell(index++));
					AttrConvertTemplate attrConvertTemplate = new AttrConvertTemplate(twoAttrId, OneAttrId, value);
					map.put(attrConvertTemplate.getId(), attrConvertTemplate);
				}
				i++;
			}
			logger.info(String.format("AttrConvert config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}