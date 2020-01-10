package com.game.helper;

import java.util.Arrays;
import java.util.List;

import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.game.entity.Room;

public class MsgHelper {
	
	public static void sendBroadcast(Message message, List<Long> accountIdList) {
		MsgSender.sendBroadcast(message, accountIdList);
	}
	
	public static void sendBroadcast(Message message, Room room) {
		List<Long> accountIdList = Arrays.asList(room.redPlayer.account.getId(), room.bluePlayer.account.getId());
		MsgSender.sendBroadcast(message, accountIdList);
	}
	
}
