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

import com.common.template.HeroTemplate;
import com.game.util.PoiUtil;

public class HeroConfig {
	private final static String sheetName = "Hero";
	private static final Logger logger = LoggerFactory.getLogger(HeroConfig.class);
	public static Map<Integer, HeroTemplate> map = new HashMap<Integer, HeroTemplate>();
	
	public static void init() {
		InputStream in = HeroConfig.class.getClassLoader().getResourceAsStream("template/Hero.xlsx");
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
					Integer itemId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String icon = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Byte work = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Integer hp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer pa = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer ma = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer pd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer md = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short ats = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short aoe = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short mvs = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short vr = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer cd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short pcrt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short mcrt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short ppt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short mpt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short hi = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short hr = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer growthHp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthPa = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthMa = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthPd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthMd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short growthAts = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthAoe = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthMvs = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer growthCd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short growthPcrt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthMcrt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthPpt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthMpt = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthHi = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short growthHr = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer skillId = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String heroAbout = PoiUtil.getString((XSSFCell)row.getCell(index++));
					String description = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Integer heroEquip = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					String location = PoiUtil.getString((XSSFCell)row.getCell(index++));
					
					HeroTemplate heroTemplate = new HeroTemplate(id, name, itemId, icon, work, hp, pa, ma, pd, md,
							ats, aoe, mvs, cd, pcrt, mcrt, ppt, mpt,
							hi, hr, growthHp, growthPa, growthMa, growthPd,
							growthMd, growthAts, growthAoe, growthMvs, growthCd, growthPcrt,
							growthMcrt, growthPpt, growthMpt, growthHi, growthHr, skillId,
							heroAbout, description, heroEquip, location, vr);
					map.put(heroTemplate.getId(), heroTemplate);
				}
				i++;
			}
			logger.info(String.format("hero config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}