package com.game.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.util.CBUtils;
import com.common.enumerate.ProfileType;
import com.common.util.AssertUtil;
import com.game.config.AppConfig;
import com.game.config.ItemConfig;
import com.game.dao.ItemDao;
import com.game.entity.Bag;
import com.game.entity.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;
	
//	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);
	/** 初始化背包 **/
	public void init(Long accountId) {
		Bag bag = new Bag();
		bag.setId(accountId);
		Map<Integer, Item> itenMap = new HashMap<Integer, Item>();
		if (AppConfig.getProfile() != ProfileType.Production) {

			ItemConfig.map.forEach((key, value) -> {
				if (key > 1000) {
					itenMap.put(key, new Item(key, 1000));
				}
			});
			bag.setItemBytes(CBUtils.getBytesFromObject(itenMap));
		}
		itemDao.create(bag);
	}
	
	
	/**
	 * 查询用户背包
	 */
	public Bag getItemList(Long accountId) {
		Bag bag = itemDao.findById(accountId);
		if (bag == null) {
			init(accountId);
			bag = itemDao.findById(accountId);
		}
		return  bag;
	}
	
	/**
	 * 使用单个道具
	 * @param templateId
	 * @param count
	 * @param accountId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Item useItem(Integer templateId, Integer count, Long accountId) {
		AssertUtil.asWarnTrue(null != templateId, "道具id不能为空");
		AssertUtil.asWarnTrue(null != count && count > 0, "道具使用数量不能为小于1");
		Bag bag = itemDao.findById(accountId);
		AssertUtil.asWarnTrue(null != bag, "背包为空");
		Map<Integer, Item> itemMap = (Map<Integer, Item>) CBUtils.getObjectFromBytes(bag.getItemBytes());
		AssertUtil.asWarnTrue(itemMap.containsKey(templateId), "背包没有指定道具");
		Item item = itemMap.get(templateId);
		AssertUtil.asWarnTrue(item.getCount() >= count, "道具要使用的数量大于拥有的数量！");
		return itemAdd(templateId, -count, accountId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> settlementItem(Long accountId, List<Item> itemList) {
		Bag bag = getItemList(accountId);
		List<Item> list = new ArrayList<Item>();
			//判断道具原来是否有，没有则新增，有则增加数量
		Map<Integer, Item> itemMap = (Map<Integer, Item>) CBUtils.getObjectFromBytes(bag.getItemBytes());
		itemList.stream().forEach(item -> { 
			if (itemMap.containsKey(item.getTemplateId())) {
				Item itemTemp = itemMap.get(item.getTemplateId());
				int count = itemTemp.getCount()+item.getCount();
				itemTemp.setCount(count);
				AssertUtil.asWarnTrue(count >= 0, "道具数量异常！");
				if (count == 0) {
					itemMap.remove(item.getTemplateId());
				} else {
					itemMap.put(item.getTemplateId(), itemTemp);
				}
				list.add(item);
			} else {
				AssertUtil.asWarnTrue(item.getCount() > 0, "道具数量异常！");
				itemMap.put(item.getTemplateId(), item);
				list.add(item);
			} 
		});
		bag.setItemBytes(CBUtils.getBytesFromObject(itemMap));
		itemDao.update(bag);
		return list;
	}
	
	/**查询多个道具详情**/
	@SuppressWarnings("unchecked")
	public Map<Integer, Item> itemsInfo(Long accountId, List<Integer> itemIdList) {
		Bag bag = itemDao.findById(accountId);
		Map<Integer, Item> itenInfoMap = new HashMap<Integer, Item>();
		if (bag == null) {
			itemIdList.stream().forEach(itemId -> {
				itenInfoMap.put(itemId, new Item(itemId, 0));
			});
		} else {
			Map<Integer, Item> itemMap = (Map<Integer, Item>)CBUtils.getObjectFromBytes(bag.getItemBytes());
			itemIdList.stream().forEach(itemId -> {
				if (itemMap.containsKey(itemId)) {
					itenInfoMap.put(itemId, itemMap.get(itemId));
				} else {
					itenInfoMap.put(itemId, new Item(itemId, 0));
				}
			});
		}
		return itenInfoMap;
	}
	
	public Item itemsInfo(Long accountId, Integer itemTemplateId) {
		Bag bag = itemDao.findById(accountId);
		if (bag == null) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			Map<Integer, Item> itemMap = (Map<Integer, Item>)CBUtils.getObjectFromBytes(bag.getItemBytes());
			return itemMap.get(itemTemplateId);
		}
	}
	
	/**多个道具使用**/
	@SuppressWarnings("unchecked")
	public void useItems(Long accountId, List<Item> itemList) {
		Bag bag = itemDao.findById(accountId);
		Map<Integer, Item> itemMap = (Map<Integer, Item>)CBUtils.getObjectFromBytes(bag.getItemBytes());
		itemList.stream().forEach(item -> {
			Item tempItem = itemMap.get(item.getTemplateId());
			AssertUtil.asWarnTrue(null != tempItem, "背包没有指定道具");
			Integer num =tempItem.getCount() - item.getCount();
			AssertUtil.asWarnTrue(num >= 0, "道具要使用的数量大于拥有的数量！");
			item.setCount(-item.getCount());
		});
		settlementItem(accountId, itemList);
	}

	public Item itemAdd(Integer templateId, Integer count, Long accountId) {
		List<Item> list = new ArrayList<Item>();
		list.add(new Item(templateId,count));
		return settlementItem(accountId,list).get(0);
	}
}
