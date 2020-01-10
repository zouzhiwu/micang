package com.game.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.JobKey;

import com.common.constant.RoomConstant;
import com.game.entity.Room;
import com.game.helper.UuidHelper;
import com.game.job.room.AutoOperationJob;
import com.game.job.room.ChooseMemberJob;
import com.game.job.room.DestroyRoomJob;
import com.game.job.room.ShowFightPowerJob;
import com.game.job.room.ShowLayoutJob;

public class RoomJobManager {
//	private final static Logger logger = LoggerFactory.getLogger(RoomJobManager.class);
	
	/**
	 *  创建展示战斗力PK Job
	 */
	public static void createShowFightPowerJob(Room room) {
		String jobName = UuidHelper.getUuid();
		String groupName = room.groupName;
		JobKey jobKey = new JobKey(jobName, groupName);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("room", room);
		long delayTime = 5 * 1000;
		JobScheduler.createSingleJob(jobKey, delayTime, dataMap, ShowFightPowerJob.class);
	}
	
	/**
	 *  创建选择默认上阵队员Job
	 */
	public static void createChooseMemberJob(Room room) {
		String jobName = UuidHelper.getUuid();
		String groupName = room.groupName;
		JobKey jobKey = new JobKey(jobName, groupName);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		room.chooseMemberJobKey = jobKey;
		dataMap.put("room", room);
		long delayTime = RoomConstant.auto_choose_member_cd_time * 1000;
		JobScheduler.createSingleJob(jobKey, delayTime, dataMap, ChooseMemberJob.class);
	}
	
	/**
	 *  创建操作通知Job
	 */
	public static void createAutoOperationJob(Room room) {
		String jobName = UuidHelper.getUuid();
		String groupName = room.groupName;
		JobKey jobKey = new JobKey(jobName, groupName);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("room", room);
		room.autoOperationJobKey = jobKey;
		long delayTime = RoomConstant.auto_operation_cd_time * 1000;
		JobScheduler.createSingleJob(jobKey, delayTime, dataMap, AutoOperationJob.class);
	}
	
	/**
	 *  创建阵容展示Job
	 */
	public static void createShowLayoutJob(Room room, long delayTime) {
		String jobName = UuidHelper.getUuid();
		String groupName = room.groupName;
		JobKey jobKey = new JobKey(jobName, groupName);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("room", room);
		JobScheduler.createSingleJob(jobKey, delayTime, dataMap, ShowLayoutJob.class);
	}
	
	/**
	 *  创建回收房间Job
	 */
	public static void createDestroyRoomJob(Room room, long delayTime) {
		String jobName = UuidHelper.getUuid();
		String groupName = room.groupName;
		JobKey jobKey = new JobKey(jobName, groupName);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("room", room);
		JobScheduler.createSingleJob(jobKey, delayTime, dataMap, DestroyRoomJob.class);
	}
}
