package com.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.common.entity.Member;
import com.common.entity.Role;
import com.common.enumerate.CoinEnum;
import com.common.enumerate.CurrencyType;
import com.common.helper.TimeHelper;
import com.common.template.GachaTemplate;
import com.common.util.AssertUtil;
import com.game.common.MessageCode;
import com.game.config.DropGroupConfig;
import com.game.config.GachaConfig;
import com.game.config.ItemConfig;
import com.game.config.MemberConfig;
import com.game.dao.GachaDao;
import com.game.dao.MemberDao;
import com.game.entity.Gacha;
import com.game.entity.Item;
import com.game.util.ParameterUtil;
import com.game.util.ProbabilityExtract;

@Service
public class GachaService {
	@Autowired
	private GachaDao gachaDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private MemberDao memberDao;
	
	/**抽奖入口**/
	public void gachaMain(Long accountId, Byte gachaTemplateId) {
		GachaTemplate gachaTemplate = GachaConfig.map.get((int)gachaTemplateId);
		AssertUtil.asWarnTrue(gachaTemplate != null, "typeId值有误");
		CurrencyType currencyType = CurrencyType.getType(gachaTemplate.getCurrencyType());
		Gacha gachaInfo = getGachaInfo(accountId);
		List<Item> gachaItemIdList = null;
		switch (currencyType) {
		case Gb:
			if (gachaTemplate.getGachaTimes() == 1) {
				gachaItemIdList = goldGachaOne(accountId, gachaInfo, gachaTemplateId);
			} else {
				gachaItemIdList = goldGachaTen(accountId, gachaInfo, gachaTemplateId);
			}
			break;
		case Jb:
			if (gachaTemplate.getGachaTimes() == 1) {
				gachaItemIdList = diamondGachaOne(accountId, gachaInfo, gachaTemplateId);
			} else {
				gachaItemIdList = diamondGachaTen(accountId, gachaInfo, gachaTemplateId);
			}
			break;
		}
		senMessage(gachaInfo, gachaItemIdList);
	}
	
	/**根据用户id查询用户的抽奖信息**/
	public Gacha getGachaInfo(Long accountId) {
		Gacha gachaInfo = gachaDao.findById(accountId);
		if (null == gachaInfo) {
			//如果用户第一次抽奖，初始化用户信息
			gachaInfo = new Gacha();
			gachaInfo.setAccountId(accountId);
			gachaInfo.setFreeDiamond(ParameterUtil.getGachaFreeByJb());
			gachaInfo.setFreeGold(ParameterUtil.getGachaFreeByGb());
			gachaInfo.setGachaCount(0);
			gachaInfo.setHalfDiamond(ParameterUtil.getGachaDiscountCnt());
			gachaInfo.setLastDiamondNow(TimeHelper.getTime());
			gachaInfo.setLastGoldNow(ParameterUtil.getGachaFreeIntervalByGb());
			gachaInfo.setLastTenGold(TimeHelper.getTime());
			gachaInfo.setLastHalfDiamondNow(TimeHelper.getTime());
			gachaInfo.setTenGold(ParameterUtil.getGachaFreeMaxByGb());
			gachaDao.create(gachaInfo);
		} else {
			long zeroTime = TimeHelper.getZeroTime();
			//如果最后一次金币免费抽在今天之前，今天的免费抽次数重置
			if (gachaInfo.getLastGoldNow() < zeroTime) {
				gachaInfo.setFreeGold(ParameterUtil.getGachaFreeByGb());
			} 
			//如果最后一次金币十连抽在今天之前，今天的十连抽次数重置
			if (gachaInfo.getLastTenGold() < zeroTime) {
				gachaInfo.setTenGold(ParameterUtil.getGachaFreeMaxByGb());
			} 
			//如果最后一次钻石免费抽在今天之前，今天的免费抽次数重置
			if (gachaInfo.getLastDiamondNow() < zeroTime) {
				gachaInfo.setFreeDiamond(ParameterUtil.getGachaFreeByJb());
			} 
			//如果最后一次钻石半价抽在今天之前，今天的半价抽次数重置
			if (gachaInfo.getLastHalfDiamondNow() < zeroTime) {
				gachaInfo.setHalfDiamond(ParameterUtil.getGachaDiscountCnt());
			} 
		}
		return gachaInfo;
	}
	
