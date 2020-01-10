package com.game.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.common.constant.AccountConstant;

@Component
public class SchedulerService {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MailService mailService;
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);
	
	
	@Scheduled(fixedDelay=AccountConstant.heart_beat_cycle * 1000)
	public void each10Second() {
		logger.info("heart_beat");
		accountService.testHearbeat();
	}
	
	@Scheduled(cron="0 0 4 * * ?")
	public void deletePublicMail() {
		mailService.deletePublicMail();
	}
	
}
