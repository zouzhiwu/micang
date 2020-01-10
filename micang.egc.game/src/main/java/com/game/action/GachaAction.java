package com.game.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.game.common.MessageCode;
import com.game.entity.Gacha;
import com.game.entity.Item;
import com.game.service.GachaService;
import com.game.service.ItemService;
import com.game.util.ParameterUtil;

import io.netty.channel.Channel;

@Service
public class GachaAction {
	
	private static final Logger logger = LoggerFactory.getLogger(GachaAction.class);
	
	@Autowired
	private GachaService gachaService;
	@Autowired
	private ItemService itemService;
	
	
	@Action(MessageCode.msg_item_Gacha)
	public void itemGacha(Message message, Channel channel) throws Exception {
		Byte typeId = message.getByte();
		logger.info(String.format("RESV %d from accountId=%s typeId=%d", message.getMsgcd(), message.getAccountId(), typeId));
		gachaService.gachaMain(message.getAccountId(), typeId);
	}
	
	@Action(MessageCode.msg_item_GachaInfo)
	public void itemGachaInfo(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		Gacha gachaInfo = gachaService.getGachaInfo(message.getAccountId());
		Integer freeItemTemplateId = ParameterUtil.getGachaFreeItemTemplateId();
		Item item = itemService.itemsInfo(message.getAccountId(), freeItemTemplateId);
		int freeItemCount = item == null ? 0 : item.getCount();
		Message msg = new Message(message);
		msg.putByte(gachaInfo.getFreeGold().byteValue());
		msg.putByte(gachaInfo.getFreeDiamond().byteValue());
		msg.putInt(freeItemCount);
		msg.putByte(gachaInfo.getHalfDiamond().byteValue());
		msg.putByte(gachaInfo.getTenGold().byteValue());
		msg.putLong(gachaInfo.getLastGoldNow());
		MsgSender.sendMsg(msg);
	}
}