	/**金币单抽入口**/
	@Transactional
	private List<Item> goldGachaOne(Long accountId, Gacha gachaInfo, Byte typeId) {
		//用户最后一次抽取免费金币的时间要小于或等于五分钟前
		Integer fiveMinutesBefore = TimeHelper.getTime();
		fiveMinutesBefore = fiveMinutesBefore - ParameterUtil.getGachaFreeIntervalByGb();
		//判断抽奖资格
		if (gachaInfo.getFreeGold() > 0 && gachaInfo.getLastGoldNow() <= fiveMinutesBefore) {
			gachaInfo.setFreeGold(gachaInfo.getFreeGold() - 1);
			gachaInfo.setLastGoldNow(TimeHelper.getTime());
		} else {
			deductAmount(accountId, typeId, false);
		}
		//抽奖
		List<Integer> resultsList = gacha(typeId, gachaInfo);
		//保存奖品到背包
		List<Item> itemList = setItemToBag(accountId, resultsList);
		//保存必中奖品
		//保存抽奖信息
		gachaDao.update(gachaInfo);
		//返回结果
		return itemList;
	}

	private List<Item> setItemToBag(Long accountId, List<Integer> resultsList) {
		List<Item> itemList = new ArrayList<Item>();
		List<Integer> memberIdList = new ArrayList<Integer>();
		List<Member> memberList = memberDao.getByList(accountId);
		List<Integer> memberTemplateIdList = memberList.stream().map(Member::getTemplateId).collect(Collectors.toList());
		resultsList.stream().forEach(itemTemplateId -> {
			if (MemberConfig.map.containsKey(itemTemplateId)) {
				//如果是队员，判断用户是否有这个队员，有则拆解成碎片发放，无则添加到队员仓库
				if (memberTemplateIdList.contains(itemTemplateId)) {
					//移除原队员id，更改为碎片id
					Short count = MemberConfig.map.get(itemTemplateId).getTransformation();
					Integer fragmentTemplateId = ItemConfig.fragmentMap.get(MemberConfig.map.get(itemTemplateId).getPreMemberId());
					itemList.add(new Item(fragmentTemplateId, (int)count));
				}else {
					memberIdList.add(itemTemplateId);
				};
			}else {
				itemList.add(new Item(itemTemplateId, 1));
			}
		});
		Role role = roleService.getById(accountId);
		int[] addCoinItemIdArr = new int[itemList.size()];
		int[] addCountCoinArr = new int[itemList.size()];
		Integer[] memberIdArr = new Integer[memberIdList.size()];
		for (int i = 0; i < itemList.size(); i++) {
			addCoinItemIdArr[i] = itemList.get(i).getTemplateId();
			addCountCoinArr[i] = itemList.get(i).getCount();
		}
		for (int i = 0; i < memberIdArr.length; i++) {
			memberIdArr[i] = memberIdList.get(i);
		}
		roleService.addCoinItem(accountId, role, addCoinItemIdArr, addCountCoinArr, memberIdArr);
		return itemList;
	}
	
