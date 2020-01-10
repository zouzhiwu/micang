package com.game.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.template.ChapterTemplate;
import com.game.util.PoiUtil;

public class ChapterConfig {
	private final static String sheetName = "Sheet1";
	private static final Logger logger = LoggerFactory.getLogger(ChapterConfig.class);
	public static List<ChapterTemplate> list = new ArrayList<ChapterTemplate>();
	public static Map<Integer, ChapterTemplate> map = new HashMap<Integer, ChapterTemplate>();
	public static void init() {
		InputStream in = ChapterConfig.class.getClassLoader().getResourceAsStream("template/Chapter.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer difficulty = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String levelId = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String chapterNo = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String title = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String address = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String boxGift = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String num = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Byte unlock = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Integer chapterId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					ChapterTemplate chapterTemplate = new ChapterTemplate(id, difficulty, levelId, chapterNo, title, address, boxGift, num, unlock, chapterId);
					list.add(chapterTemplate);
					map.put(id, chapterTemplate);
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