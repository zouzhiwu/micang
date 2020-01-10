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
import com.game.factory.Context;
import com.game.service.RoomService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ShowLayoutJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(ShowLayoutJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("ShowLayoutBeforeJob");
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		RoomService roomService = Context.getBean(RoomService.class);
		// 阵容展示
		roomService.showLayout(room);
	}
}
