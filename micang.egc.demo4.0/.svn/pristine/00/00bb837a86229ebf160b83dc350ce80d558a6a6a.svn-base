package com.game.job.fight;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.common.enumerate.TacticsType;
import com.game.entity.Room;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class InitTacticesJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		TacticsType tacticsType = (TacticsType)jobDataMap.get("tacticsType");
		room.changeTactices(tacticsType);
	}
}
