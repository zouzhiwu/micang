package com.game.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;

/**
 * 根据概率选取集合中的一条数据
 * 
 * @gf
 *
 */
public final class ProbabilityExtract {

	private int[] indexList;
	private int count;

	public ProbabilityExtract(List<Integer> list) {
		indexList = new int[list.size()];
		for (int i = 0; i < indexList.length; i++) {
			if (i > 0) {
				indexList[i] = list.get(i) + indexList[i - 1];
			} else {
				indexList[i] = list.get(i);
			}
			count = count + list.get(i);
		}
	}

	public int next() {
		int nextInt = RandomUtils.nextInt(0, count);
		int index = 0;
		for (int i = 0; i < indexList.length; i++) {
			if (indexList[i] > nextInt) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static Integer outPrize(Map<Integer, Integer> map) {
		List<Integer> list = new ArrayList<Integer>(map.values());
		List<Integer> gifts = new ArrayList<Integer>(map.keySet());
		ProbabilityExtract method = new ProbabilityExtract(list);
		Integer integer = gifts.get(method.next());
		return integer;
	}

//	public static List<Integer> getRandomList(int total, int count) {
//		List<Integer> list = new Random().ints(0, total).distinct().limit(count).boxed().collect(Collectors.toList());
//		return list;
//	}
	
	/**
	 * 随机不重复数组 distinct
	 * @param total
	 * @param count
	 * @return
	 */
	public static List<Integer> getRandomList(int total, int count) {
		if(count>total) {
			count = total;
		}
		Integer[] arr = new Integer[total];
		for (int i = 0; i < total; i++) {
			arr[i] = i;
		}
		Random r = new Random();
		for (int i = 0; i < total; i++) {
			int in = r.nextInt(total - i) + i;
			int t = arr[in];
			arr[in] = arr[i];
			arr[i] = t;
		}
		return Arrays.asList(arr).subList(0, count);
	}

}