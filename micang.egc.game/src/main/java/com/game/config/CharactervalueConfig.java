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

import com.common.template.CharactervalueTemplate;
import com.game.util.PoiUtil;

public class CharactervalueConfig {

	private final static String sheetName = "Charactervalue";
	private static final Logger logger = LoggerFactory.getLogger(CharactervalueConfig.class);
	public static Map<Integer, CharactervalueTemplate> map = new HashMap<Integer, CharactervalueTemplate>();

	public static void init() {
		InputStream in = ChapterConfig.class.getClassLoader().getResourceAsStream("template/Charactervalue.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Byte rarity = PoiUtil.getByte((XSSFCell) row.getCell(index++));
					Integer weight = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Integer condition = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					Integer attribute = PoiUtil.getInt((XSSFCell) row.getCell(index++));
					String attributeQuantity = PoiUtil.getString((XSSFCell) row.getCell(index++));
					String txt = PoiUtil.getString((XSSFCell) row.getCell(index++));

					CharactervalueTemplate charactervalueTemplate = new CharactervalueTemplate(id, rarity, weight,
							condition, attribute, attributeQuantity, txt);
					map.put(id, charactervalueTemplate);
				}
				i++;
			}
			logger.info(String.format("CharactervalueConfig config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