	/**根据抽奖次数抽奖**/
	private List<Integer> gacha(Byte typeId, Gacha gachaInfo) {
		GachaTemplate gachaTemplate = GachaConfig.map.get((int) typeId);
		List<Integer> resultsList = new ArrayList<Integer>();
		for (int i = 0; i < gachaTemplate.getGachaTimes(); i++) {
			CurrencyType currencyType = CurrencyType.getType(gachaTemplate.getCurrencyType());
			if (currencyType == CurrencyType.Gb) {
				gachaByGb(gachaTemplate, resultsList);
			} else {
				if ((gachaInfo.getGachaCount() + 1) % ParameterUtil.getGachaBaodiCountByJb() == 0) {
					// 钻石40抽保底
					Integer groupId = ParameterUtil.getGacha40GroupIdByJb();
					gachaThree(resultsList, groupId);
					gachaInfo.setGachaCount(-1);
				} else if ((gachaInfo.getGachaCount() + 1) % ParameterUtil.getGachaBaodiCountByGb() == 0) {
					// 钻石10抽保底
					Integer groupId = ParameterUtil.getGacha10GroupIdByJb();
					gachaThree(resultsList, groupId);
				} else {
					gachaByGb(gachaTemplate, resultsList);
				}
				gachaInfo.setGachaCount(gachaInfo.getGachaCount() + 1);
			}
		}
		return resultsList;
	}
	/**正常抽奖流程**/
	private void gachaByGb(GachaTemplate gachaTemplate, List<Integer> resultsList) {
		Integer dropGroup = ProbabilityExtract.outPrize(gachaTemplate.getJackpot());
		gachaThree(resultsList, dropGroup);
	}
	/**正常抽奖核心流程**/
	private void gachaThree(List<Integer> resultsList, Integer dropGroup) {
		Integer index = ProbabilityExtract.outPrize(DropGroupConfig.map.get(dropGroup).getGroupProbability());
		Integer results = null;
		if (index == 1) {
			results = ProbabilityExtract.outPrize(DropGroupConfig.map.get(dropGroup).getItemGroup());
		} else {
			results = ProbabilityExtract.outPrize(DropGroupConfig.map.get(dropGroup).getMemberGroup());
		}
		resultsList.add(results);
	}
	
	/**金币十连抽入口**/
	@Transactional
	private List<Item> goldGachaTen(Long accountId, Gacha gachaInfo, Byte typeId) {
		//判断抽奖资格
		AssertUtil.asWarnTrue(gachaInfo.getTenGold() > 0, "今天的金币十连抽次数已经用完！");
		gachaInfo.setTenGold(gachaInfo.getTenGold() - 1);
		gachaInfo.setLastTenGold(TimeHelper.getTime());
		//判断抽奖资源是否足够//扣除金币
		deductAmount(accountId, typeId, false);
		//抽奖
		List<Integer> resultsList = gacha(typeId, gachaInfo);
		//保存奖品到背包
		List<Item> itemList = setItemToBag(accountId, resultsList);
		//保存必中奖品
		//保存抽奖信息
		gachaDao.update(gachaInfo);
		//返回结果
		return itemList;
	}
	
	/**
	 * 根据支付类型选择扣除对应类型金额
	 * @param accountId
	 * @param gachaTemplateId
	 * @param isDiscount 是否半价
	 */
	private void deductAmount(Long accountId, Byte gachaTemplateId, Boolean isDiscount) {
		Role role = roleService.getById(accountId);
		GachaTemplate gachaTemplate = GachaConfig.map.get((int)gachaTemplateId);
		int needCurrency = gachaTemplate.getNeedCurrency();
		int addCoinItemId;
		int addCountCoin;
		if (gachaTemplate.getCurrencyType() == CurrencyType.Gb.getIndex()) { //金币抽奖
			AssertUtil.asWarnTrue(role.getGb() >= gachaTemplate.getNeedCurrency(), "金币不足！！");//判断金币数量
			addCoinItemId = CoinEnum.gb.getIndex();
			addCountCoin = -needCurrency;
		} else { //钻石抽奖
			AssertUtil.asWarnTrue(role.getJb() >= gachaTemplate.getNeedCurrency(), "钻石不足！！");//判断钻石数量
			//扣除钻石 
			addCoinItemId = CoinEnum.jb.getIndex();
			addCountCoin = isDiscount ? -needCurrency / 2 : -needCurrency;
		}
		roleService.addCoinItem(accountId, role, addCoinItemId, addCountCoin, null);
	}
	
