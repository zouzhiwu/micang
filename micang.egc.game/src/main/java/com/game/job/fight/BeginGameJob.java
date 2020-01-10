package com.game.job.fight;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.entity.Room;
import com.game.factory.Context;
import com.game.service.FightService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class BeginGameJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(BeginGameJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		logger.info(String.format("房间%d开始游戏", room.id));
		FightService fightService = Context.getBean(FightService.class);
		fightService.beginGame(room);
	}
}
