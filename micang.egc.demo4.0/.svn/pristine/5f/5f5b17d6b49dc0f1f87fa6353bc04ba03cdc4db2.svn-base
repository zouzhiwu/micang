package com.game.config;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.common.entity.Location;
import com.common.enumerate.MapNodeType;
import com.common.util.MapUtil;

@Component
public class MapConfig {
	private final static Logger logger = LoggerFactory.getLogger(MapConfig.class);
	/**英雄自动寻路地图(白+灰+黑) **/
	public static byte[][] whiteMap;
	/**小兵自动寻路地图(白+黑) **/
	public static byte[][] grayMap;
	public static final List<Location> locationList = new ArrayList<Location>();
	
	@PostConstruct
	public static void init() {
		logger.info("load map...");
		loadData("template/map.bmp");
	}
	
	private static void loadData(String fileName) {
		InputStream in = MapConfig.class.getClassLoader().getResourceAsStream(fileName);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte srcWidth = (byte)bi.getWidth();
		byte srcHeight = (byte)bi.getHeight();
		byte minx = (byte)bi.getMinX();
		byte miny = (byte)bi.getMinY();
		whiteMap = MapUtil.initMap(srcWidth, srcHeight);
		grayMap = MapUtil.initMap(srcWidth, srcHeight);
		for (byte i = miny; i < srcHeight; i++) {
			for (byte j = minx; j < srcWidth; j++) {
				int pixel = bi.getRGB(j, i);
				Color color = new Color(pixel);
				MapNodeType nodeType = MapUtil.toNodeType(color);
				if (nodeType == null) {
					logger.error(String.format("第%d行第%d列错误", j, i));
				} else if (nodeType == MapNodeType.White) {
					whiteMap[i][j] = nodeType.getIndex();
				} else if (nodeType == MapNodeType.Gray) {
					whiteMap[i][j] = MapNodeType.White.getIndex();
					grayMap[i][j] = MapNodeType.Gray.getIndex();
				}
				Location location = new Location(j, i);
				locationList.add(location);
			}
		}
//		MapUtil.print(whiteMap);
	}
	
	public static boolean isRoad(float x, float z) {
		int intx = (int)Math.ceil(x * 10);
		int intz = (int)Math.ceil(z * 10);
		byte value = whiteMap[intz][intx];
		MapNodeType nodeType = MapNodeType.getType(value);
		return nodeType == MapNodeType.White || nodeType == MapNodeType.Gray;
	}
}