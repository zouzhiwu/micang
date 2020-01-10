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

import com.common.template.GachaTemplate;
import com.game.util.Params;
import com.game.util.PoiUtil;

public class GachaConfig {
	private final static String sheetName = "Sheet1";
	private static final Logger logger = LoggerFactory.getLogger(GachaConfig.class);
	public static Map<Integer,GachaTemplate> map = new HashMap<Integer,GachaTemplate>();
	
	public static void init() {
		InputStream in = GachaConfig.class.getClassLoader().getResourceAsStream("template/gacha.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			Map<Integer, Integer> jackpot = null;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short currencyType = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer needCurrency = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short gachaTimes = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					String dropGroupArray = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String rateArray = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Integer freeGift = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					jackpot = new HashMap<Integer, Integer>();
					String[] dropGroup = dropGroupArray.split(Params.fenge);
					String[] rate = rateArray.split(Params.fenge);
					for (int temp = 0 ;temp < rate.length;temp++) {
						jackpot.put(new Integer(dropGroup[temp]), new Integer(rate[temp]));
					}
					GachaTemplate gachaTemplate = new GachaTemplate(id, currencyType, needCurrency, gachaTimes, jackpot , freeGift);
					map.put(id, gachaTemplate);
				}
				i++;
			}
			logger.info(String.format("gacha config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}