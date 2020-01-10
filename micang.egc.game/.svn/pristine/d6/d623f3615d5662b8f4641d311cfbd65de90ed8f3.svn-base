package com.game.job.fight;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.common.enumerate.CampType;
import com.common.enumerate.WorkType;
import com.game.entity.Room;
import com.game.factory.Context;
import com.game.service.FightService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CreateHeroJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		CampType campType = (CampType)jobDataMap.get("campType");
		WorkType workType = (WorkType)jobDataMap.get("workType");
		FightService fightService = Context.getBean(FightService.class);
		fightService.generateHeroNode(room, campType, workType);
	}
}
