package com.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.util.CBUtils;
import com.common.enumerate.MailOperationType;
import com.common.helper.TimeHelper;
import com.common.util.AssertUtil;
import com.game.dao.PrivateMailDao;
import com.game.dao.PublicMailAccountDao;
import com.game.dao.PublicMailDao;
import com.game.entity.Item;
import com.game.entity.Mail;
import com.game.entity.PrivateMail;
import com.game.entity.PublicMail;
import com.game.entity.PublicMailAccount;

@Service
public class MailService {
	@Autowired
	private PrivateMailDao privateMailDao;
	
	@Autowired
	private PublicMailDao publicMailDao;
	
	@Autowired
	private PublicMailAccountDao publicMailAccountDao;
	
	@Autowired
	private ItemService itemService;
	
	public PrivateMail getPrivateMail(Long accountId) {
		PrivateMail privateMail = privateMailDao.findById(accountId);
		if (null == privateMail) {
			privateMail = new PrivateMail();
			privateMail.setAccountId(accountId);
			List<Mail> email = new ArrayList<Mail>();
			privateMail.setMailList(CBUtils.getBytesFromObject(email));
			privateMailDao.create(privateMail);
		}
		return privateMail;
	};
	
	/***
	 * 给所有玩家发送系统邮件，该方法不能写到Action，后台通过9003端口发送消息调用
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param name 发件人
	 * @param itemList 道具列表
	 * @return
	 */
	public boolean createPublicMail(String title, String name, String content, List<Item> itemList) {
		// 验证道具模板Id和数量
		AssertUtil.asWarnTrue(!StringUtils.isBlank(title), "标题不能为空！");
		AssertUtil.asWarnTrue(!StringUtils.isBlank(name), "发送人不能为空");
		AssertUtil.asWarnTrue(!StringUtils.isBlank(content), "正文不能为空");
		// 创建邮件 存储到数据库public_mail表
		PublicMail publicMail = new PublicMail();
		publicMail.setCreateTime(TimeHelper.getTime());
		publicMail.setContent(content);
		publicMail.setItemList(CBUtils.getBytesFromObject(itemList));
		publicMail.setName(name);
		publicMail.setTitle(title);
		publicMailDao.create(publicMail);
		// TODO 给玩家推送通知消息
		return true;
	}
	
