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

import com.common.enumerate.ArmsType;
import com.common.enumerate.CampType;
import com.common.enumerate.DoorCode;
import com.common.enumerate.MonsterType;
import com.common.enumerate.NodeType;
import com.common.enumerate.TowerCode;
import com.common.enumerate.WayType;
import com.common.template.NodeTemplate;
import com.game.util.PoiUtil;

public class NodeConfig {
	private final static String sheetName = "Node";
	private static final Logger logger = LoggerFactory.getLogger(NodeConfig.class);
	public static Map<Integer, NodeTemplate> map = new HashMap<Integer, NodeTemplate>();
	/**蓝方基地*/
	public static NodeTemplate buleHomeNodeTemplate;
	/**红方基地*/
	public static NodeTemplate redHomeNodeTemplate;
	
	/**蓝方上路水晶*/
	public static NodeTemplate buleTopCrystalNodeTemplate;
	/**蓝方中路水晶*/
	public static NodeTemplate buleMidCrystalNodeTemplate;
	/**蓝方下路水晶*/
	public static NodeTemplate buleDownCrystalNodeTemplate;
	/**红方上路水晶*/
	public static NodeTemplate redTopCrystalNodeTemplate;
	/**红方中路水晶*/
	public static NodeTemplate redMidCrystalNodeTemplate;
	/**红方下路水晶*/
	public static NodeTemplate redDownCrystalNodeTemplate;
	
	/**蓝方小兵*/
	public static NodeTemplate buleSmallNodeTemplate;
	/**蓝方炮兵*/
	public static NodeTemplate buleBigNodeTemplate;
	/**蓝方超级兵*/
	public static NodeTemplate buleSuperNodeTemplate;
	/**红方小兵*/
	public static NodeTemplate redSmallNodeTemplate;
	/**红方炮兵*/
	public static NodeTemplate redBigNodeTemplate;
	/**红方超级兵*/
	public static NodeTemplate redSuperNodeTemplate;
	
	/**小龙*/
	public static NodeTemplate samllDragonTemplate;
	/**大龙*/
	public static NodeTemplate bigDragonTemplate;
	
	/**蓝方野区巨鸟怪*/
	public static NodeTemplate buleBirdNodeTemplate;
	/**蓝方野区蛤蟆怪*/
	public static NodeTemplate buleToadNodeTemplate;
	/**蓝方野区小狼怪*/
	public static NodeTemplate buleWolfNodeTemplate;
	/**蓝方野区石头人*/
	public static NodeTemplate buleStoneNodeTemplate;
	/**蓝方野区红爸爸*/
	public static NodeTemplate buleRbuffNodeTemplate;
	/**蓝方野区蓝爸爸*/
	public static NodeTemplate buleBbuffNodeTemplate;
	
	/**红方野区巨鸟怪*/
	public static NodeTemplate redBirdNodeTemplate;
	/**红方野区蛤蟆怪*/
	public static NodeTemplate redToadNodeTemplate;
	/**红方野区小狼怪*/
	public static NodeTemplate redWolfNodeTemplate;
	/**红方野区石头人*/
	public static NodeTemplate redStoneNodeTemplate;
	/**红方野区红爸爸*/
	public static NodeTemplate redRbuffNodeTemplate;
	/**红方野区蓝爸爸*/
	public static NodeTemplate redBbuffNodeTemplate;
	
	/**蓝方门牙塔1*/
	public static NodeTemplate buleDoor1NodeTemplate;
	/**蓝方门牙塔2*/
	public static NodeTemplate buleDoor2NodeTemplate;
	/**蓝方上路防御塔1*/
	public static NodeTemplate buleTopTower1NodeTemplate;
	/**蓝方上路防御塔2*/
	public static NodeTemplate buleTopTower2NodeTemplate;
	/**蓝方上路防御塔3*/
	public static NodeTemplate buleTopTower3NodeTemplate;
	/**蓝方中路防御塔1*/
	public static NodeTemplate buleMidTower1NodeTemplate;
	/**蓝方中路防御塔2*/
	public static NodeTemplate buleMidTower2NodeTemplate;
	/**蓝方中路防御塔3*/
	public static NodeTemplate buleMidTower3NodeTemplate;
	/**蓝方下路防御塔1*/
	public static NodeTemplate buleDownTower1NodeTemplate;
	/**蓝方下路防御塔2*/
	public static NodeTemplate buleDownTower2NodeTemplate;
	/**蓝方下路防御塔3*/
	public static NodeTemplate buleDownTower3NodeTemplate;
	
