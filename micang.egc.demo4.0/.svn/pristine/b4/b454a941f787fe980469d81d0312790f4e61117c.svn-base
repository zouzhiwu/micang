package com.game.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateStr(String pattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);// Date指定格式：yyyy-MM-dd HH:mm:ss:SSS
		Date date = new Date();// 创建一个date对象保存当前时间
		return simpleDateFormat.format(date);

	}
	
	
	public static void main(String[] args) {
		System.out.println(getDateStr("yyyy-MM-dd HH:mm:ss:SSS"));
	}

}
