package com.game.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.msg.Action;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.game.common.MessageCode;
import com.game.entity.Mail;
import com.game.service.MailService;

import io.netty.channel.Channel;

@Service
public class MailAction {
	@Autowired
	private MailService mailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	
	@Action(MessageCode.msg_mail_get_email)
	public void getEmail(Message message, Channel channel) throws Exception {
		logger.info(String.format("RESV %d from accountId=%s", message.getMsgcd(), message.getAccountId()));
		List<Mail> email = mailService.getEmail(message.getAccountId());
		Message msg = new Message(message);
		msg.putShort(email.size());
		email.stream().forEach(mail -> {
			msg.putLong(mail.getId());
			//msg.putInt(mail.getTemplateId());
			msg.putString("admin");
			msg.putString(mail.getTitle());
			msg.putString(mail.getContent());
			msg.putLong(mail.getCreateTime());
			msg.putBoolean(mail.getReadTime() == 0 ? false : true);
			msg.putBoolean(mail.getReceiveTime() == 0 ? false : true);
			msg.putShort(mail.getItemList().size());
			mail.getItemList().stream().forEach(item -> {
				msg.putInt(item.getTemplateId());
				msg.putInt(item.getCount());
			});
		});
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_mail_del)
	public void delMail(Message message, Channel channel) throws Exception {
		long mailId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s mailId = ", message.getMsgcd(), message.getAccountId()));
		Boolean delMail = mailService.delMail(message.getAccountId(), mailId);
		Message msg = new Message(message);
		msg.putBoolean(delMail);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_mail_get_attachment)
	public void getAttachment(Message message, Channel channel) throws Exception {
		long mailId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s mailId = ", message.getMsgcd(), message.getAccountId()));
		Boolean delMail = mailService.getAttachment(message.getAccountId(), mailId);
		Message msg = new Message(message);
		msg.putBoolean(delMail);
		MsgSender.sendMsg(msg);
	}
	
	@Action(MessageCode.msg_mail_a_key)
	public void aKey(Message message, Channel channel) throws Exception {
		Byte type = message.getByte();
		logger.info(String.format("RESV %d from accountId=%s type=%s", message.getMsgcd(), message.getAccountId(), type));
		Boolean delMail = mailService.onekeyOperation(message.getAccountId(), type);
		Message msg = new Message(message);
		msg.putBoolean(delMail);
		MsgSender.sendMsg(msg);
	}

	
	@Action(MessageCode.msg_mail_read_mail)
	public void readMail(Message message, Channel channel) throws Exception {
		long mailId = message.getLong();
		logger.info(String.format("RESV %d from accountId=%s mailId = ", message.getMsgcd(), message.getAccountId()));
		Boolean readMail = mailService.openMail(message.getAccountId(), mailId);
		Message msg = new Message(message);
		msg.putBoolean(readMail);
		MsgSender.sendMsg(msg);
	}
}
