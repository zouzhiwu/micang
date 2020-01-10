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

import com.common.template.ChapterStarGiftTemplate;
import com.game.util.PoiUtil;

public class ChapterStarGiftConfig {
	

	private final static String sheetName = "Sheet1";
	private static final Logger logger = LoggerFactory.getLogger(ChapterConfig.class);
	public static Map<Integer, ChapterStarGiftTemplate> map = new HashMap<Integer, ChapterStarGiftTemplate>();
	
	public static void init() {
		InputStream in = ChapterConfig.class.getClassLoader().getResourceAsStream("template/ChapterStarGift.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer chapterId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String star = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String reward1 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String num1 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String reward2 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String num2 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String reward3 = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String num3 = PoiUtil.getString((XSSFCell)row.getCell(index++));

					ChapterStarGiftTemplate chapterStarGiftTemplate = new ChapterStarGiftTemplate(id, chapterId, star, reward1, num1, reward2,
																				num2, reward3, num3);
					
					map.put(chapterId, chapterStarGiftTemplate);
				}
				i++;
			}
			logger.info(String.format("chapter config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
