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

import com.common.template.DropGroupTemplate;
import com.game.util.Params;
import com.game.util.PoiUtil;

public class DropGroupConfig {
	private final static String sheetName = "Sheet1";
	private static final Logger logger = LoggerFactory.getLogger(DropGroupConfig.class);
	public static Map<Integer,DropGroupTemplate> map = new HashMap<Integer,DropGroupTemplate>();
	
	public static void init() {
		InputStream in = DropGroupConfig.class.getClassLoader().getResourceAsStream("template/DropGroup.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			Map<Integer, Integer> groupProbability = null;
			Map<Integer, Integer> itemGroupMap = null;
			Map<Integer, Integer> memberGroupMap = null;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String dropRate = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String itemGroup = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String itemGroupRate = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String memberGroup = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String memberGroupRate = PoiUtil.getString((XSSFCell)row.getCell(index++));
					groupProbability = new HashMap<Integer, Integer>();
					itemGroupMap = new HashMap<Integer, Integer>();
					memberGroupMap = new HashMap<Integer, Integer>();
					String[] split = dropRate.split(Params.fenge);
					String[] split2 = itemGroup.split(Params.fenge);
					String[] split3 = itemGroupRate.split(Params.fenge);
					String[] split4 = memberGroup.split(Params.fenge);
					String[] split5 = memberGroupRate.split(Params.fenge);
					for (int j = 1; j <= split.length; j++) {
						groupProbability.put(j, new Integer(split[j-1].trim()));
					}
					for (int j = 0; j < split2.length; j++) {
						itemGroupMap.put(new Integer(split2[j].trim()), new Integer(split3[j].trim()));
					}
					for (int j = 0; j < split4.length; j++) {
						memberGroupMap.put(new Integer(split4[j]), new Integer(split5[j]));
					}
					DropGroupTemplate gachaTemplate = new DropGroupTemplate(id, groupProbability, itemGroupMap, memberGroupMap);
					map.put(id, gachaTemplate);
				}
				i++;
			}
			logger.info(String.format("DropGroup config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}