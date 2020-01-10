package com.game.util;

import com.common.entity.Lvxp;
import com.common.enumerate.LevelType;
import com.common.template.LevelTemplate;
import com.game.config.LevelConfig;

public class LevelUtil {

	public static int getLevel(LevelType levelType, int exp) {
		Lvxp lvxp = getLvxp(levelType, exp);
		return lvxp.getLevel();
	}

	public static Lvxp getLvxp(LevelType levelType, int exp) {
		int lv = 0;
		int xp = exp;
		for (int i = 1; i < LevelConfig.map.size(); i++) {
			LevelTemplate levelTemplate = LevelConfig.map.get(i);

			if (levelType.getIndex() == levelTemplate.getType().byteValue()) {
				lv++;
				if (xp < levelTemplate.getExp() || levelTemplate.getExp() == 0) {
					break;
				} else {
					xp -= levelTemplate.getExp();
				}
			}
		}
		Lvxp lvxp = new Lvxp(lv, xp);
		return lvxp;
	}

	public static int getMaxLv(LevelType levelType) {
		int maxLv = 0;
		for (int i = 1; i < LevelConfig.map.size(); i++) {
			LevelTemplate levelTemplate = LevelConfig.map.get(i);
			if (levelType.getIndex() == levelTemplate.getType().byteValue()) {
				maxLv++;
				if (levelTemplate.getExp() == 0) {
					break;
				}
			}
		}
		return maxLv;
	}
}
