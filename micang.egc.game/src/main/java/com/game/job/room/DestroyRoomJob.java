package com.game.job.room;

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

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DestroyRoomJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(DestroyRoomJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("ShowLayoutBeforeJob");
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		room.destroy();
	}
}
