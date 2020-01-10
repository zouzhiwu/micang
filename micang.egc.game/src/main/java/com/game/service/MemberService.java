package com.game.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.common.entity.Lvxp;
import com.common.entity.Member;
import com.common.entity.Role;
import com.common.enumerate.CoinEnum;
import com.common.enumerate.CurrencyType;
import com.common.enumerate.LevelType;
import com.common.enumerate.MemberAttrEnum;
import com.common.enumerate.WorkType;
import com.common.template.HeroProficiencyTemplate;
import com.common.template.LevelTemplate;
import com.common.template.MemberDuanTemplate;
import com.common.template.MemberStarTemplate;
import com.common.template.MemberTemplate;
import com.common.util.AssertUtil;
import com.game.common.MessageCode;
import com.game.config.CharacterConfig;
import com.game.config.HeroProficiencyConfig;
import com.game.config.LevelConfig;
import com.game.config.MemberConfig;
import com.game.config.MemberDuanConfig;
import com.game.config.MemberStarConfig;
import com.game.config.ParameterConfig;
import com.game.dao.MemberDao;
import com.game.entity.CharacterBean;
import com.game.entity.Item;
import com.game.entity.LikeHeroDaoBean;
import com.game.util.CommonUtil;
import com.game.util.LevelUtil;
import com.game.util.Params;
import com.game.util.PowerUtils;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private RoleService roleService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CharacterService characterService;

