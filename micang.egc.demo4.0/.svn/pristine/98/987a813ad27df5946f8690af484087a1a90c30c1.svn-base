package com.json.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringArrayDemo {
	public static List<Object> analyze2Array(String msg) {
		Pattern pattern = Pattern.compile("\\[(.*?)\\]");
		Matcher matcher = pattern.matcher(msg);
		if (matcher.find()) {
			do {
				String txt = matcher.group();
				StringBuilder sb = new StringBuilder(txt);
				sb = sb.deleteCharAt(0).deleteCharAt(sb.length() - 1);
				String content = sb.toString();
				matcher = pattern.matcher(content);
			} while (matcher.find());
			
			String txt = matcher.group();
			StringBuilder sb = new StringBuilder(txt);
			sb = sb.deleteCharAt(0).deleteCharAt(sb.length() - 1);
			String content = sb.toString();
			matcher = pattern.matcher(content);
			if (matcher.find()) {
				List<Object> list = new ArrayList<Object>();
				do {
					List<Object> subList = analyze2Array(matcher.group(0));
					list.add(subList);
				} while (matcher.find());
				return list;
			} else {
				Object[] strArray = content.split(",");
				List<Object> list = Arrays.asList(strArray);
				return list;
			}
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception {
		String msg = "[[1,2,3],[4,5,6]]";
		List<Object> list = analyze2Array(msg);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
//	public static void main(String[] args) {
//		String text="[1,2,3],[4,5,6]";
//        text=text.replaceAll(" ", "");
//        Pattern p=Pattern.compile("\\[(.*?)\\]");
//        Matcher m=p.matcher(text);
//        while(m.find()){
//        	System.out.println(m.group());
//        }
//	}
}
