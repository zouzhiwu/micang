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

import com.common.template.HeroProficiencyTemplate;
import com.game.util.PoiUtil;

public class HeroProficiencyConfig {

	private final static String sheetName = "Hero_proficiency";
	private static final Logger logger = LoggerFactory.getLogger(MemberConfig.class);
	public static Map<Integer, HeroProficiencyTemplate> map = new HashMap<Integer, HeroProficiencyTemplate>();
	
	public static void init() {
		InputStream in = MemberConfig.class.getClassLoader().getResourceAsStream("template/Hero_Proficiency.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Byte work = PoiUtil.getByte((XSSFCell) row.getCell(index++));
					Integer lv = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Integer exp = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Integer advanced = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					String prop = PoiUtil.getString((XSSFCell) row.getCell(index++));
					String number = PoiUtil.getString((XSSFCell) row.getCell(index++));

					HeroProficiencyTemplate heroProficiencyTemplate = new HeroProficiencyTemplate(id, work, lv, exp,
							advanced, prop, number);
					map.put(id, heroProficiencyTemplate);
				}
				i++;
			}
			logger.info(String.format("HeroProficiencyConfig config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
