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

import com.common.entity.OperateStep;
import com.common.enumerate.OperateType;
import com.game.entity.Room;
import com.game.factory.Context;
import com.game.service.RoomService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class AutoOperationJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(AutoOperationJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("执行自动操作Job");
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		OperateStep operateStep = room.operateStepList.get(room.stepIndex);
		// 如果玩家没有选择(禁用)，则自动选择(禁用)
		if (!operateStep.isOperated()) {
			RoomService roomService = Context.getBean(RoomService.class);
			Long accountId = operateStep.getAccountId();
			// 如果是禁用操作，则自动执行禁用英雄，否则自动执行选择英雄
			if (operateStep.getOperateType() == OperateType.Ban) {
				roomService.autoBanHero(room.id, accountId);
			} else {
				roomService.autoChooseHero(room.id, accountId);
			}
		}
	}
}
