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

import com.common.template.MailTemplate;
import com.game.util.PoiUtil;

public class MailConfig {
	private final static String sheetName = "mail";
	private static final Logger logger = LoggerFactory.getLogger(MailConfig.class);
	public static Map<Integer,MailTemplate> map = new HashMap<Integer,MailTemplate>();
	
	public static void init() {
		InputStream in = MailConfig.class.getClassLoader().getResourceAsStream("template/Mail.xlsx");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(in);
			XSSFSheet sheet = workBook.getSheet(sheetName);
			int i = 0;
			for (Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (i >= 2) {
					int index = 0;
					Integer id = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String Title = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String Name = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String Content = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String Param = PoiUtil.getString((XSSFCell)row.getCell(index++)).trim();
					String Reward = PoiUtil.getString((XSSFCell)row.getCell(index++)).trim();
					String RewardNum = PoiUtil.getString((XSSFCell)row.getCell(index++));              
					MailTemplate gachaTemplate = new MailTemplate(id, Title, Name, Content , Param, Reward, RewardNum);
					map.put(id, gachaTemplate);
				}
				i++;
			}
			logger.info(String.format("mail config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}