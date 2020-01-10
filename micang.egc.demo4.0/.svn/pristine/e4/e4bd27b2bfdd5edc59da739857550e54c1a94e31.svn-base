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

import com.common.template.StageDropTemplate;
import com.game.util.PoiUtil;

public class StageDropConfig {
	private final static String sheetName = "Sheet1";
	private static final Logger logger = LoggerFactory.getLogger(StageDropConfig.class);
	public static Map<Integer, StageDropTemplate> map = new HashMap<Integer, StageDropTemplate>();
	
	public static void init() {
		InputStream in = StageDropConfig.class.getClassLoader().getResourceAsStream("template/StageDrop.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String dropGroup = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String dropMinNum = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String dropMaxNum = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String randDropGroup = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String randDropRate = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String randDropMinNum = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String randDropMaxNum = PoiUtil.getString((XSSFCell)row.getCell(index++));
					StageDropTemplate stageDropTemplate = new StageDropTemplate(id, dropGroup, dropMinNum, dropMaxNum, randDropGroup,
							randDropRate, randDropMinNum, randDropMaxNum);
					
					map.put(id, stageDropTemplate);
				}
				i++;
			}
			logger.info(String.format("StageDropConfig config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
