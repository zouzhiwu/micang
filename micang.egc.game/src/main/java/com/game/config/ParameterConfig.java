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

import com.common.constant.ParameterIdConstant;
import com.common.template.ParameterTemplate;
import com.game.util.PoiUtil;

public class ParameterConfig {
	private final static String sheetName = "Parameter";
	private static final Logger logger = LoggerFactory.getLogger(ParameterConfig.class);
	public static Map<Integer, ParameterTemplate> map = new HashMap<Integer, ParameterTemplate>();
	public static List<Object[]> killHeroCoefficient = new ArrayList<Object[]>();
	// 游戏时间与现实时间比例
	public static int timeProportion;
	
	public static void init() {
		InputStream in = ParameterConfig.class.getClassLoader().getResourceAsStream("template/Parameter.xlsx");
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
					String value = PoiUtil.getString((XSSFCell)row.getCell(index++));
					
					ParameterTemplate template = new ParameterTemplate(id, name, value);
					map.put(template.getId(), template);
					if (id == ParameterIdConstant.exp_list) {
						killHeroCoefficient = analysisKillHeroCoefficient(value);
					} else if (id == ParameterIdConstant.game_time_real_time) {
						timeProportion = Integer.parseInt(template.getValue());
					}
				}
				i++;
			}
			logger.info(String.format("node config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Object[]> analysisKillHeroCoefficient(String value) {
		String[] arrays = value.split("\\|");
		List<Object[]> objsList = new ArrayList<Object[]>();
		for (String array : arrays) {
			String[] strs = array.split("\\,");
			String levelDiff = strs[0];
			String conf = strs[1];
			Object[] objs = new Object[] {Integer.parseInt(levelDiff), Float.parseFloat(conf)};
			objsList.add(objs);
		}
		return objsList;
	}
	
}