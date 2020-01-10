package com.game.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.cb.util.Constant;
import com.common.entity.Account;
import com.common.entity.Role;
import com.common.enumerate.AccountState;
import com.common.enumerate.LevelType;
import com.game.common.MessageCode;
import com.game.service.AccountService;
import com.game.service.RoleService;
import com.game.util.LevelUtil;
import io.netty.channel.Channel;

@Service
public class AccountAction {

	private static final Logger logger = LoggerFactory.getLogger(AccountAction.class);

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@Action(MessageCode.msg_account_login)
	public void login(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		String loginName = message.getString();
		String passwd = message.getString();
		Byte deviceType = message.getByte();
		Account account = accountService.login(loginName, passwd, deviceType, channel);
		boolean isGaming = account.getState() == AccountState.Gaming;
		
		Role role = roleService.getById(account.getId());
		
		Message msg = new Message(message);
		msg.putLong(account.getId());
		msg.putBoolean(isGaming);
		msg.putLong(role.getGb());
		msg.putString(account.getLoginName());
		msg.putString(account.getNick());
		msg.putString(account.getPhoneNumber());
		msg.putInt(channel.attr(Constant.token).get());
		msg.putInt(role.getExpPot()); 
		msg.putInt(role.getJb().intValue());
		msg.putShort(LevelUtil.getLevel(LevelType.Club, role.getExp()));
		msg.putShort(role.getTili().shortValue());

		int powerTotal = accountService.getPowerTotal(account.getId());
		msg.putInt(powerTotal);
		// TODO 队徽
		msg.putString("123");
		
		MsgSender.sendMsg(msg, channel);
	}
	
	@Action(MessageCode.msg_account_heartbeat)
	public void requestHeartbeat(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		accountService.requestHeartbeat(message.getAccountId());
		Message msg = new Message(message);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_account_online_list)
	public void getOnlineList(Message message, Channel channel) throws Exception {
		boolean isContainSelf = message.getBoolean();
		short count = message.getShort();
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		accountService.getOnlineList(message.getAccountId(), isContainSelf, count);
	}

}
