package com.common.util;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class ChatUtil {
	public static String sortHeroIds(String heroId1, String heroId2) {
		if (StringUtils.isNotBlank(heroId1) && StringUtils.isNotBlank(heroId2)) {
			String[] heroArray = new String[]{heroId1, heroId2};
			Arrays.sort(heroArray);
			return heroArray[0] + "_" + heroArray[1];
		} else {
			return StringUtils.EMPTY;
		}
		
	}
}
