package com.game.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.entity.Location;
import com.common.enumerate.CampType;
import com.common.enumerate.NodeType;
import com.common.enumerate.WayType;
import com.common.template.NodeTemplate;
import com.game.entity.Room;
import com.game.entity.Task;
import com.game.util.ParameterUtil;

public class TowerNode extends AbsUpdateVisionNode {
	private static final Logger logger = LoggerFactory.getLogger(TowerNode.class);
	public TowerNode(short nodeId, Room room, NodeTemplate template) {
		this.nodeId = nodeId;
		this.room = room;
		this.template = template;
		this.location = new Location(template.getX(), template.getY());
		this.selfCamp = CampType.getType(template.getCampType());
		setEnemyCamp();
		this.wayType = WayType.getType(template.getWayType());
		this.hp = template.getHp();
		this.pa = template.getPa();
		this.ma = template.getMa();
		logger.info(String.format("创建 nodeId=%d %s %s %s", nodeId, this.selfCamp.getName(), this.getNodeType().getName(), location));
	}
	
	public WayType wayType;
	
	@Override
	public NodeType getNodeType() {
		return NodeType.Tower;
	}

	@Override
	public void dead(BaseNode attackNode) {
		logger.info(String.format("nodeId=%d 阵亡", this.nodeId));
		room.remove(this.nodeId);
		stopAttack();
		stopUpdateVision();
		refreshAllNodeVision();
		// 计算击杀野怪，小兵，建筑物的经验值收益
		if (attackNode.getNodeType() == NodeType.Hero) {
			int addExp = template.getExp();
			if (template.getGrowthInterval() != 0 && template.getGrowthExp() != 0) {
				int growthInterval = ParameterUtil.gameTime2realTime(template.getGrowthInterval() * 1000);
				int growthCount = room.getGameTime() / growthInterval;
				addExp += Math.round(growthCount * template.getGrowthExp() * 10);
			}
			HeroNode heroNode = (HeroNode)attackNode;
			heroNode.exp += addExp;
		}
	}

	@Override
	public void changeTask(Task newTask) {
		this.task = newTask;
		switch (this.task.type) {
		case Wait:
			break;
		case Attack:
			startAttack();
			break;
		default:
			break;
		}
	}

	@Override
	public void onAiLogic() {
		// TODO Auto-generated method stub
		
	}

}
