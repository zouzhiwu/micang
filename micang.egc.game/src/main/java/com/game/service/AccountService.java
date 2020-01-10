package com.game.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cb.cache.ChannelCache;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.cb.util.ChannelUtil;
import com.common.constant.AccountConstant;
import com.common.entity.Account;
import com.common.entity.GoldRecord;
import com.common.entity.JewelRecord;
import com.common.entity.Role;
import com.common.enumerate.AccountState;
import com.common.enumerate.ProfileType;
import com.common.helper.RandomHelper;
import com.common.helper.TimeHelper;
import com.common.util.AssertUtil;
import com.game.common.MessageCode;
import com.game.config.AppConfig;
import com.game.dao.AccountDao;
import com.game.dao.GoldRecordDao;
import com.game.dao.JewelRecordDao;
import com.game.entity.Player;
import com.game.helper.MsgHelper;
import com.game.model.Model;

import io.netty.channel.Channel;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private JewelRecordDao jewelRecordDao;
	@Autowired
	private GoldRecordDao goldRecordDao;
	@Autowired
	private MemberService memberService;
	@Autowired
	private HeroService heroService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ItemService itemService;
	@Autowired 
	private DuplicateService duplicateService;

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Transactional
	public synchronized Account login(String loginName, String passwd, Byte deviceType, Channel channel) {
		AssertUtil.asWarnTrue(!StringUtils.isBlank(passwd), "密码不能为空");
		Account account = accountDao.getByName(loginName);
		Integer guildId = null;
		if (account == null) {
			// 创建帐户
			account = new Account();
			account.setLoginName(loginName);
			account.setNick(loginName);
			account.setAwtar(loginName);
			account.setPasswd(passwd);
			account.setState(AccountState.Online);
			account.setIsTest(0);
			accountDao.add(account);

			Role role = new Role();
			role.setAccountId(account.getId());
			role.setExpPot(177001);
			role.setJb(100000L);
			role.setGb(1000000L);
			roleService.add(role);

			//初始化背包
			itemService.init(account.getId());
			//初始化关卡
			duplicateService.init(account.getId(), null);
			if (AppConfig.getProfile() != ProfileType.Production) {
				// 添加所有队员
				memberService.addAllMember(account.getId());
				// 添加所有英雄
				heroService.addAllHero(account.getId());
			}
		} else {
			AssertUtil.asWarnTrue(account.getPasswd().equals(passwd), "密码不正确");
			account.setState(AccountState.Online);
			accountDao.update(account);
		}
		Model.getInstance().accountMap.put(account.getId(), account);
		Integer token = RandomHelper.getRandom();
		ChannelUtil.setHeartbeatTime(channel, TimeHelper.getTime());
		ChannelUtil.setToken(channel, token);
		ChannelUtil.setGuildId(channel, guildId);
		ChannelUtil.setDeviceType(channel, deviceType);
		ChannelCache.putChannel(account.getId(), channel);
		return account;
	}
	
	public void requestHeartbeat(Long accountId) {
		logger.info(String.format("玩家%s心跳请求", accountId));
		int currTime = TimeHelper.getTime();
		Channel channel = ChannelCache.getChannel(accountId);
		AssertUtil.asWarnTrue(channel != null, String.format("玩家%s的channel不存在", accountId));
		boolean isSuccess = ChannelUtil.setHeartbeatTime(channel, currTime);
		AssertUtil.asWarnTrue(isSuccess, String.format("accountId=%s 心跳请求失败", accountId));
	}
	
	public void getOnlineList(Long accountId, Boolean isContainSelf, Short count) {
		List<Long> onlineIdList = Model.getInstance().accountMap.keySet().stream().collect(Collectors.toList());
		List<Account> onlineList = new ArrayList<Account>();
		if (!CollectionUtils.isEmpty(onlineIdList)) {
			Collections.shuffle(onlineIdList);
			int size = Math.min(10, onlineIdList.size());
			for (int i = 0; i < size; i++) {
				Account account = Model.getInstance().accountMap.get(onlineIdList.get(i));
				if (!onlineList.contains(account)) {
					if (accountId.equals(account.getId())) {
						if (isContainSelf) {
							onlineList.add(Model.getInstance().accountMap.get(onlineIdList.get(i)));
						}
					} else {
						onlineList.add(Model.getInstance().accountMap.get(onlineIdList.get(i)));
					}
				}
				if (onlineList.size() >= count) {
					break;
				}
			}
		}
		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_account_online_list);
		msg.putShort(onlineList.size());
		for (int i = 0; i < onlineList.size(); i++) {
			Account account = onlineList.get(i);
			Player player = Model.getInstance().playerMap.get(account.getId());
			boolean isFighting = player != null && player.roomId > 0;
			msg.putLong(account.getId());
			msg.putString(account.getNick());
			msg.putString(account.getAwtar());
			msg.putBoolean(isFighting);
		}
		MsgSender.sendMsg(msg);
	}
	
	public void testHearbeat() {
		int testTime = TimeHelper.getTime() - 3 * AccountConstant.heart_beat_cycle;
		Stream<Channel> logoutChannelList = ChannelCache.getChannelMap().values().stream().filter(ch -> ChannelUtil.getHeartbeatTime(ch) < testTime);
		List<Long> logoutIdlist = logoutChannelList.mapToLong(ch -> ChannelUtil.getAccountId(ch)).collect((Supplier<ArrayList<Long>>) ArrayList::new, ArrayList::add, ArrayList::addAll);
		if (CollectionUtils.isNotEmpty(logoutIdlist)) {
			Message msg = new Message();
			msg.setMsgcd(MessageCode.msg_account_logout);
			MsgHelper.sendBroadcast(msg, logoutIdlist);
			logoutIdlist.forEach(accountId -> {
				Role role = roleService.getById(accountId);
				role.setOutTime(TimeHelper.getTime());
				roleService.update(role);
				ChannelCache.removeChannel(accountId);
			});
		}
	}
	
	@Transactional
	public Long updateJb(Long accountId, Long addjb, String remark) {
		roleService.updateJb(accountId, addjb);
		Role role = roleService.getById(accountId);
		JewelRecord jewelRecord = new JewelRecord();
		jewelRecord.setJb(addjb);
		jewelRecord.setCreateTime(TimeHelper.getTime());
		jewelRecord.setRemark(remark);
		jewelRecord.setBalance(role.getJb());
		jewelRecordDao.create(jewelRecord);
		return role.getJb();
	}
	
	@Transactional
	public Long updateGb(Long accountId, Long addgb, String remark) {
		roleService.updateGb(accountId, addgb);
		Role role = roleService.getById(accountId);
		GoldRecord goldRecord = new GoldRecord();
		goldRecord.setGb(addgb);
		goldRecord.setCreateTime(TimeHelper.getTime());
		goldRecord.setRemark(remark);
		goldRecord.setBalance(role.getJb());
		goldRecordDao.create(goldRecord);
		return role.getGb();
	}
	public int getPowerTotal(Long accountId){
		return memberService.getPowerTotal(accountId);
	}
}
