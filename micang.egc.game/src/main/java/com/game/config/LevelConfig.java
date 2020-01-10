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

import com.common.template.LevelTemplate;
import com.game.util.PoiUtil;

public class LevelConfig {
	private final static String sheetName = "Level";
	private static final Logger logger = LoggerFactory.getLogger(LevelConfig.class);
	public static Map<Integer, LevelTemplate> map = new HashMap<Integer, LevelTemplate>();
	
	public static Map<String, LevelTemplate> typeLvMap = new HashMap<String, LevelTemplate>();
	
	
	public static void init() {
		InputStream in = LevelConfig.class.getClassLoader().getResourceAsStream("template/Level.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Byte type = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte lv = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Integer exp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer totalExp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					LevelTemplate levelTemplate = new LevelTemplate(id, type, lv, exp, totalExp);
					map.put(levelTemplate.getId(), levelTemplate);
					
					typeLvMap.put(levelTemplate.getType()+"_"+levelTemplate.getLv(), levelTemplate);
				}
				i++;
			}
			logger.info(String.format("level config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}