//	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	public List<Member> getByList(Long accountId) {
		return memberDao.getByList(accountId);
	}

	public Member getById(Long memberId) {
		return memberDao.getById(memberId);
	}

	/**
	 * 招募队员
	 */
	public Member recruit(Long accountId, Byte currencyIndex, Byte count) {
		CurrencyType currencyType = CurrencyType.getType(currencyIndex);
		AssertUtil.asWarnTrue(currencyType != null, "货币类型不存在");
		// TODO 扣除货币
		// 在队员模板中随机选择一个队员
		List<Integer> memberTemplateIdList = MemberConfig.map.keySet().stream().collect(Collectors.toList());
		// TODO 随机命中
		Integer memberTemplateId = memberTemplateIdList.get(RandomUtils.nextInt(0, memberTemplateIdList.size()));
		return addMember(accountId, memberTemplateId);
	}

	public void addAllMember(Long accountId) {
		MemberConfig.map.forEach((memberTemplateId, memberTemplate) -> {
			addMember(accountId, memberTemplateId);
		});
	}

	private Member addMember(Long accountId, Integer memberTemplateId) {
		Boolean isExist = memberDao.isExistMember(accountId, memberTemplateId);
		if (isExist) {
			// TODO 添加卡牌
			return null;
		} else {
			MemberTemplate memberTemplate = MemberConfig.map.get(memberTemplateId);
			WorkType workType = WorkType.getType(memberTemplate.getWork());
			Member[] members = getWorkMembers(accountId);
			// 查找已上阵的队员位置，如果该位置已经有人，则不设置位置
			if (members[workType.getIndex() - 1] != null) {
				workType = WorkType.Off;
			}
			Member member = new Member();
			member.setTemplateId(memberTemplateId);
			member.setAccountId(accountId);
			member.setExp(0);
			member.setWork(workType);

			Integer likeHeroId = Integer
					.valueOf(MemberConfig.map.get(memberTemplateId).getLikeHeros().split(Params.fenge)[0]);
			LikeHeroDaoBean likeHeroDaoBean = new LikeHeroDaoBean();
			likeHeroDaoBean.setHeroLevel(1);
			likeHeroDaoBean.setLikeVal(0);
			likeHeroDaoBean.setSeat(1);
			likeHeroDaoBean.setHeroTemplateId(likeHeroId);
			List<LikeHeroDaoBean> list = new ArrayList<LikeHeroDaoBean>();
			list.add(likeHeroDaoBean);
			member.setLikeHeros(JSONObject.toJSONString(list));

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
			return member;
		}
	}

	/**
	 * 上阵队员是否已满
	 */
	public boolean isFullMembers(Member[] members) {
		if (members == null) {
			return false;
		}
		for (int i = 0; i < members.length; i++) {
			if (members[i] == null) {
				return false;
			}
		}
		return true;
	}

	@Transactional
	public void gotoWork(Long accountId, Long topMemberId, Long junMemberId, Long midMemberId, Long adcMemberId,
			Long supMemberId) {

		Member topMember = memberDao.getById(topMemberId);
		AssertUtil.asWarnTrue(topMember != null, "队员不存在");
		AssertUtil.asWarnTrue(topMember.getAccountId().equals(accountId), "上单队员不属于你");
		Member junMember = memberDao.getById(junMemberId);
		AssertUtil.asWarnTrue(junMember != null, "队员不存在");
		AssertUtil.asWarnTrue(junMember.getAccountId().equals(accountId), "打野队员不属于你");
		Member midMember = memberDao.getById(midMemberId);
		AssertUtil.asWarnTrue(midMember != null, "队员不存在");
		AssertUtil.asWarnTrue(midMember.getAccountId().equals(accountId), "中单队员不属于你");
		Member adcMember = memberDao.getById(adcMemberId);
		AssertUtil.asWarnTrue(adcMember != null, "队员不存在");
		AssertUtil.asWarnTrue(adcMember.getAccountId().equals(accountId), "输出队员不属于你");
		Member supMember = memberDao.getById(supMemberId);
		AssertUtil.asWarnTrue(supMember != null, "队员不存在");
		AssertUtil.asWarnTrue(supMember.getAccountId().equals(accountId), "辅助队员不属于你");
		Member[] members = getWorkMembers(accountId);
		// 上单队员上阵
		swapWork(WorkType.Top, topMember, members[WorkType.Top.getIndex() - 1]);
		// 中单队员上阵
		swapWork(WorkType.Mid, midMember, members[WorkType.Mid.getIndex() - 1]);
		// 打野队员上阵
		swapWork(WorkType.Jun, junMember, members[WorkType.Jun.getIndex() - 1]);
		// 辅助队员上阵
		swapWork(WorkType.Sup, supMember, members[WorkType.Sup.getIndex() - 1]);
		// 输出队员上阵
		swapWork(WorkType.Adc, adcMember, members[WorkType.Adc.getIndex() - 1]);
	}

	/***
	 * 从经验罐分配经验给队员升级
	 * 
	 * @param accountId
	 * @param memberId   队员Id
	 * @param levelCount 升级数量
	 */
	@Transactional
	public void updateLv(Long accountId, long memberId, byte levelCount) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");
		AssertUtil.asWarnTrue(levelCount == 1 || levelCount == 5, "升级参数错误");
		Role role = roleService.getById(accountId);
		AssertUtil.asWarnTrue(role.getExpPot() > 0, "经验不足");