	/**红方门牙塔1*/
	public static NodeTemplate redDoor1NodeTemplate;
	/**红方门牙塔2*/
	public static NodeTemplate redDoor2NodeTemplate;
	/**红方上路防御塔1*/
	public static NodeTemplate redTopTower1NodeTemplate;
	/**红方上路防御塔2*/
	public static NodeTemplate redTopTower2NodeTemplate;
	/**红方上路防御塔3*/
	public static NodeTemplate redTopTower3NodeTemplate;
	/**红方中路防御塔1*/
	public static NodeTemplate redMidTower1NodeTemplate;
	/**红方中路防御塔2*/
	public static NodeTemplate redMidTower2NodeTemplate;
	/**红方中路防御塔3*/
	public static NodeTemplate redMidTower3NodeTemplate;
	/**红方下路防御塔1*/
	public static NodeTemplate redDownTower1NodeTemplate;
	/**红方下路防御塔2*/
	public static NodeTemplate redDownTower2NodeTemplate;
	/**红方下路防御塔3*/
	public static NodeTemplate redDownTower3NodeTemplate;
	
	public static void init() {
		InputStream in = NodeConfig.class.getClassLoader().getResourceAsStream("template/Node.xlsx");
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
					String icon = PoiUtil.getString((XSSFCell)row.getCell(index++));
					Byte type = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte campType = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte wayType = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte armsType = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte monsterType = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte towerCode = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Byte skillTarget = PoiUtil.getByte((XSSFCell)row.getCell(index++));
					Integer hp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer pa = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer ma = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer pd = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer md = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short ats = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short aoe = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short vr = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short mvs = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short gold = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short exp = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Integer growthHP = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthPA = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthMA = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthGold = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthExp = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer buff = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer growthInterval = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Integer endGrowthTime = PoiUtil.getInt((XSSFCell)row.getCell(index++));
					Short x = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					Short y = PoiUtil.getShort((XSSFCell)row.getCell(index++));
					
					NodeTemplate template = new NodeTemplate(id, name, icon, type, campType, wayType, armsType, monsterType, towerCode, skillTarget, hp, pa, ma
							, pd, md, ats, aoe, vr, mvs, gold, exp, growthHP, growthPA, growthMA, growthGold, growthExp, buff
							, growthInterval, endGrowthTime, x, y);
					map.put(template.getId(), template);
					// 分类
					doCategory(template);
				}
				i++;
			}
			logger.info(String.format("node config loaded record %d", i - 2));
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void doCategory(NodeTemplate nodeTemplate) {
		NodeType nodeType = NodeType.getType(nodeTemplate.getType());
		CampType campType = CampType.getType(nodeTemplate.getCampType());
		if (nodeType == NodeType.Home) {
			if (campType == CampType.Blue) {
				buleHomeNodeTemplate = nodeTemplate;
			} else {
				redHomeNodeTemplate = nodeTemplate;
			}
		} else if (nodeType == NodeType.Crystal) {
			WayType wayType = WayType.getType(nodeTemplate.getWayType());
			if (campType == CampType.Blue) {
				switch (wayType) {
				case Top:
					buleTopCrystalNodeTemplate = nodeTemplate;
					break;
				case Mid:
					buleMidCrystalNodeTemplate = nodeTemplate;						
					break;
				case Down:
					buleDownCrystalNodeTemplate = nodeTemplate;
					break;
				}
			} else if (campType == CampType.Red) {
				switch (wayType) {
				case Top:
					redTopCrystalNodeTemplate = nodeTemplate;
					break;
				case Mid:
					redMidCrystalNodeTemplate = nodeTemplate;
					break;
				case Down:
					redDownCrystalNodeTemplate = nodeTemplate;
					break;
				}
			}
		} else if (nodeType == NodeType.Soldier) {
			ArmsType armsType = ArmsType.getType(nodeTemplate.getArmsType());
			if (campType == CampType.Blue) {
				switch (armsType) {
				case Small:
					buleSmallNodeTemplate = nodeTemplate;
					break;
				case Big:
					buleBigNodeTemplate = nodeTemplate;
					break;
				case Super:
					buleSuperNodeTemplate = nodeTemplate;
					break;
				}
			} else if (campType == CampType.Red) {
				switch (armsType) {
				case Small:
					redSmallNodeTemplate = nodeTemplate;
					break;
				case Big:
					redBigNodeTemplate = nodeTemplate;
					break;
				case Super:
					redSuperNodeTemplate = nodeTemplate;
					break;
				}
			}
		} else if (nodeType == NodeType.Monster) {
			MonsterType monsterType = MonsterType.getType(nodeTemplate.getMonsterType());
			switch (monsterType) {
			case SamllDragon:
				samllDragonTemplate = nodeTemplate;
				break;
			case BigDragon:
				bigDragonTemplate = nodeTemplate;
				break;
			case BlueBird:
				buleBirdNodeTemplate = nodeTemplate;
				break;
			case BlueToad:
				buleToadNodeTemplate = nodeTemplate;
				break;
			case BlueWolf:
				buleWolfNodeTemplate = nodeTemplate;
				break;
			case BlueStone:
				buleStoneNodeTemplate = nodeTemplate;
				break;
			case BlueRbuff:
				buleRbuffNodeTemplate = nodeTemplate;
				break;
			case BlueBbuff:
				buleBbuffNodeTemplate = nodeTemplate;
				break;
			case RedBird:
				redBirdNodeTemplate = nodeTemplate;
				break;
			case RedToad:
				redToadNodeTemplate = nodeTemplate;
				break;
			case RedWolf:
				redWolfNodeTemplate = nodeTemplate;
				break;
			case RedStone:
				redStoneNodeTemplate = nodeTemplate;
				break;
			case RedRbuff:
				redRbuffNodeTemplate = nodeTemplate;
				break;
			case RedBbuff:
				redBbuffNodeTemplate = nodeTemplate;
				break;
			default:
				break;
			}
		} else if (nodeType == NodeType.Tower) {
			TowerCode towerCode = TowerCode.getType(nodeTemplate.getTowerCode());
			WayType wayType = WayType.getType(nodeTemplate.getWayType());
			if (campType == CampType.Blue) {
				if (wayType == WayType.Top) {
					switch (towerCode) {
					case Tower1:
						buleTopTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						buleTopTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						buleTopTower3NodeTemplate = nodeTemplate;
						break;
					}
				} else if (wayType == WayType.Mid) {
					switch (towerCode) {
					case Tower1:
						buleMidTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						buleMidTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						buleMidTower3NodeTemplate = nodeTemplate;
						break;
					}
				} else if (wayType == WayType.Down) {
					switch (towerCode) {
					case Tower1:
						buleDownTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						buleDownTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						buleDownTower3NodeTemplate = nodeTemplate;
						break;
					}
				}
			} else {
				if (wayType == WayType.Top) {
					switch (towerCode) {
					case Tower1:
						redTopTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						redTopTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						redTopTower3NodeTemplate = nodeTemplate;
						break;
					}
				} else if (wayType == WayType.Mid) {
					switch (towerCode) {
					case Tower1:
						redMidTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						redMidTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						redMidTower3NodeTemplate = nodeTemplate;
						break;
					}
				} else if (wayType == WayType.Down) {
					switch (towerCode) {
					case Tower1:
						redDownTower1NodeTemplate = nodeTemplate;
						break;
					case Tower2:
						redDownTower2NodeTemplate = nodeTemplate;
						break;
					case Tower3:
						redDownTower3NodeTemplate = nodeTemplate;
						break;
					}
				}
			}
		} else if (nodeType == NodeType.Door) {
			DoorCode doorCode = DoorCode.getType(nodeTemplate.getTowerCode());
			if (campType == CampType.Blue) {
				switch (doorCode) {
				case Door1:
					buleDoor1NodeTemplate = nodeTemplate;
					break;
				case Door2:
					buleDoor2NodeTemplate = nodeTemplate;
					break;
				}
			} else {
				switch (doorCode) {
				case Door1:
					redDoor1NodeTemplate = nodeTemplate;
					break;
				case Door2:
					redDoor2NodeTemplate = nodeTemplate;
					break;
				}
			}
		}
	}
	
}