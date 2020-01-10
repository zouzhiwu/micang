package com.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.common.entity.Member;
import com.common.entity.Role;
import com.common.enumerate.CoinEnum;
import com.common.enumerate.ItemChangeEnum;
import com.common.enumerate.WorkType;
import com.common.util.AssertUtil;
import com.game.common.MessageCode;
import com.game.config.MemberConfig;
import com.game.dao.MemberDao;
import com.game.dao.RoleDao;
import com.game.entity.CoinItemCountBean;
import com.game.entity.Item;
import com.game.entity.LikeHeroDaoBean;
import com.game.util.Params;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private CharacterService characterService;
	
	
	public void addCoinItem(long accountId, Role role, int addCoinItemId, int addCountCoin,
			Integer memberTemplateId) {
		Integer[] memberTemplateIdArr = null;
		if (memberTemplateId != null) {
			memberTemplateIdArr = new Integer[memberTemplateId];
		}
		addCoinItem(accountId, role, new int[addCoinItemId] , new int[addCountCoin], memberTemplateIdArr);
	}

	/**
	 * 添加道具和货币
	 * 
	 * @param accountId
	 * @param role
	 * @param addCoinItemId
	 * @param addCountCoin  正数是增加，负数是减少)
	 */
	public void addCoinItem(long accountId, Role role, int[] addCoinItemIdArr, int[] addCountCoinArr,
			Integer[] memberTemplateIdArr) {
		List<CoinItemCountBean> list = new ArrayList<CoinItemCountBean>();
		for (int i = 0; i < addCoinItemIdArr.length; i++) {
			CoinItemCountBean cBean = new CoinItemCountBean(addCoinItemIdArr[i], addCountCoinArr[i]);
			list.add(cBean);
		}

		List<CoinItemCountBean> coinList = list.stream().filter(p -> p.getCoinItemId().intValue() <= Params.coin_100)
				.collect(Collectors.toList());
		this.addCoin(role, coinList);

		List<CoinItemCountBean> itemList = list.stream().filter(p -> p.getCoinItemId().intValue() > Params.coin_100)
				.collect(Collectors.toList());
		List<Item> tempAdditemList = new ArrayList<Item>();
		for (CoinItemCountBean ciBean : itemList) {
			Item item = new Item();
			item.setTemplateId(ciBean.getCoinItemId());
			item.setCount(ciBean.getCount());
			tempAdditemList.add(item);
		}
		if (tempAdditemList.size() > 0) {
			itemService.settlementItem(accountId, tempAdditemList);
		}

		if (memberTemplateIdArr != null && memberTemplateIdArr.length > 0) {
			for (int memberTemplateId : memberTemplateIdArr) {
				Member member = new Member();
				member.setTemplateId(memberTemplateId);
				member.setAccountId(accountId);
				member.setExp(0);
				member.setWork(WorkType.Off);

				Integer likeHeroId = Integer
						.valueOf(MemberConfig.map.get(memberTemplateId).getLikeHeros().split(Params.fenge)[0]);
				LikeHeroDaoBean likeHeroDaoBean = new LikeHeroDaoBean();
				likeHeroDaoBean.setHeroLevel(1);
				likeHeroDaoBean.setLikeVal(0);
				likeHeroDaoBean.setSeat(1);
				likeHeroDaoBean.setHeroTemplateId(likeHeroId);
				List<LikeHeroDaoBean> likeList = new ArrayList<LikeHeroDaoBean>();
				likeList.add(likeHeroDaoBean);

				member.setLikeHeros(JSONObject.toJSONString(likeList));
				member.setMasterSkill(0);
				member.setMaskerSkillLv(0);
				member.setSlaveSkill1(0);
				member.setSlaveSkillLv1(0);
				member.setSlaveSkill2(0);
				member.setSlaveSkillLv2(0);
				member.setSlaveSkill3(0);
				member.setSlaveSkillLv3(0);
				member.setCharacterstr(characterService.createCharacter());
				memberDao.add(member);
			}
		}
		// 通知
		Message msgNotify = new Message();
		msgNotify.setMsgcd(MessageCode.msg_item_change);
		int memberIdLength = (memberTemplateIdArr != null && memberTemplateIdArr.length > 0) ? memberTemplateIdArr.length : 0;
		msgNotify.putShort(addCoinItemIdArr.length + memberIdLength);
		for (int i = 0; i < addCoinItemIdArr.length; i++) {
			if (addCoinItemIdArr[i] <= Params.coin_100) {
				msgNotify.putByte(ItemChangeEnum.Coin.getIndex());
			} else {
				msgNotify.putByte(ItemChangeEnum.Item.getIndex());
			}
			msgNotify.putInt(addCoinItemIdArr[i]);
			msgNotify.putInt(addCountCoinArr[i]);
		}
		if (memberIdLength > 0) {
			for (int memberId : memberTemplateIdArr) {
				msgNotify.putByte(ItemChangeEnum.Member.getIndex());
				msgNotify.putInt(memberId);
				msgNotify.putInt(1);
			}
		}
		MsgSender.sendMsg(msgNotify);
	}

	public void addCoin(Role role, List<CoinItemCountBean> list) {

		if (list == null || list.size() == 0) {
			return;
		}
		if (CollectionUtils.isNotEmpty(list)) {
			for (CoinItemCountBean cb : list) {
				CoinEnum coin = CoinEnum.getType(cb.getCoinItemId());
				switch (coin) {
				case gb:
					role.setGb(role.getGb() + cb.getCount());
					break;
				case jb:
					role.setJb(role.getJb() + cb.getCount());
					break;
				case xinlifudaoCoin:
					role.setXinlifudaoCoin(role.getXinlifudaoCoin() + cb.getCount());
					break;
				case expPot:
					role.setExpPot(role.getExpPot().intValue() + cb.getCount());
					break;
				}
			}
			roleDao.update(role);
		}
	}

	/**
	 * 检测道具和货币是否足够
	 * 
	 * @param accountId
	 * @param role
	 * @param list
	 * @return
	 */
	public boolean checkCoinItemEnough(long accountId, Role role, int[] consumeCoinItemId, int[] consumeCountCoin) {

		List<CoinItemCountBean> list = new ArrayList<CoinItemCountBean>();
		for (int i = 0; i < consumeCoinItemId.length; i++) {
			CoinItemCountBean cBean = new CoinItemCountBean(consumeCoinItemId[i], consumeCountCoin[i]);
			list.add(cBean);
		}
		List<CoinItemCountBean> coinList = list.stream().filter(p -> p.getCoinItemId().intValue() <= Params.coin_100)
				.collect(Collectors.toList());
		boolean flag = true;
		if (coinList != null && coinList.size() > 0) {
			flag = this.checkCoinEnough(role, coinList);
		}
		if (!flag) {
			return flag;
		}
		List<CoinItemCountBean> itemList = list.stream().filter(p -> p.getCoinItemId().intValue() > Params.coin_100)
				.collect(Collectors.toList());
		if (itemList != null && itemList.size() > 0) {
			List<Integer> itemIdlist = itemList.stream().map(p -> p.getCoinItemId()).collect(Collectors.toList());
			Map<Integer, Item> map = itemService.itemsInfo(accountId, itemIdlist);
			flag = this.checkItemEnough(map, itemList);
		}

		return flag;
	}

	/**
	 * 检测道具是否足够
	 * 
	 * @param itemsMap
	 * @param consumeList
	 * @return
	 */
	public boolean checkItemEnough(Map<Integer, Item> itemsMap, List<CoinItemCountBean> consumeList) {
		boolean flag = true;
		for (CoinItemCountBean coinItemCountBean : consumeList) {
			Item item = itemsMap.get(coinItemCountBean.getCoinItemId());
			if (item == null || item.getCount() < coinItemCountBean.getCount()) {
				flag = false;
				AssertUtil.asWarnTrue(true, "itemid:" + coinItemCountBean.getCoinItemId() + "不足");
				break;
			}
		}
		return flag;
	}

	public boolean checkCoinEnough(Role role, List<CoinItemCountBean> list) {
		boolean flag = true;
		for (CoinItemCountBean cb : list) {
			CoinEnum coin = CoinEnum.getType(cb.getCoinItemId());
			switch (coin) {
			case gb:
				AssertUtil.asWarnTrue(role.getGb() >= cb.getCount(), "itemid:" + CoinEnum.gb.getName() + "不足");
				break;
			case jb:
				AssertUtil.asWarnTrue(role.getJb() >= cb.getCount(), "itemid:" + CoinEnum.gb.getName() + "不足");
				break;
			case xinlifudaoCoin:
				AssertUtil.asWarnTrue(role.getXinlifudaoCoin() >= cb.getCount(), "itemid:" + CoinEnum.gb.getName() + "不足");
				break;
			case expPot:
				AssertUtil.asWarnTrue(role.getExpPot() >= cb.getCount(), "itemid:" + CoinEnum.gb.getName() + "不足");
				break;
			}
		}
		return flag;
	}

	public void add(Role role) {
		roleDao.add(role);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	public void updateGb(Long accountId, Long add) {
		roleDao.updateGb(accountId, add);
	}

	public void updateJb(Long accountId, Long add) {
		roleDao.updateJb(accountId, add);
	}

}