//		byte memberType = Params.type_member;
		Lvxp lvxp = LevelUtil.getLvxp(LevelType.Member, member.getExp());
		int maxLv = LevelUtil.getMaxLv(LevelType.Member);
		AssertUtil.asWarnTrue(lvxp.getLevel() < maxLv, "已经最大等级");
		// 实际升级到目标等级所需要的经验值
		int expNeed = 0;
		// 如果升1级，则计算升1级所需要的经验值，否则计算升5级所需要的经验值
		if (levelCount == 1) {
			LevelTemplate levelTemplate = LevelConfig.typeLvMap
					.get(LevelType.Member.getIndex() + "_" + lvxp.getLevel());
			if ((lvxp.getXp() + role.getExpPot()) >= levelTemplate.getExp()) {
				expNeed = levelTemplate.getExp() - lvxp.getXp();
			} else {
				expNeed = role.getExpPot();
			}
		} else {
			// 利用队员的总经验 经验罐里面的总经验 与升级消耗总经验做对比
			if ((lvxp.getLevel() + levelCount) > maxLv) {
				levelCount = (byte) (maxLv - lvxp.getLevel());
			}
			LevelTemplate targetLevelTemplate = LevelConfig.typeLvMap
					.get(LevelType.Member.getIndex() + "_" + (lvxp.getLevel() + levelCount));
			// 计算实际所需要的经验值
			if ((member.getExp() + role.getExpPot()) <= targetLevelTemplate.getTotalExp()) {
				expNeed = role.getExpPot();
			} else {
				expNeed = targetLevelTemplate.getTotalExp() - member.getExp();
			}
		}
		int[] addCoinItemId = new int[1];
		addCoinItemId[0] = CoinEnum.expPot.getIndex();
		int[] addCountCoin = new int[1];
		addCountCoin[0] = -expNeed;
		roleService.addCoinItem(accountId, role, addCoinItemId, addCountCoin, null);

		member.setExp(member.getExp() + expNeed);
		memberDao.update(member);
		// 当前等级
		int nowLv = LevelUtil.getLvxp(LevelType.Member, member.getExp()).getLevel();
