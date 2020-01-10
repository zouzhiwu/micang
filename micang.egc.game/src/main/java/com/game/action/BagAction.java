package com.game.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.cb.util.CBUtils;
import com.game.common.MessageCode;
import com.game.entity.Item;
import com.game.entity.Bag;
import com.game.service.ItemService;

import io.netty.channel.Channel;

@Service
public class BagAction {

	private static final Logger logger = LoggerFactory.getLogger(BagAction.class);

	@Autowired
	private ItemService itemService;
	
	@SuppressWarnings("unchecked")
	@Action(MessageCode.msg_item_get_list)
	public void getItemList(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		Bag bag = itemService.getItemList(message.getAccountId());
		Message msg = new Message(message);
		if (null == bag) {
			msg.putShort(0);
			MsgSender.sendMsg(msg);
		} else {
			Map<Integer, Item> propsMap = (Map<Integer, Item>)CBUtils.getObjectFromBytes(bag.getItemBytes());
			msg.putShort(propsMap.size());
			propsMap.forEach((key, props) -> {
				msg.putInt(props.getTemplateId());
				msg.putInt(props.getCount());
			});
			MsgSender.sendMsg(msg);
		}
	}
	
	@Action(MessageCode.msg_item_use)
	public void useItem(Message message, Channel channel) throws Exception {
		Integer templateId = message.getInt();
		Integer count = message.getInt();
		logger.info(String.format("RESV %d from accountId=%s templateId=%d count=%d"
				, message.getMsgcd(), message.getAccountId(), templateId, count));
		Item item = itemService.useItem(templateId,count,message.getAccountId());
		Message msg = new Message(message);
		msg.putInt(item.getTemplateId());
		msg.putInt(item.getCount());
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_item_add)
	public void itemAdd(Message message, Channel channel) throws Exception {
		Integer templateId = message.getInt();
		Integer count = message.getInt();
		logger.info(String.format("RESV %d from accountId=%s templateId=%d count=%d"
				, message.getMsgcd(), message.getAccountId(), templateId, count));
		Item item = itemService.itemAdd(templateId,count,message.getAccountId());
		Message msg = new Message(message);
		msg.putInt(item.getTemplateId());
		msg.putInt(item.getCount());
		MsgSender.sendMsg(msg);
	}
	
}
