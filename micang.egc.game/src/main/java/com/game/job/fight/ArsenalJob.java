package com.game.job.fight;

import java.util.ArrayList;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.enumerate.ArmsType;
import com.common.enumerate.CampType;
import com.game.entity.Room;
import com.game.job.JobScheduler;
import com.game.node.SoldierNode;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ArsenalJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(ArsenalJob.class);
	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		Integer index = (Integer)jobDataMap.get("index");
		List<SoldierNode> buleTopSoldierNodeList = (List<SoldierNode>)jobDataMap.get("buleTopSoldierNodeList");
		List<SoldierNode> buleMidSoldierNodeList = (List<SoldierNode>)jobDataMap.get("buleMidSoldierNodeList");
		List<SoldierNode> buleDownSoldierNodeList = (List<SoldierNode>)jobDataMap.get("buleDownSoldierNodeList");
		
		List<SoldierNode> soldierNodeList = new ArrayList<SoldierNode>();
		if (!buleTopSoldierNodeList.isEmpty()) {
			soldierNodeList.add(buleTopSoldierNodeList.remove(0));
		}
		if (!buleMidSoldierNodeList.isEmpty()) {
			soldierNodeList.add(buleMidSoldierNodeList.remove(0));
		}
		if (!buleDownSoldierNodeList.isEmpty()) {
			soldierNodeList.add(buleDownSoldierNodeList.remove(0));
		}
		List<SoldierNode> redTopSoldierNodeQueue = (List<SoldierNode>)jobDataMap.get("redTopSoldierNodeList");
		List<SoldierNode> redMidSoldierNodeQueue = (List<SoldierNode>)jobDataMap.get("redMidSoldierNodeList");
		List<SoldierNode> redDownSoldierNodeQueue = (List<SoldierNode>)jobDataMap.get("redDownSoldierNodeList");
		if (!redTopSoldierNodeQueue.isEmpty()) {
			soldierNodeList.add(redTopSoldierNodeQueue.remove(0));
		}
		if (!redMidSoldierNodeQueue.isEmpty()) {
			soldierNodeList.add(redMidSoldierNodeQueue.remove(0));
		}
		if (!redDownSoldierNodeQueue.isEmpty()) {
			soldierNodeList.add(redDownSoldierNodeQueue.remove(0));
		}
		for (int i = 0; i < soldierNodeList.size(); i++) {
			SoldierNode soldierNode = soldierNodeList.get(i);
			// 启动移动Job
			soldierNode.startMove();
			// 启动视野Job
			soldierNode.startUpdateVision();
			room.putNode(soldierNode);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < soldierNodeList.size(); i++) {
			SoldierNode soldierNode = soldierNodeList.get(i);
			if (soldierNode.selfCamp == CampType.Blue) {
				ArmsType armsType = ArmsType.getType(soldierNode.template.getArmsType());
				if (ArmsType.Small == armsType) {
					sb.append(",蓝方小兵" + soldierNode.nodeId);
				} else if (ArmsType.Big == armsType) {
					sb.append(",蓝方炮兵" + soldierNode.nodeId);
				} else if (ArmsType.Super == armsType) {
					sb.append(",蓝方超级兵" + soldierNode.nodeId);
				}
			} else if (soldierNode.selfCamp == CampType.Red) {
				ArmsType armsType = ArmsType.getType(soldierNode.template.getArmsType());
				if (ArmsType.Small == armsType) {
					sb.append(",红方小兵" + soldierNode.nodeId);
				} else if (ArmsType.Big == armsType) {
					sb.append(",红方炮兵" + soldierNode.nodeId);
				} else if (ArmsType.Super == armsType) {
					sb.append(",红方超级兵" + soldierNode.nodeId);
				}
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
			logger.info("出兵：" + sb.toString());
		}
		jobDataMap.put("index", ++index);
		if (buleTopSoldierNodeList.isEmpty() && buleMidSoldierNodeList.isEmpty() && buleDownSoldierNodeList.isEmpty()) {
			if (redTopSoldierNodeQueue.isEmpty() && redMidSoldierNodeQueue.isEmpty() && redDownSoldierNodeQueue.isEmpty()) {
				JobKey jobKey = jobDetail.getKey();
				JobScheduler.stopJob(jobKey);
			}
		}
	}
}
