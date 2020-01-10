package com.game.action.pb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cb.cache.ChannelCache;
import com.cb.msg.MsgSender;
import com.cb.msg.PbAction;
import com.cb.msg.PbMessage;
import com.game.common.MessageCode;
import com.game.proto.Account.ReqLogin;
import com.game.proto.Account.ResLogin;

import io.netty.channel.Channel;

@Service
public class AccountAction {

	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);
	
	@PbAction(MessageCode.msg_account_login)
	public void login(PbMessage<ReqLogin> msg) {
		ReqLogin req = msg.getBody();
		logger.info(req.toString());
		// TODO 业务逻辑
		Channel channel = MsgSender.getCurrChannel();
		long accountId = 1000;
		ChannelCache.putChannel(accountId, channel);
		PbMessage<ResLogin> message = new PbMessage<ResLogin>();
		message.setMsgcd(msg.getMsgcd());
		ResLogin res = ResLogin.newBuilder().setId(10000).setName("test").build();
		message.setBody(res);
		MsgSender.sendMsg(message);
	}
}