	/**钻石单抽入口**/
	@Transactional
	private List<Item> diamondGachaOne(Long accountId, Gacha gachaInfo, Byte typeId) {
		// 判断抽奖资格
		if (gachaInfo.getFreeDiamond() > 0) {// 判断免费次数
			gachaInfo.setFreeDiamond(gachaInfo.getFreeDiamond() - 1);
			gachaInfo.setLastDiamondNow(TimeHelper.getTime());
		} else {
			// 判断背包有没有免费卷
			Integer freeItemTemplateId = ParameterUtil.getGachaFreeItemTemplateId();
			Item item = itemService.itemsInfo(accountId, freeItemTemplateId);
			
			int itemId = ParameterUtil.gachaItemIdByJb();
			if (item != null && item.getCount() > 0) {
				itemService.useItem(itemId, 1, accountId);
			} else if (gachaInfo.getHalfDiamond() > 0) { // 判断半价
				// 判断抽奖资源是否足够
				AssertUtil.asWarnTrue(true, "钻石不足！！");// 判断钻石数量
				// 扣除钻石
				deductAmount(accountId, typeId, true);
				// 改变最后使用半价的时间
				gachaInfo.setHalfDiamond(gachaInfo.getHalfDiamond() - 1);
				gachaInfo.setLastHalfDiamondNow(TimeHelper.getTime());
			} else { // 原价购买
				// 判断抽奖资源是否足够//扣除钻石
				deductAmount(accountId, typeId, false);
			}
		}
		// 正常抽奖
		List<Integer> resultsList = gacha(typeId, gachaInfo);
		// 保存奖品到背包
		List<Item> itemList = setItemToBag(accountId, resultsList);
		// 保存必中奖品
		// 保存抽奖信息
		gachaDao.update(gachaInfo);
		// 返回结果
		return itemList;
	}
	
	/**判断背包有没有免费卷**/
	public Item freeSecurities(Long accountId) {
		ArrayList<Integer> itemIdList = new ArrayList<Integer>();
		Integer freeItemTemplateId = ParameterUtil.getGachaFreeItemTemplateId();
		itemIdList.add(freeItemTemplateId);
		Item item = itemService.itemsInfo(accountId, freeItemTemplateId);
		return item;
	}
	
	/**钻石十连抽入口
	 * @return **/
	@Transactional
	private List<Item> diamondGachaTen(Long accountId, Gacha gachaInfo, Byte typeId) {
		//判断抽奖资源是否足够//扣除钻石
		deductAmount(accountId, typeId, false);
		//抽奖
		List<Integer> resultsList = gacha(typeId, gachaInfo);
		//保存奖品到背包
		List<Item> itemList = setItemToBag(accountId, resultsList);
		//保存必中奖品
		//保存抽奖信息
		gachaDao.update(gachaInfo);
		//返回结果
		return itemList;
	}
	
	/**发送抽奖消息**/
	private void senMessage(Gacha gachaInfo, List<Item> itemList) {
		Integer freeItemTemplateId = ParameterUtil.getGachaFreeItemTemplateId();
		Item item = itemService.itemsInfo(gachaInfo.getAccountId(), freeItemTemplateId);
		int freeItemCount = item == null ? 0 : item.getCount();
		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_item_Gacha);
		msg.putByte(gachaInfo.getFreeGold().byteValue());
		msg.putByte(gachaInfo.getFreeDiamond().byteValue());
		msg.putInt(freeItemCount);
		msg.putByte(gachaInfo.getHalfDiamond().byteValue());
		msg.putByte(gachaInfo.getTenGold().byteValue());
		msg.putLong(gachaInfo.getLastGoldNow());
		msg.putShort(itemList.size());
		itemList.stream().forEach(itemId -> {
			msg.putInt(itemId.getTemplateId());
			msg.putInt(itemId.getCount()); 
		});
		MsgSender.sendMsg(msg);
	}
}
