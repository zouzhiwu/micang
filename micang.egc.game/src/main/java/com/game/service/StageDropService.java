package com.game.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.common.template.StageDropTemplate;
import com.game.config.StageDropConfig;
import com.game.util.Params;

@Service
public class StageDropService {

	/**
	 * 获取掉落道具
	 * @param stageDropId
	 * @return
	 */
	public Map<Integer, Integer> getDrop(Integer stageDropId) {
		StageDropTemplate stageDropTemplate = StageDropConfig.map.get(stageDropId);
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (stageDropTemplate == null) {
			return map;
		}
		String[] dropGroupArr = stageDropTemplate.getDropGroup().split(Params.fenge);
		String[] dropMinNumArr = stageDropTemplate.getDropMinNum().split(Params.fenge);
		String[] dropMaxNumArr = stageDropTemplate.getDropMaxNum().split(Params.fenge);
		String[] randDropGroupArr = stageDropTemplate.getRandDropGroup().split(Params.fenge);
		String[] randDropRateArr = stageDropTemplate.getRandDropRate().split(Params.fenge);
		String[] randDropMinNumArr = stageDropTemplate.getRandDropMinNum().split(Params.fenge);
		String[] randDropMaxNumArr = stageDropTemplate.getRandDropMaxNum().split(Params.fenge);

		Random random = new Random();
		if (!(dropGroupArr.length == 1 && Integer.valueOf(dropGroupArr[0]).intValue() == 0)) {
			for (int i = 0; i < dropGroupArr.length; i++) {
				int next = Integer.valueOf(dropMaxNumArr[i])
						+ random.nextInt(Integer.valueOf(dropMaxNumArr[i]) - Integer.valueOf(dropMinNumArr[i]));
				map.put(Integer.valueOf(dropGroupArr[i]), next);
			}

		}

		if (!(randDropGroupArr.length == 1 && Integer.valueOf(randDropGroupArr[0]).intValue() == 0)) {
			for (int i = 0; i < randDropGroupArr.length; i++) {
				int rate = random.nextInt(1000) + 1;
				boolean flag = false;
				if (i == 0 && (rate <= Integer.valueOf(randDropRateArr[i]))) {
					flag = true;
				} else if (rate >= (Integer.valueOf(randDropRateArr[i - 1]) + 1)
						&& rate <= Integer.valueOf(randDropRateArr[i])) {
					flag = true;
				}
				if (flag) {
					int next = Integer.valueOf(randDropMinNumArr[i]) + random
							.nextInt(Integer.valueOf(randDropMaxNumArr[i]) - Integer.valueOf(randDropMinNumArr[i]));
					map.put(Integer.valueOf(randDropGroupArr[i]), next);
				}

			}
		}
		return map;
	}

}
