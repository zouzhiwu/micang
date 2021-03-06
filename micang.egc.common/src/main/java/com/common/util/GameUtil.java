package com.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.common.constant.FightConstant;
import com.common.entity.Location;
import com.common.entity.Node;
import com.common.enumerate.CampType;
import com.common.enumerate.WayType;

public class GameUtil {
	/**
	 * 四舍五入，保留num位小数点
	 */
	public static float round(double f, int num) {
		double pow = Math.pow(10, num);
		return (float)(Math.round(f * pow) / pow);
	}
	
	public static float round2(double f) {
		return round(f, 2);
	}
	
	/**
	 * 两点的实际距离
	 */
	public static float distance(Location p1, Location p2) {
		double d = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
//		double distance = round(d, 2);
		return (float)d;
	}
	
	public static boolean isInCircle(Location p0, float r, Location p) {
		return r > distance(p0, p);
	}
	
	public static double angle(Location p1, Location p0, Location p2) {
		double angle1 = angle(p0, p1);
		double angle2 = angle(p0, p2);
		return Math.abs((angle1 - angle2) * (180 / Math.PI));
	}
	
	public static double angle(Location p1, Location p2) {
		return Math.atan2(p2.y - p1.y, p2.x - p1.x);
	}
	
	public static double angle(Node node1, Node node2) {
		return Math.atan2(node2.coord.y - node1.coord.y, node2.coord.x - node1.coord.x);
	}
	
	public static int[] string2Ints(String value) {
	    int[] intArr = new int[0];
	    if(StringUtils.isEmpty(value)){
	        intArr = new int[0];
	    }else{
	        String[] valueArr = value.split(",");
	        intArr = new int[valueArr.length];
	        for (int i = 0; i < valueArr.length; i++) {
	            intArr[i] = Integer.parseInt(valueArr[i]);
	        }
	    }
	    return intArr;
	}
	
	public static List<Location> getWayList(Location currLocation, CampType myCampType, WayType wayType) {
		List<Location> srcLocationList = null;
		switch (wayType) {
		case Top:
			srcLocationList = FightConstant.topWay;
			break;
		case Mid:
			srcLocationList = FightConstant.midWay;
			break;
		case Down:
			srcLocationList = FightConstant.downWay;
			break;
		}
		List<Location> destLocationList = new ArrayList<Location>();
		if (myCampType == CampType.Blue) {
			destLocationList.addAll(srcLocationList);
		} else {
			for (int i = srcLocationList.size() - 1; i >= 0; i--) {
				Location location = srcLocationList.get(i);
				destLocationList.add(location);
			}
		}
		float d = distance(currLocation, destLocationList.get(0));
		int index = 0;
		for (int i = 1; i < destLocationList.size(); i++) {
			float d1 = distance(currLocation, destLocationList.get(i));
			if (d1 > d) {
				break;
			} else {
				d = d1;
				index = i;
			}
		}
		List<Location> locationList = new ArrayList<Location>();
		for (int i = index; i < destLocationList.size(); i++) {
			locationList.add(destLocationList.get(i));
		}
		return locationList;
	}
}
