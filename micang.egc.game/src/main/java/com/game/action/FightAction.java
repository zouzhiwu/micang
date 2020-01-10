package com.game.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.game.common.MessageCode;
import com.game.service.FightService;

import io.netty.channel.Channel;

@Service
public class FightAction {

	private static final Logger logger = LoggerFactory.getLogger(FightAction.class);

	@Autowired
	private FightService fightService;
	
	@Action(MessageCode.msg_fight_load_finish)
	public void loadFinish(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		fightService.loadFinish(message.getAccountId());
		Message msg = new Message(message);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_fight_get_hero_node_list)
	public void getHeroNodeList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		fightService.getHeroNodeList(message.getAccountId());
	}
	
	@Action(MessageCode.msg_fight_get_build_node_list)
	public void getBuildNodeList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		fightService.getBuildNodeList(message.getAccountId());
	}
	
	@Action(MessageCode.msg_fight_get_monster_node_list)
	public void getMonsterNodeList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		fightService.getMonsterNodeList(message.getAccountId());
	}
	
	@Action(MessageCode.msg_fight_get_soldier_node_list)
	public void getSoldierNodeList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		fightService.getSoldierNodeList(message.getAccountId());
	}
	
	@Action(MessageCode.msg_fight_pve_reward)
	public void getFightPveReward(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		int checkpointId = message.getInt();
		boolean results = message.getBoolean();
		fightService.getFightPveReward(message.getAccountId(), checkpointId, results);
	}
	
	
	
	
}
