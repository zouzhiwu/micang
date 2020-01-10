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

import com.common.template.CharacterTemplate;
import com.game.util.PoiUtil;

public class CharacterConfig {
	private final static String sheetName = "Character";
	private static final Logger logger = LoggerFactory.getLogger(ChapterConfig.class);
	public static Map<Integer, CharacterTemplate> map = new HashMap<Integer, CharacterTemplate>();
	public static void init() {
		InputStream in = ChapterConfig.class.getClassLoader().getResourceAsStream("template/Character.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String name = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String characterTxt = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Integer quality = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String characterIcon = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String probability1 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String character1 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String probability2 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String character2 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Integer constant = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String number = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String yxt = PoiUtil.getString((XSSFCell)row.getCell(index++));

					CharacterTemplate CharacterTemplate = new CharacterTemplate(id, name, characterTxt, quality, characterIcon, probability1,
																	character1, probability2, character2, constant, number, yxt);
					map.put(id, CharacterTemplate);
				}
				i++;
			}
			logger.info(String.format("CharacterConfig config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
