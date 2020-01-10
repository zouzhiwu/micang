package com.json.test;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class FF {

	public static void main(String args[]){
		String reg="\\[+([a-z]{1,}|_)+[a-z]{1,}+\\]";
		//只用reg2可以匹配两个括号的内容
		//String reg2="\\[\\[+([a-z]{1,}|_)+[a-z]{1,}+\\]\\]"
		//这里的content就是要用来匹配的数据
		String content="[[world],[hello]]";
		//以逗号分隔
		String arr[]=content.split(",");
		for(int i=0;i<arr.length;i++){
			Pattern pattern=Pattern.compile(reg);
			Matcher matcher=pattern.matcher(arr[i]);
			if(matcher.find()){
			//得到第一次循环的内容，判断里面还有中括号没有
			String target1=matcher.group();
			if(target1.contains("[")){
				//把所有的方括号替换掉，就得到想要的数据了
				target1=target1.replace("[","");
				target1=target1.replace("]","");
			}
			System.out.println(target1);
			}
		}
	}
}