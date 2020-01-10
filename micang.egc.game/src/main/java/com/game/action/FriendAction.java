package com.game.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.common.constant.ParameterIdConstant;
import com.common.constant.UserRedisKeyPrefix;
import com.common.template.ParameterTemplate;
import com.game.common.MessageCode;
import com.game.common.RedisClient;
import com.game.config.ParameterConfig;
import com.game.dao.RoleDao;
import com.game.entity.vo.ApplyAddFriendVO;
import com.game.entity.vo.Friend;
import com.game.entity.vo.UserRecord;
import com.game.service.FriendService;

import io.netty.channel.Channel;

@Service
public class FriendAction {
	private static final Logger logger = LoggerFactory.getLogger(FriendAction.class);

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private RedisClient<UserRecord> redisClient;
	
	@Autowired
	private RoleDao roleDao;
	
	@Action(MessageCode.msg_friend_get_list)
	public void friendGetList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		List<Friend> friendList = friendService.getFriendList(message.getAccountId());
		UserRecord userRecord = redisClient.getValue(UserRedisKeyPrefix.recordPrefix + message.getAccountId());
		Message msg = new Message(message);
		msg.putShort(userRecord.getOutPhysical());
		msg.putShort(userRecord.getPutPhysical());
		msg.putShort(friendList.size());
		friendList.stream().forEach(friendVO -> {
			msg.putLong(friendVO.getAccountId());
			msg.putString(friendVO.getLogo());
			msg.putString(friendVO.getNickName());
			msg.putShort(friendVO.getLv());
			msg.putInt(friendVO.getFighting());
			msg.putBoolean(friendVO.getStart());
			msg.putLong(friendVO.getOutTime());
			msg.putBoolean(friendVO.getPhysicalGive());
			msg.putBoolean(friendVO.getPhysicalGet());
		});
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_set_physicals)
	public void setPhysicals(Message message, Channel channel) throws Exception {
		Short size = message.getShort();
		logger.info(String.format("RESV %d from accountId=%s size=%d", message.getMsgcd(), message.getAccountId(), size));
		List<Long> friendsId = new ArrayList<Long>();
		for (int i = 0; i < size; i++) {
			friendsId.add(message.getLong());
		}
		Boolean setPhysicals = friendService.setPhysicals(message.getAccountId(), friendsId );
		Message msg = new Message(message);
		msg.putBoolean(setPhysicals);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_get_physicals)
	public void getPhysicals(Message message, Channel channel) throws Exception {
		Short size = message.getShort();
		logger.info(String.format("RESV %d from accountId=%s size=%d", message.getMsgcd(), message.getAccountId(), size));
		List<Long> friendsId = new ArrayList<Long>();
		for (int i = 0; i < size; i++) {
			friendsId.add(message.getLong());
		}
		Boolean getPhysicals = friendService.getPhysicals(message.getAccountId(), friendsId );
		Message msg = new Message(message);
		msg.putBoolean(getPhysicals);
		msg.putShort(roleDao.getById(message.getAccountId()).getTili());
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_del)
	public void friendDel(Message message, Channel channel) throws Exception {
		Long firendId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s friendId = %d", message.getMsgcd(), message.getAccountId(), firendId));
		boolean delFriend = friendService.delFriend(message.getAccountId(), firendId);
		Message msg = new Message(message);
		msg.putBoolean(delFriend);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_recommend)
	public void recommend(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		Set<Friend> friendList = friendService.recommendFriend(message.getAccountId());
		ParameterTemplate pT = ParameterConfig.map.get(ParameterIdConstant.index620);
		int i = new Integer(pT.getValue());
		Message msg = new Message(message);
		msg.putShort(friendList.size() > i ? i : friendList.size());
		for (Friend friendVO : friendList) {
			msg.putLong(friendVO.getAccountId());
			msg.putString(friendVO.getLogo());
			msg.putString(friendVO.getNickName());
			msg.putShort(friendVO.getLv());
			msg.putInt(friendVO.getFighting());
			msg.putBoolean(friendVO.getStart());
			msg.putLong(friendVO.getOutTime());
			i--;
			if (i == 0) {
				break;
			}
		}
		MsgSender.sendMsg(msg);
	}
	@Action(MessageCode.msg_friend_search)
	public void search(Message message, Channel channel) throws Exception {
		String firendId = message.getString();
		logger.info(String.format("RESV %d from accountId=%s friendId = %s", message.getMsgcd(), message.getAccountId(), firendId));
		Friend friendVO = friendService.searchFriend(firendId);
		Message msg = new Message(message);
		if (friendVO != null) {
			msg.putShort(1);
			msg.putLong(friendVO.getAccountId());
			msg.putString(friendVO.getLogo());
			msg.putString(friendVO.getNickName());
			msg.putShort(friendVO.getLv());
			msg.putInt(friendVO.getFighting());
			msg.putBoolean(friendVO.getStart());
			msg.putLong(friendVO.getOutTime());
		}else {
			msg.putShort(0);
		}
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_apply_add)
	public void applyAdd(Message message, Channel channel) throws Exception {
		Long firendId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s friendId = %d", message.getMsgcd(), message.getAccountId(), firendId));
		ApplyAddFriendVO applyAddFriend = friendService.applyAddFriend(message.getAccountId(), firendId);
		Message msg = new Message(message);
		msg.putBoolean(applyAddFriend.isRes());
		msg.putInt(applyAddFriend.getMesgCode());
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_get_apply)
	public void getApply(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		List<Friend> friendList = friendService.getFriendApply(message.getAccountId());
		Message msg = new Message(message);
		msg.putShort(friendList.size());
		friendList.stream().forEach(friendVO -> {
			msg.putLong(friendVO.getAccountId());
			msg.putString(friendVO.getLogo());
			msg.putString(friendVO.getNickName());
			msg.putShort(friendVO.getLv());
			msg.putInt(friendVO.getFighting());
			msg.putBoolean(friendVO.getStart());
			msg.putLong(friendVO.getOutTime());
		});
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_dispose_apply)
	public void disposeApply(Message message, Channel channel) throws Exception {
		boolean type = message.getBoolean();
		short size = message.getShort();
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		List<Long> friendsId = new ArrayList<Long>();
		for (int i = 0; i < size; i++) {
			friendsId.add(message.getLong());
		}
		Boolean setPhysicals = friendService.disposeFriendApply(message.getAccountId(), friendsId, type);
		Message msg = new Message(message);
		msg.putBoolean(setPhysicals);
		msg.putBoolean(type);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_get_blacklist)
	public void getBlacklist(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		List<Friend> friendList = friendService.getBlacklistFriend(message.getAccountId());
		Message msg = new Message(message);
		msg.putShort(friendList.size());
		friendList.stream().forEach(friendVO -> {
			msg.putLong(friendVO.getAccountId());
			msg.putString(friendVO.getLogo());
			msg.putString(friendVO.getNickName());
			msg.putShort(friendVO.getLv());
			msg.putInt(friendVO.getFighting());
			msg.putBoolean(friendVO.getStart());
			msg.putLong(friendVO.getOutTime());
		});
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_remove_toBlacklist)
	public void removeToBlacklist(Message message, Channel channel) throws Exception {
		Long firendId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s friendId = %d", message.getMsgcd(), message.getAccountId(), firendId));
		Boolean res = friendService.removeFrienToBlacklist(message.getAccountId(), firendId);
		Message msg = new Message(message);
		msg.putBoolean(res);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_friend_add_toBlacklist)
	public void addToBlacklist(Message message, Channel channel) throws Exception {
		Long firendId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s friendId = %d", message.getMsgcd(), message.getAccountId(), firendId));
		Boolean res = friendService.addFrienToBlacklist(message.getAccountId(), firendId);
		Message msg = new Message(message);
		msg.putBoolean(res);
		MsgSender.sendMsg(msg);
	}
	
}
