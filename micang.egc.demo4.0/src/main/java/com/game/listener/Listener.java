package com.game.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cb.lisener.AbsLisener;
import com.game.common.MessageCode;

import io.netty.channel.Channel;

@Service
public class Listener extends AbsLisener {
	private static final Logger logger = LoggerFactory.getLogger(Listener.class);
	
	@Override
	public void channelInactive(Channel channel) {
		logger.info("channelInactive");
	}
	
	@Override
	public String getCbActionPath() {
		return "com.game.action";
	}
	
	@Override
	public String getPbActionPath() {
		return "com.game.action.pb";
	}
	
	@Override
	public short getLoginMsgcd() {
		return MessageCode.msg_account_login;
	}

}