//		System.out.println("memberType-->"+memberType+",member.getExp()-->"+member.getExp()+","+"nowLv-->"+nowLv);
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
		// 战力
		int power = PowerUtils.getPower(memberTemplate, nowLv, member.getStage().intValue());

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_update_lv);
		msg.putLong(memberId);
		msg.putByte((byte) nowLv);
		msg.putInt(power);
		msg.putInt(member.getExp());
		msg.putInt(role.getExpPot());
		MsgSender.sendMsg(msg);
	}

	public void getExpPot(Long accountId) {
		Role role = roleService.getById(accountId);

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_expPot);
		msg.putInt(role.getExpPot());
		MsgSender.sendMsg(msg);
	}

	public void updateStar(Long accountId, long memberId, byte type) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");
		MemberStarTemplate memberStarTemplate = MemberStarConfig.map.get(member.getTemplateId());
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());

		int starMaxLevel = CommonUtil.getStarMaxLevel(member.getTemplateId());
		AssertUtil.asWarnTrue(memberTemplate.getStars() < starMaxLevel, "星级已是最大");

		if (type == Params.type_upstar_1 || type == Params.type_upstar_2) {

			AssertUtil.asWarnTrue(member.getStarSeat() < Params.starSeat10, "卡位已满");
			int relationItemId = memberStarTemplate.getRelation();
			Map<Integer, Item> bagItemMap = itemService.itemsInfo(accountId, Arrays.asList(relationItemId));
			int starPatchCount = bagItemMap.get(relationItemId).getCount();
			Role role = roleService.getById(accountId);
			int[] consumeCoinItemId = new int[1];
			consumeCoinItemId[0] = relationItemId;
			int[] consumeCountCoin = new int[1];
			
			
			int consume = 0;
			int addSeatReal = 0;
			if (type == Params.type_upstar_1) {
				consume = memberStarTemplate.getConsume();
				addSeatReal = 1;
			} else {
				int addSeat = starPatchCount / memberStarTemplate.getConsume();
				if ((Params.starSeat10 - member.getStarSeat()) >= addSeat) {
					addSeatReal = addSeat;
				} else {
					addSeatReal = Params.starSeat10 - member.getStarSeat();
				}
				consume = addSeatReal * memberStarTemplate.getConsume();
			}
			consumeCountCoin[0] = consume;
			roleService.checkCoinItemEnough(accountId, role, consumeCoinItemId, consumeCountCoin);
			
			roleService.addCoinItem(accountId, role, consumeCoinItemId, CommonUtil.IntToMinus(consumeCountCoin), null);
			member.setStarSeat(member.getStarSeat() + addSeatReal);
			memberDao.update(member);

			Message msg = new Message();
			msg.setMsgcd(MessageCode.msg_member_update_star);
			msg.putLong(memberId);
			
			//System.out.println(addSeatReal+"=member.getStarSeat()==>"+member.getStarSeat());
			
			msg.putByte(member.getStarSeat().byteValue());
			msg.putInt(0);
			int power = PowerUtils.getPower(memberTemplate, LevelUtil.getLevel(LevelType.Member, member.getExp()), member.getStage().intValue());
			msg.putInt(power);
			MsgSender.sendMsg(msg);
		} else {
			// 升星
			AssertUtil.asWarnTrue(member.getStarSeat() == Params.starSeat10, "升星条件不足");

			member.setTemplateId(CommonUtil.nextStartemplateId(member.getTemplateId()));
			member.setStarSeat(0);
			memberDao.update(member);

			Message msg = new Message();
			msg.setMsgcd(MessageCode.msg_member_update_star);
			msg.putLong(memberId);
			msg.putByte(member.getStarSeat().byteValue());
			msg.putInt(member.getTemplateId());
			//此刻memberTemplate模板以及变化过
			MemberTemplate memberTemplateNext = MemberConfig.map.get(member.getTemplateId());
			int power = PowerUtils.getPower(memberTemplateNext, LevelUtil.getLevel(LevelType.Member, member.getExp()), member.getStage().intValue());
			msg.putInt(power);
			MsgSender.sendMsg(msg);
		}
	}

	/***
	 * 升级段位
	 * 
	 * @param accountId
	 * @param memberId
	 */
	@Transactional
	public void updateDuan(Long accountId, long memberId) {

		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");

		Byte stage = member.getStage();
		Byte stageNext = (byte) (1+stage);
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
		int stageMax = this.getMaxCount(MemberDuanConfig.list, memberTemplate.getQuality());

//		System.out.println("stageMax-->"+stageMax+",memberId-->"+member.getTemplateId());
		AssertUtil.asWarnTrue(stage < stageMax, "段位已经最大等级");
		MemberDuanTemplate memberDuanTemplate = MemberDuanConfig.memberIdAscendingMap
				.get(memberTemplate.getQuality() + "_" + (stage+1));
		AssertUtil.asWarnTrue(memberTemplate != null, "不存在此段位");

//		System.out.println(LevelUtil.getLvxp(Params.type_member, member.getExp()).getLevel()+"==pp=="+memberDuanTemplate.getGrade());
		AssertUtil.asWarnTrue(LevelUtil.getLevel(LevelType.Member, member.getExp()) >= memberDuanTemplate.getGrade(),
				"队员等级不够");

		int[] itemIdArr = CommonUtil.stringToInt((memberDuanTemplate.getMaterial1()+Params.shuxian+CoinEnum.gb.getIndex()).split(Params.fenge));
		int[] itemCountArr = CommonUtil.stringToInt((memberDuanTemplate.getNumber()+Params.shuxian+memberDuanTemplate.getCoin()).split(Params.fenge));
		
		Role role = roleService.getById(accountId);
		roleService.checkCoinItemEnough(accountId, role, itemIdArr, itemCountArr);
		// 消耗道具
		roleService.addCoinItem(accountId, role, itemIdArr, CommonUtil.IntToMinus(itemCountArr), null);
		// 段位增加
		member.setStage(stageNext);
		memberDao.update(member);

		// 判断当前是否有技能解锁
		String unlock = memberTemplate.getUnlock();// 段位解锁条件

		boolean unlockFlag = checkUnlock(unlock, stageNext);
		if (unlockFlag) {
			// TODO 解锁当前技能
		} else {
			// TODO 弹出升级成功
		}

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_update_duan);
		msg.putLong(memberId);
		msg.putByte(stageNext);
		msg.putInt(PowerUtils.getPower(memberTemplate, LevelUtil.getLevel(LevelType.Member, member.getExp()), member.getStage().intValue()));
		MsgSender.sendMsg(msg);
	}

	private boolean checkUnlock(String unlock, Byte stageNext) {

		String[] stageArr = unlock.split(Params.fenge);
		boolean flag = false;
		for (String stage : stageArr) {
			if (stage.equals(stageNext.toString())) {
				flag = true;
			}
		}
		return flag;
	}

	// 检查需要消耗的物品是否足够
