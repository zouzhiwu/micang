package com.game.job.fight;

import java.util.ArrayList;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.msg.Message;
import com.common.enumerate.CampType;
import com.common.enumerate.NodeType;
import com.common.enumerate.WayType;
import com.common.template.NodeTemplate;
import com.game.common.MessageCode;
import com.game.config.NodeConfig;
import com.game.entity.Room;
import com.game.helper.MsgHelper;
import com.game.node.BaseNode;
import com.game.node.SoldierNode;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ArsenalControllerJob implements Job {
	private final static Logger logger = LoggerFactory.getLogger(ArsenalControllerJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		// 第x波
		Integer index = (Integer)jobDataMap.get("index") + 1;
		logger.info(String.format("第%d波兵", index));
		// 所有的水晶建筑列表
		List<BaseNode> crystalList = room.getNodeList(NodeType.Crystal);
		// 蓝方上路水晶是否存在
		boolean isBlueTopExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.buleTopCrystalNodeTemplate.getId());
		// 蓝方中路水晶是否存在
		boolean isBlueMidExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.buleMidCrystalNodeTemplate.getId());
		// 蓝方下路水晶是否存在
		boolean isBlueDownExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.buleDownCrystalNodeTemplate.getId());
		// 蓝方所有水晶是否都不存在
		boolean isBlueAllNotExist = !isBlueTopExist && !isBlueMidExist && !isBlueDownExist;
		// 红方上路水晶是否存在
		boolean isRedTopExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.redTopCrystalNodeTemplate.getId());
		// 红方中路水晶是否存在
		boolean isRedMidExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.redMidCrystalNodeTemplate.getId());
		// 红方下路水晶是否存在
		boolean isRedDownExist = crystalList.stream().anyMatch(t ->t.template.getId() == NodeConfig.redDownCrystalNodeTemplate.getId());
		// 红方所有水晶是否都不存在
		boolean isRedAllNotExist = !isRedTopExist && !isRedMidExist && !isRedDownExist;
		List<List<NodeTemplate>> buleNodeTemplateList = new ArrayList<List<NodeTemplate>>();
		// 蓝方上中下三路分别出兵
		for (int i = 0; i < WayType.values().length; i++) {
			WayType wayType = WayType.values()[i];
			List<NodeTemplate> wayNoteTemplateList = new ArrayList<NodeTemplate>();
			if (wayType == WayType.Top) {				// 如果是上路，则判断红方上路水晶是否存在
				if (!isRedTopExist) {	// 如果红方上路水晶不存在，则添加蓝方1个超级兵
					wayNoteTemplateList.add(NodeConfig.buleSuperNodeTemplate);
				}
			} else if (wayType == WayType.Mid) {		// 如果是中路，则判断红方中路水晶是否存在
				if (!isRedMidExist) {	// 如果红方中路水晶不存在，则添加蓝方1个超级兵
					wayNoteTemplateList.add(NodeConfig.buleSuperNodeTemplate);
				}
			} else if (wayType == WayType.Down) {		// 如果是下路，则判断红方下路水晶是否存在
				if (!isRedDownExist) {	// 如果红方下路水晶不存在，则添加蓝方1个超级兵
					wayNoteTemplateList.add(NodeConfig.buleSuperNodeTemplate);
				}
			}
			// 如果红方上中下路水晶全部不存在，则再添加蓝方1个超级兵
			if (isRedAllNotExist) {
				wayNoteTemplateList.add(NodeConfig.buleSuperNodeTemplate);
			}
			wayNoteTemplateList.add(NodeConfig.buleSmallNodeTemplate);
			wayNoteTemplateList.add(NodeConfig.buleSmallNodeTemplate);
			wayNoteTemplateList.add(NodeConfig.buleSmallNodeTemplate);
			// 每隔3波，出1个炮兵
			if (index % 3 == 0) {
				wayNoteTemplateList.add(NodeConfig.buleBigNodeTemplate);
			}
			buleNodeTemplateList.add(wayNoteTemplateList);
		}
		List<List<NodeTemplate>> redNodeTemplateList = new ArrayList<List<NodeTemplate>>();
		// 红方上中下三路分别出兵
		for (int i = 0; i < WayType.values().length; i++) {
			WayType wayType = WayType.values()[i];
			List<NodeTemplate> wayNoteTemplateList = new ArrayList<NodeTemplate>();
			if (wayType == WayType.Top) {		// 如果是上路，则判断蓝方上路水晶是否存在
				if (!isBlueTopExist) {	// 如果蓝方上路水晶不存在，则添加红方1个超级兵
					wayNoteTemplateList.add(NodeConfig.redSuperNodeTemplate);
				}
			} else if (wayType == WayType.Mid) {		// 如果是中路，则判断蓝方上路水晶是否存在
				if (!isBlueMidExist) {	// 如果蓝方中路水晶不存在，则添加红方1个超级兵
					wayNoteTemplateList.add(NodeConfig.redSuperNodeTemplate);
				}
			} else if (wayType == WayType.Down) {		// 如果是下路，则判断蓝方上路水晶是否存在
				if (!isBlueDownExist) {	// 如果蓝方下路水晶不存在，则添加红方1个超级兵
					wayNoteTemplateList.add(NodeConfig.redSuperNodeTemplate);
				}
			}
			// 如果蓝方上中下路水晶全部不存在，则再添加红方1个超级兵
			if (isBlueAllNotExist) {
				wayNoteTemplateList.add(NodeConfig.redSuperNodeTemplate);
			}
			wayNoteTemplateList.add(NodeConfig.redSmallNodeTemplate);
			wayNoteTemplateList.add(NodeConfig.redSmallNodeTemplate);
			wayNoteTemplateList.add(NodeConfig.redSmallNodeTemplate);
			// 每隔3波，出1个炮兵
			if (index % 3 == 0) {
				wayNoteTemplateList.add(NodeConfig.redBigNodeTemplate);
			}
			redNodeTemplateList.add(wayNoteTemplateList);
		}
		
		List<SoldierNode> buleTopSoldierNodeList = createSoldierNodeList(room, buleNodeTemplateList.get(0), CampType.Blue, WayType.Top);
		List<SoldierNode> buleMidSoldierNodeList = createSoldierNodeList(room, buleNodeTemplateList.get(1), CampType.Blue, WayType.Mid);
		List<SoldierNode> buleDownSoldierNodeList = createSoldierNodeList(room, buleNodeTemplateList.get(2), CampType.Blue, WayType.Down);
		List<List<SoldierNode>> buleSoldierNodeList = new ArrayList<List<SoldierNode>>(3);
		buleSoldierNodeList.add(buleTopSoldierNodeList);
		buleSoldierNodeList.add(buleMidSoldierNodeList);
		buleSoldierNodeList.add(buleDownSoldierNodeList);
		
		List<SoldierNode> redTopSoldierNodeList = createSoldierNodeList(room, redNodeTemplateList.get(0), CampType.Red, WayType.Top);
		List<SoldierNode> redMidSoldierNodeList = createSoldierNodeList(room, redNodeTemplateList.get(1), CampType.Red, WayType.Mid);
		List<SoldierNode> redDownSoldierNodeList = createSoldierNodeList(room, redNodeTemplateList.get(2), CampType.Red, WayType.Down);
		List<List<SoldierNode>> redSoldierNodeList = new ArrayList<List<SoldierNode>>(3);
		redSoldierNodeList.add(redTopSoldierNodeList);
		redSoldierNodeList.add(redMidSoldierNodeList);
		redSoldierNodeList.add(redDownSoldierNodeList);
		
		room.startArsenal(buleTopSoldierNodeList, buleMidSoldierNodeList, buleDownSoldierNodeList
				, redTopSoldierNodeList, redMidSoldierNodeList, redDownSoldierNodeList);
		jobDataMap.put("index", index);
		
		Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_fight_soldier_create_notice);
		msg.putInt(room.getGameTime());
		msg.putShort(buleSoldierNodeList.size());
		for (int i = 0; i < buleSoldierNodeList.size(); i++) {
			List<SoldierNode> soldierNodeList = buleSoldierNodeList.get(i);
			msg.putShort(soldierNodeList.size());
			for (int j = 0; j < soldierNodeList.size(); j++) {
				SoldierNode soldierNode = soldierNodeList.get(j);
				msg.putShort(soldierNode.nodeId);
				msg.putInt(soldierNode.template.getId());
				msg.putByte((byte)soldierNode.wayType.getIndex());
			}
		}
		msg.putShort(redSoldierNodeList.size());
		for (int i = 0; i < redSoldierNodeList.size(); i++) {
			List<SoldierNode> nodeTemplatesList = redSoldierNodeList.get(i);
			msg.putShort(nodeTemplatesList.size());
			for (int j = 0; j < nodeTemplatesList.size(); j++) {
				SoldierNode soldierNode = nodeTemplatesList.get(j);
				msg.putShort(soldierNode.nodeId);
				msg.putInt(soldierNode.template.getId());
				msg.putByte((byte)soldierNode.wayType.getIndex());
			}
		}
		MsgHelper.sendBroadcast(msg, room);
	}
	
	private List<SoldierNode> createSoldierNodeList(Room room, List<NodeTemplate> nodeTemplatesList, CampType campType, WayType wayType) {
		List<SoldierNode> soldierNodeList = new ArrayList<SoldierNode>();
		for (int j = 0; j < nodeTemplatesList.size(); j++) {
			NodeTemplate nodeTemplate = nodeTemplatesList.get(j);
			SoldierNode soldierNode = new SoldierNode(room.getNewNodeId(), room, nodeTemplate, campType, wayType);
			soldierNodeList.add(soldierNode);
		}
		return soldierNodeList;
	}
}
