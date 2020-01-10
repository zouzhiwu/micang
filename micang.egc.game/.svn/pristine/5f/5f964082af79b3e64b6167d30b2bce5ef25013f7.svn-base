package com.game.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.common.enumerate.OperateType;
import com.game.common.MessageCode;
import com.game.service.RoomService;

import io.netty.channel.Channel;

@Service
public class RoomAction {

	private static final Logger logger = LoggerFactory.getLogger(RoomAction.class);

	@Autowired
	private RoomService roomService;
	
	@Action(MessageCode.msg_room_challenge_request)
	public void requestChallenge(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		Long beAccountId = message.getLong();		// 被挑战的玩家Id
		roomService.requestChallenge(message.getAccountId(), beAccountId);
		Message msg = new Message(message);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_room_challenge_response)
	public void responseChallenge(Message message, Channel channel) {
		Long asAccountId = message.getLong();
		Boolean isAgree = message.getBoolean();
		Boolean isFast = message.getBoolean();
		if (isFast) {
			roomService.fastFight(message.getAccountId(), asAccountId, isAgree);
		} else {
			roomService.responseChallenge(message.getAccountId(), asAccountId, isAgree);
		}
	}
	
	@Action(MessageCode.msg_room_choose_member)
	public void chooseMember(Message message, Channel channel) throws Exception {
		Long topMemberId = message.getLong();		// 上单队员Id
		Long midMemberId = message.getLong();		// 中单队员Id
		Long junMemberId = message.getLong();		// 打野队员Id
		Long supMemberId = message.getLong();		// 辅助队员Id
		Long adcMemberId = message.getLong();		// 输出队员Id
		logger.info(String.format("RESV %d from accountId=%s topMemberId=%s midMemberId=%s junMemberId=%s supMemberId=%s adcMemberId=%s"
				, message.getMsgcd(), message.getAccountId(), topMemberId, midMemberId, junMemberId, supMemberId, adcMemberId));
		roomService.chooseMember(message.getAccountId(), topMemberId, midMemberId, junMemberId, supMemberId, adcMemberId);
		Message msg = new Message(message);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_room_choose_hero)
	public void chooseHero(Message message, Channel channel) throws Exception {
		Byte operateIndex = message.getByte();			// 操作类型
		Integer heroTemplateId = message.getInt();		// 选择的英雄Id
		logger.info(String.format("RESV %d from accountId=%s operateIndex=%s heroTemplateId=%s", message.getMsgcd(), message.getAccountId(), operateIndex, heroTemplateId));
		OperateType operateType = OperateType.getType(operateIndex);
		if (operateType == OperateType.Choose) {
			roomService.chooseHero(message.getAccountId(), heroTemplateId);
		} else {
			roomService.banHero(message.getAccountId(), heroTemplateId);
		}
		Message msg = new Message(message);
		MsgSender.sendMsg(msg);
	}
}