//	private boolean checkItemEnough(Map<Integer, Item> itemsMap, List<Integer> itemIdList,
//			List<Integer> itemCountList) {
//		boolean flag = true;
//		for (int i = 0; i < itemIdList.size(); i++) {
//			Integer itemId = itemIdList.get(i);
//			Integer itemNeedCount = itemCountList.get(i);
//
//			if (!(itemsMap.get(itemId) != null && itemsMap.get(itemId).getCount() >= itemNeedCount)) {
//				flag = false;
//				break;
//			}
//		}
//		return flag;
//	}

	private int getMaxCount(List<MemberDuanTemplate> list, Byte quality) {
		return (int) list.stream().filter(mdt -> mdt.getQuality().byteValue() == quality.byteValue()).count();
	}

	public void swapWork(WorkType workType, Member onMember, Member offMember) {
		if (offMember == null) {
			onMember.setWork(workType);
			memberDao.update(onMember);
		} else {
			// 如果上阵队员和下阵队员不同，则替换
			if (!onMember.getId().equals(offMember.getId())) {
				// 设置队员下阵
				offMember.setWork(WorkType.Off);
				memberDao.update(offMember);
				// 设置队员上阵
				onMember.setWork(workType);
				memberDao.update(onMember);
			}
		}
	}

	/**
	 * 获取已上阵的队员
	 */
	public Member[] getWorkMembers(Long accountId) {
		List<Member> workMemberList = memberDao.getWorkMemberList(accountId);
		Member[] members = new Member[5];
		for (int i = 0; i < workMemberList.size(); i++) {
			Member member = workMemberList.get(i);
			switch (member.getWork()) {
			case Off:
				break;
			case Top:
				members[WorkType.Top.getIndex() - 1] = member;
				break;
			case Mid:
				members[WorkType.Mid.getIndex() - 1] = member;
				break;
			case Jun:
				members[WorkType.Jun.getIndex() - 1] = member;
				break;
			case Sup:
				members[WorkType.Sup.getIndex() - 1] = member;
				break;
			case Adc:
				members[WorkType.Adc.getIndex() - 1] = member;
				break;
			}
		}
		return members;
	}

	@Transactional
	public void memberAddHero(Long accountId, long memberId, int heroTemplateId, byte seat) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
		Integer openSeatCount = CommonUtil.getOpenSeatCount(memberTemplate, member.getStage());
		AssertUtil.asWarnTrue(openSeatCount >= seat, "暂未开启");
		String[] likeHerosArr = memberTemplate.getLikeHeros().split(Params.fenge);
		List<String> likeHeroList = Arrays.asList(likeHerosArr);
		AssertUtil.asWarnTrue(likeHeroList.stream().anyMatch(heroId -> heroId.equals(String.valueOf(heroTemplateId))),
				"英雄不匹配");

		List<LikeHeroDaoBean> likeHeroBeanList = JSONObject.parseArray(member.getLikeHeros(), LikeHeroDaoBean.class);
		boolean isExistFlag = false;
		LikeHeroDaoBean templikeHeroBean = null;
		if (likeHeroBeanList != null) {
			isExistFlag = likeHeroBeanList.stream()
					.anyMatch(p -> p.getHeroTemplateId() == heroTemplateId && p.getSeat().intValue() != 0);
			AssertUtil.asWarnTrue(!isExistFlag, "英雄已上阵");

			Optional<LikeHeroDaoBean> optSeat = likeHeroBeanList.stream().filter(p -> p.getSeat().intValue() == seat)
					.findFirst();
			if (optSeat.isPresent()) {
				// 将以往位置上的设置为0
				optSeat.get().setSeat(0);
			}

			Optional<LikeHeroDaoBean> opt = likeHeroBeanList.stream()
					.filter(p -> p.getHeroTemplateId() == heroTemplateId).findFirst();
			if (opt.isPresent()) {
				templikeHeroBean = opt.get();
				templikeHeroBean.setSeat(seat);
			} else {
				templikeHeroBean = new LikeHeroDaoBean();
				templikeHeroBean.setHeroTemplateId(heroTemplateId);
				templikeHeroBean.setHeroLevel(1);
				templikeHeroBean.setLikeVal(0);
				templikeHeroBean.setSeat(seat);
				likeHeroBeanList.add(templikeHeroBean);
			}
		} else {
			likeHeroBeanList = new ArrayList<LikeHeroDaoBean>();
			templikeHeroBean = new LikeHeroDaoBean();
			templikeHeroBean.setHeroTemplateId(heroTemplateId);
			templikeHeroBean.setHeroLevel(1);
			templikeHeroBean.setLikeVal(0);
			templikeHeroBean.setSeat(seat);
			likeHeroBeanList.add(templikeHeroBean);

		}
		member.setLikeHeros(JSONObject.toJSONString(likeHeroBeanList));

		memberDao.update(member);

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_add_hero);
		msg.putLong(memberId);
		msg.putInt(heroTemplateId);
		msg.putByte(seat);
		msg.putInt(templikeHeroBean.getHeroLevel());
		msg.putInt(templikeHeroBean.getLikeVal());
		MsgSender.sendMsg(msg);
	}

	/**
	 * 计算队员附加属性 队员附加属性 = （队员基础属性 + 队员成长属性 + 队员装备属性 + 队员进阶属性n）* 英雄熟练度转化百分比
	 */
	public Map<Integer, Integer> callcAdditionalAttr(Member member) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int level = LevelUtil.getLevel(LevelType.Member, member.getExp());
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
		// TODO需要用一星的值做基础

		int dynamic = memberTemplate.getDynamic();
		// TODO 队员装备属性
		int memberDynamicZb = 0;
		// TODO 英雄熟练度转化百分比
		int heroSld = 1;
		// 进阶
		double jinjie = Math.max(Math.pow(member.getStage() / 1.5, 1.5), 1);

		Double dynamicTotal = (dynamic + level * memberTemplate.getGrowthDynamic() + memberDynamicZb
				+ dynamic * (jinjie - 1)) * heroSld;
		map.put(MemberAttrEnum.Dynamic.getIndex(), (int) Math.round(dynamicTotal));

		int operation = memberTemplate.getOperation();
		// TODO 队员装备属性
		int memberOperationZb = 1;
		Double operationTotal = (operation + level * memberTemplate.getGrowthOperation() + memberOperationZb
				+ operation * (jinjie - 1)) * heroSld;
		map.put(MemberAttrEnum.Operation.getIndex(), (int) Math.round(operationTotal));

		int focus = memberTemplate.getOperation();
		// TODO 队员装备属性
		int memberFocusZb = 1;
		Double focusTotal = (focus + level * memberTemplate.getGrowthFocus() + memberFocusZb + focus * (jinjie - 1))
				* heroSld;
		map.put(MemberAttrEnum.Focus.getIndex(), (int) Math.round(focusTotal));

		int tough = memberTemplate.getTough();
		// TODO 队员装备属性
		int memberToughZb = 1;
		Double toughTotal = (tough + level * memberTemplate.getTough() + memberToughZb + tough * (jinjie - 1))
				* heroSld;
		map.put(MemberAttrEnum.Tough.getIndex(), (int) Math.round(toughTotal));

		int potential = memberTemplate.getPotential();
		// TODO 队员装备属性
		int memberPotentialZb = 1;
		Double potentialTotal = (tough + level * memberTemplate.getPotential() + memberPotentialZb
				+ potential * (jinjie - 1)) * heroSld;
		map.put(MemberAttrEnum.Potential.getIndex(), (int) Math.round(potentialTotal));
		return map;
	}

	@Transactional
	public void memberCharacter(Long accountId, long memberId, Byte seat) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");

		Role role = roleService.getById(accountId);
		// 查找消耗材料是否满足 ()
		// 560 性格洗练材料
		// 570 性格洗练数量
		int[] consumeCoin = CommonUtil
				.stringToInt(ParameterConfig.map.get(Params.parameter_charater_560).getValue().split(Params.fenge));
		int[] consumeCountCoin = CommonUtil
				.stringToInt(ParameterConfig.map.get(Params.parameter_charater_570).getValue().split(Params.fenge));

		boolean tempFlag = roleService.checkCoinItemEnough(accountId, role, consumeCoin, consumeCountCoin);
		AssertUtil.asWarnTrue(tempFlag, "材料不足");
		roleService.addCoinItem(accountId, role, consumeCoin, CommonUtil.IntToMinus(consumeCountCoin), null);

		List<CharacterBean> list = JSONObject.parseArray(member.getCharacterstr(), CharacterBean.class);
		List<Integer> tempList = new ArrayList<Integer>();
		CharacterConfig.map.forEach((key, value) -> {
			boolean flag = true;
			for (CharacterBean cb : list) {
				if (cb.getCharacterId().intValue() == key.intValue()) {
					flag = false;
					break;
				}
			}
			if (flag) {
				tempList.add(key);
			}
		});
		Random random = new Random();
		int next = random.nextInt(tempList.size());
		int characterIdNew = tempList.get(next);
		CharacterBean characterBean = characterService.createCharacterBean(characterIdNew, seat.intValue());

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_character);
		msg.putLong(memberId);
		msg.putByte(seat);
		msg.putInt(characterBean.getCharacterId());
		msg.putInt(characterBean.getCharactervalue1Id());
		msg.putInt(characterBean.getCharactervalue2Id());
		MsgSender.sendMsg(msg);
	}

	@Transactional
	public void memberCharacterUpdate(long accountId, long memberId, Byte seat, int characterId, int charactervalue1Id,
			int charactervalue2Id) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");
		List<CharacterBean> list = JSONObject.parseArray(member.getCharacterstr(), CharacterBean.class);
		Iterator<CharacterBean> iterator = list.iterator();
		while (iterator.hasNext()) {
			CharacterBean cBean = iterator.next();
			if (cBean.getSeat().intValue() == seat.intValue()) {
				iterator.remove();
			}
		}
		CharacterBean characterBean = new CharacterBean();
		characterBean.setSeat(seat.intValue());
		characterBean.setCharacterId(characterId);
		characterBean.setCharactervalue1Id(charactervalue1Id);
		characterBean.setCharactervalue2Id(charactervalue2Id);
		list.add(characterBean);
		member.setCharacterstr(JSONObject.toJSONString(list));
		memberDao.update(member);

		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_member_character_update);
		msg.putLong(memberId);
		msg.putByte(seat);
		msg.putInt(characterBean.getCharacterId());
		msg.putInt(characterBean.getCharactervalue1Id());
		msg.putInt(characterBean.getCharactervalue2Id());
		MsgSender.sendMsg(msg);
	}

	@Transactional
	public void heroProficiencyUpdate(long accountId, long memberId, Integer heroTemplateId) {
		Member member = memberDao.getById(memberId);
		AssertUtil.asWarnTrue(member != null, "队员不存在");
		AssertUtil.asWarnTrue(member.getAccountId().equals(accountId), "队员不属于你");
		MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
		Byte work = memberTemplate.getWork();

		List<LikeHeroDaoBean> likeList = JSONObject.parseArray(member.getLikeHeros(), LikeHeroDaoBean.class);
		LikeHeroDaoBean likeHeroDaoBean = CommonUtil.getLikeHeroBeanByTemplateId(likeList, heroTemplateId);
		AssertUtil.asWarnTrue(likeHeroDaoBean != null, "没有此英雄");

		Long maxLv = HeroProficiencyConfig.map.values().stream().filter(p -> {
			return p.getWork().byteValue() == work.byteValue();
		}).count();
		AssertUtil.asWarnTrue(likeHeroDaoBean.getHeroLevel() < maxLv, "不能升级");

		List<HeroProficiencyTemplate> list = HeroProficiencyConfig.map.values().stream().filter(p -> {
			return p.getWork().byteValue() == work.byteValue()
					&& p.getLv().intValue() == likeHeroDaoBean.getHeroLevel();
		}).collect(Collectors.toList());
		AssertUtil.asWarnTrue(list != null && list.size() == 1, "模板错误");
		HeroProficiencyTemplate heroProficiencyTemplate = list.get(0);
		AssertUtil.asWarnTrue(likeHeroDaoBean.getLikeVal() >= heroProficiencyTemplate.getExp(), "经验不足");

		int[] itemIdArr = CommonUtil.stringToInt(heroProficiencyTemplate.getProp().split(Params.fenge));
		int[] countArr = CommonUtil.stringToInt(heroProficiencyTemplate.getNumber().split(Params.fenge));

		Role role = roleService.getById(accountId);
		boolean flag = roleService.checkCoinItemEnough(accountId, role, itemIdArr, countArr);
		AssertUtil.asWarnTrue(flag, "材料不足");

		// TODO 升级
		LikeHeroDaoBean hero = likeList.stream().filter(p -> p.getHeroTemplateId() == heroTemplateId.intValue())
				.findFirst().get();
		AssertUtil.asWarnTrue(hero.getSeat() != 0, "英雄必须上阵");

		roleService.addCoinItem(accountId, role, itemIdArr, CommonUtil.IntToMinus(countArr), null);

		hero.setLikeVal(0);
		hero.setHeroLevel(hero.getHeroLevel() + 1);

		member.setLikeHeros(JSONObject.toJSONString(likeList));
		memberDao.update(member);

		Message msg = new Message();
		msg.setMsgcd(MessageCode.hero_proficiency_update);
		msg.putLong(memberId);
		msg.putInt(heroTemplateId);
		msg.putInt(hero.getHeroLevel());

		MsgSender.sendMsg(msg);

	}

	//所有上阵队员总战力
	public int getPowerTotal(Long accountId) {
		Member[] memberArr = this.getWorkMembers(accountId);
		int total = 0;
		for (Member member : memberArr) {
			if (member != null) {
				MemberTemplate memberTemplate = MemberConfig.map.get(member.getTemplateId());
				int power = PowerUtils.getPower(memberTemplate, LevelUtil.getLevel(LevelType.Member, member.getExp()), member.getStage().intValue());
				total += power;
			}
		}
		return total;
	}

	// 计算队员附加属性 队员附加属性 = （队员基础属性 + 队员成长属性 + 队员装备属性 + 队员进阶属性n）* 英雄熟练度转化百分比
//	public static void main(String[] args) {
//		int dynamic = 32;
//		int level = 1;
//		int dynamicStar1 = 1;
//		int GrowthDynamic = 4;
//		int memberDynamicZb = 0;
//		int stage = 3;
//		int heroSld = 1;
//		double jinjie = Math.max(Math.pow(stage / 1.5, 1.5), 1);
//		System.out.println(jinjie);
//		
//		Double dynamicTotalPower = (dynamic + level * GrowthDynamic + memberDynamicZb + dynamicStar1 * (jinjie - 1));
//		Double dynamicTotalBattle = dynamicTotalPower * heroSld;
//	}
}
