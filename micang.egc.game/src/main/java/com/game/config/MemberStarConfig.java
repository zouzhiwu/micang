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

import com.common.template.MemberStarTemplate;
import com.game.util.PoiUtil;

public class MemberStarConfig {

	private final static String sheetName = "Member_star";
	private static final Logger logger = LoggerFactory.getLogger(MemberStarConfig.class);
	public static Map<Integer, MemberStarTemplate> map = new HashMap<Integer, MemberStarTemplate>();
	
	public static void init() {
		InputStream in = MemberStarConfig.class.getClassLoader().getResourceAsStream("template/Member_star.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer memberId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer consume = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short y1 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v1 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y2 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v2 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y3 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v3 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y4 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v4 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y5 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v5 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y6 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v6 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y7 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v7 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y8 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v8 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y9 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v9 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y10 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short v10 = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer relation = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					
					MemberStarTemplate MemberStarTemplate = new MemberStarTemplate(id, memberId, consume, y1, v1, y2, v2, y3, v3,
																		y4, v4, y5, v5, y6, v6, y7, v7, y8, v8, y9, v9, y10, v10, relation);
					
					map.put(MemberStarTemplate.getMemberId(), MemberStarTemplate);
				}
				i++;
			}
			logger.info(String.format("MemberStarTemplate config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