	/***
	 * 给指定玩家发送系统邮件
	 * @param accountId 接收的玩家Id
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param name 发件人
	 * @param itemList 道具列表
	 * @return
	 */
	public boolean createPrivateMail(Long accountId, String title, String name, String content, List<Item> itemList) {
		// 验证道具模板Id和数量
		AssertUtil.asWarnTrue(null != accountId, "收件人不能为空！");
		AssertUtil.asWarnTrue(!StringUtils.isBlank(title), "标题不能为空！");
		AssertUtil.asWarnTrue(!StringUtils.isBlank(name), "发送人不能为空");
		AssertUtil.asWarnTrue(!StringUtils.isBlank(content), "正文不能为空");
		PrivateMail privateMail = getPrivateMail(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> mailList = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		// 校验处理邮箱是否已满，满了则自动清理邮件
		checkoutEmail(mailList);
		// 创建邮件 存储到数据库private_mail表，标记为私有邮件
		Mail mail = new Mail();
		mail.setCreateTime(TimeHelper.getTime());
		mail.setContent(content);
		mail.setId(mailList.size() == 0 ? 0 : mailList.get(mailList.size() - 1).getId() + 1);
		mail.setItemList(itemList);
		mail.setName(name);
		mail.setReadTime(0);
		mail.setReceiveTime(0);
		mailList.add(mail);
		privateMail.setMailList(CBUtils.getBytesFromObject(mailList));
		privateMailDao.update(privateMail);
		//给玩家推送通知消息
		return true;
	}
	
	/**
	 * 删除多余邮件，邮件上限99
	 * @param email
	 */
	private void checkoutEmail(List<Mail> email) {
		if (email.size() > 99) {
			for (int i = 0; email.size() < 99;) {
				email.remove(i);
			}
		}
	}

	/**
	 * 获取公共邮件
	 **/
	public List<Mail> getEmail(Long accountId) {
		PrivateMail privateMail = getPrivateMail(accountId);
		// 公共邮件：所有玩家都发送的邮件，私有邮件：给指定的单个玩家或特定人群发送的邮件，工会邮件用私有邮件发送
		// 获取公共邮件：
		// 第1步骤. 查询系统发送的公共邮件 select pm.* from public_mail pm left join public_mail_account pma on pm.mailId = pma.id
		List<PublicMail> publicMails = publicMailDao.findByAccountId(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> mailList = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		// 第2步骤.将第1步骤的邮件插入public_mail_account表，表示该邮件已放入邮箱
		if (publicMails.size() > 0) {
			publicMails.stream().forEach(publicMail -> {
				PublicMailAccount publicMailAccount = new PublicMailAccount();
				publicMailAccount.setAccountId(accountId);
				publicMailAccount.setMailId(publicMail.getId());
				publicMailAccountDao.create(publicMailAccount);
				Mail mail = publicMailToMail(publicMail, mailList);
				mailList.add(mail);
			});
			privateMail.setMailList(CBUtils.getBytesFromObject(mailList));
			// 第3步骤.校验处理邮箱是否已满，满了则自动清理邮件
			checkoutEmail(mailList);
			//保存新邮箱
			privateMailDao.update(privateMail);
		}
		// 第4步骤.返回查询结果
		return mailList == null ? new ArrayList<Mail>() : mailList;
	}

	/**公共邮件转化为个人邮箱内邮件*/
	private Mail publicMailToMail(PublicMail publicMail, List<Mail> email) {
		Mail mail = new Mail();
		mail.setContent(publicMail.getContent());
		mail.setCreateTime(publicMail.getCreateTime());
		mail.setId(email.size() == 0 ? 0 : email.get(email.size() - 1).getId() + 1);
		@SuppressWarnings("unchecked")
		List<Item> mailList = (List<Item>) CBUtils.getObjectFromBytes(publicMail.getItemList());
		mail.setItemList(mailList);
		mail.setName(publicMail.getName());
		mail.setReadTime(0);
		mail.setReceiveTime(0);
		mail.setTitle(publicMail.getTitle());
		return mail;
	}

	/***
	 * 阅读邮件
	 * @param accountId
	 * @param privateMailId
	 */
	public Boolean openMail(Long accountId, Long privateMailId) {
		// private_mail表的记录设置已读时间，注意，已读时间默认为0,已读时间大于0标识已读，这个时间表示读取的时间
		AssertUtil.asWarnTrue(null != privateMailId, "个人邮件id不能为空");
		PrivateMail privateMail = getPrivateMail(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> email = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		email.stream().forEach(mail -> {
			if (mail.getId() == privateMailId.longValue() && mail.getReadTime() == 0) {
				mail.setReadTime(TimeHelper.getTime());
			}
		});
		privateMail.setMailList(CBUtils.getBytesFromObject(email));
		privateMailDao.update(privateMail);
		return true;
	}
	
	/**
	 * 批量删除
	 * @param accountId
	 * @param privateMailIds
	 */
	public Boolean deleteMail(Long accountId, List<Long> privateMailIds) {
		AssertUtil.asWarnTrue(0 != privateMailIds.size(), "个人邮件id不能为空");
		PrivateMail privateMail = getPrivateMail(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> mailList = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		for (Iterator<Mail> iterator = mailList.iterator(); iterator.hasNext();) {
			//判断是否满足删除条件
			Mail mail = iterator.next();
			if (privateMailIds.indexOf(mail.getId()) != -1) {
				// 当有附件未领取时，领取附件后删除
				if (mail.getReceiveTime() == 0 && mail.getItemList().size() != 0) {
					itemService.settlementItem(accountId, mail.getItemList());
				}
				iterator.remove();
			}
		}
		privateMail.setMailList(CBUtils.getBytesFromObject(mailList));
		privateMailDao.update(privateMail);
		return true;
	}
	
	/**
	 * 批量领取道具
	 * @param accountId
	 * @param privateMailIds
	 */
	public Boolean receiveAttachment(Long accountId, List<Long> privateMailIds) {
		// 领取道具
		AssertUtil.asWarnTrue(0 != privateMailIds.size(), "个人邮件id不能为空");
		PrivateMail privateMail = getPrivateMail(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> mailList = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		for (int i = 0; i < mailList.size(); i++) {
			//判断是否满足领取条件
			Mail mail = mailList.get(i);
			if (privateMailIds.indexOf(mail.getId()) != -1 && mail.getReceiveTime() == 0) {
				// 当有附件未领取时，领取附件后删除
				if (mail.getItemList().size() != 0) {
					itemService.settlementItem(accountId, mail.getItemList());
				}
				mail.setReceiveTime(TimeHelper.getTime());
			}
		}
		privateMail.setMailList(CBUtils.getBytesFromObject(mailList));
		privateMailDao.update(privateMail);
		return true;
	}

	public Boolean delMail(Long accountId, Long mailId) {
		AssertUtil.asWarnTrue(mailId != null, "个人邮件id不能为空");
		ArrayList<Long> arrayList = new ArrayList<Long>();
		arrayList.add(mailId);
		return deleteMail(accountId, arrayList);
	}

	public Boolean getAttachment(Long accountId, Long mailId) {
		AssertUtil.asWarnTrue(mailId != null, "个人邮件id不能为空");
		ArrayList<Long> arrayList = new ArrayList<Long>();
		arrayList.add(mailId);
		return receiveAttachment(accountId, arrayList);
	}

	public Boolean onekeyOperation(Long accountId, Byte type) {
		MailOperationType mailOperationType = MailOperationType.getType(type);
		AssertUtil.asWarnTrue(mailOperationType != null, "操作类型错误！");
		List<Long> privateMailIds = new ArrayList<Long>();
		PrivateMail privateMail = getPrivateMail(accountId);
		@SuppressWarnings("unchecked")
		List<Mail> mailList = (List<Mail>) CBUtils.getObjectFromBytes(privateMail.getMailList());
		mailList.stream().forEach(mail -> privateMailIds.add(mail.getId()));
		Boolean res = null;
		if (mailOperationType == MailOperationType.delMail) {
			res = deleteMail(accountId, privateMailIds);
		}else {
			res = receiveAttachment(accountId, privateMailIds);
		}
		return res;
	}
	
	/**
	 * 删除一个月前的公共邮件
	 */
	public void deletePublicMail() {
		Integer time = TimeHelper.getTime();
		time = time - (60 * 60 * 24 *30);
		List<PublicMail> publicMailList = publicMailDao.findByCreateTime(time);
		publicMailList.stream().forEach(publicMail -> {
			publicMailAccountDao.delByMailId(publicMail.getId());
			publicMailDao.delById(publicMail.getId());
		});
	}
	
}
