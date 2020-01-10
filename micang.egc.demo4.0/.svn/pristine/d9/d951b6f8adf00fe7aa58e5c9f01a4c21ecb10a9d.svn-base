package com.game.job.fight;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.cb.msg.Message;
import com.game.common.MessageCode;
import com.game.entity.Room;
import com.game.helper.MsgHelper;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SyncFrameJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_fight_sync_frame);
		msg.putInt(room.getFrameCount());
		MsgHelper.sendBroadcast(msg, room);
	}
}
