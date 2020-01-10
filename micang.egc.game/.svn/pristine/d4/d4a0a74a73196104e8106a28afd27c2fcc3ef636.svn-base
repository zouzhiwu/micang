package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.common.entity.Location;
import com.common.entity.Node;
import com.common.enumerate.MapNodeType;
import com.common.util.MapUtil;
import com.game.config.MapConfig;

@Service
public class MapService {
	private static final Logger logger = LoggerFactory.getLogger(MapService.class);	
	
	public static List<Location> searchWay(Location startPngLocation, Location endPngLocation, MapNodeType mapNodeType) {
		logger.info(String.format("searchWay startPngLocation=%s endPngLocation=%s", startPngLocation, endPngLocation));
		Location startBitLocation = MapUtil.pngmap2bitmap(startPngLocation);
		Location endBitLocation = MapUtil.pngmap2bitmap(endPngLocation);
		logger.info(String.format("searchWay start=%s end=%s", startBitLocation, endBitLocation));
		List<Location> bitLocationList;
		if (mapNodeType == MapNodeType.White) {
			bitLocationList = searchWay(startBitLocation, endBitLocation, MapConfig.whiteMap);
		} else {
			bitLocationList = searchWay(startBitLocation, endBitLocation, MapConfig.grayMap);
		}
		List<Location> pngLocationList = MapUtil.bitmap2pngmap(bitLocationList);
		if (pngLocationList.size() > 0) {
			Location lastLocation = pngLocationList.get(pngLocationList.size() - 1);
			// 如果最后一个位置和目标位置不一样，则添加进去
			if (!lastLocation.equals(endPngLocation)) {
				pngLocationList.add(endPngLocation);
			}
		} else {
			if (!startPngLocation.equals(endPngLocation)) {
				pngLocationList.add(endPngLocation);
			}
		}
		return pngLocationList;
	}
	
	public static List<Location> searchWay(Location startBitLocation, Location endBitLocation, byte[][] map) {
		if (map[(short)startBitLocation.y][(short)startBitLocation.x] == MapNodeType.Black.getIndex()) {
			startBitLocation = changeLocation(startBitLocation, map);
		}
		if (map[(short)endBitLocation.y][(short)endBitLocation.x] == MapNodeType.Black.getIndex()) {
			endBitLocation = changeLocation(endBitLocation, map);
		}
		List<Node> nodeList = AStarService.searchWay((short)startBitLocation.x, (short)startBitLocation.y, (short)endBitLocation.x, (short)endBitLocation.y, map);
		List<Location> locationList = new ArrayList<Location>();
		for (int i = 0; i < nodeList.size(); i++) {
			Node node = nodeList.get(i);
			Location location = new Location((short)(node.coord.x), (short)(node.coord.y));
			locationList.add(location);
		}
		return locationList;
	}
	
	/**
	 * 如果该点是障碍物，那么在该点附近[-1,1]区间范围查找不是障碍物的点返回
	 * 如果没有找到，则把原点返回
	 * @param bitLocation 位图节点
	 * @param map 位图地图
	 * @return
	 */
	public static Location changeLocation(Location bitLocation, byte[][] map) {
		// 增量列表
		List<Integer[]> addValueList = new ArrayList<Integer[]>(8);
		addValueList.add(new Integer[] {-1, 0});
		addValueList.add(new Integer[] {-1, 1});
		addValueList.add(new Integer[] {-1, -1});
		addValueList.add(new Integer[] {0, 1});
		addValueList.add(new Integer[] {0, -1});
		addValueList.add(new Integer[] {1, 0});
		addValueList.add(new Integer[] {1, 1});
		addValueList.add(new Integer[] {1, -1});
		for (int i = 0; i < addValueList.size(); i++) {
			Integer[] addValue = addValueList.get(i);
			int addx = addValue[0];
			int addy = addValue[1];
			if (bitLocation.y + addy > 0 && bitLocation.x + addx > 0) {
				if (map.length > bitLocation.y + addy && map[(short)bitLocation.y + addy].length > bitLocation.x + addx) {
					if (map[(short)bitLocation.y + addy][(short)bitLocation.x + addx] != MapNodeType.Black.getIndex()) {
						short x = (short)(bitLocation.x + addx);
						short y = (short)(bitLocation.y + addy);
						return new Location(x, y);
					}
				}
			}
		}
		return bitLocation;
	}
}
