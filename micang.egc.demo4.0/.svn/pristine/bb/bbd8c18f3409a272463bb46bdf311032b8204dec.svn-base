package com.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.enumerate.CharacterSeat;
import com.common.template.CharacterTemplate;
import com.common.template.CharactervalueTemplate;
import com.game.config.CharacterConfig;
import com.game.config.CharactervalueConfig;
import com.game.entity.CharacterBean;
import com.game.util.CommonUtil;
import com.game.util.Params;
import com.game.util.ProbabilityExtract;

@Service
public class CharacterService {

	
	public CharacterBean getCharacterBean(String characters, CharacterSeat seat) {
		List<CharacterBean> list = JSONObject.parseArray(characters, CharacterBean.class);
		CharacterBean characterBean = null;
		Optional<CharacterBean> opt = list.stream().filter(p->p.getSeat().intValue() == seat.getIndex()).findFirst();
		if(opt.isPresent()) {
			characterBean = opt.get();
		}
		return characterBean;
	}
	
	
	
	public String createCharacter() {
		// 随机获取性格2个不同的性格ID
		int size = CharacterConfig.map.size();
		List<Integer> randList = ProbabilityExtract.getRandomList(size, 2);
		List<Integer> keyList = new ArrayList<Integer>();
		CharacterConfig.map.forEach((key, value) -> {
			keyList.add(key);
		});
		List<Integer> chaIdList = new ArrayList<Integer>();
		for (Integer ind : randList) {
			chaIdList.add(keyList.get(ind));
		}

		List<CharacterBean> list = new ArrayList<CharacterBean>();
		int seat = 1;
		for (Integer characterId : chaIdList) {
			CharacterBean characterBean = this.createCharacterBean(characterId, seat);
			list.add(characterBean);
			seat++;
		}
		return JSONObject.toJSONString(list);
	}
	
	
	
	public CharacterBean createCharacterBean(int characterId, int seat) {
		CharacterTemplate characterTemplate = CharacterConfig.map.get(characterId);
		int id1 = this.getCvId(characterTemplate.getProbability1(), characterTemplate.getCharacter1());
		int id2 = this.getCvId(characterTemplate.getProbability2(), characterTemplate.getCharacter2());
		CharacterBean characterBean = new CharacterBean();
		characterBean.setSeat(seat);
		characterBean.setCharacterId(characterId);
		characterBean.setCharactervalue1Id(id1);
		characterBean.setCharactervalue2Id(id2);
		return characterBean;
	}
	
	
	
	

	public int getCvId(String pro, String cha) {
		String[] arr = pro.split(Params.fenge);
		int[] prob1Arr = CommonUtil.stringToInt(arr);
		int index = getRandIndex(prob1Arr);

		int[] character1Arr = CommonUtil.stringToInt(cha.split(Params.fenge));
		int character1 = character1Arr[index];
		CharactervalueTemplate ct = this.getRandCv(character1);
		return ct.getId();
	}

	public CharactervalueTemplate getRandCv(int character1) {
		List<CharactervalueTemplate> valueList = new ArrayList<CharactervalueTemplate>();
		CharactervalueConfig.map.forEach((key, value) -> {
			if (value.getRarity().intValue() == character1) {
				valueList.add(value);
			}
		});
		int[] pro = new int[valueList.size()];
		int ii = 0;
		for (CharactervalueTemplate cv : valueList) {
			pro[ii] = cv.getWeight();
			ii++;
		}
		int index = this.getRandIndex(pro);
		return valueList.get(index);
	}

	public int getRandIndex(int[] prob1Arr) {
		int total = 0;
		for (int pro : prob1Arr) {
			total += pro;
		}
		Random random = new Random();
		int next = random.nextInt(total) + 1;

		int sum = 0;
		int index = 0;
		for (int i = 0; i < prob1Arr.length; i++) {
			if (next > sum && next <= (sum + prob1Arr[i])) {
				index = i;
				break;
			} else {
				sum += prob1Arr[i];
			}
		}
		return index;
	}

}
