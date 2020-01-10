package com.game.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.entity.Location;
import com.common.enumerate.CampType;
import com.common.enumerate.NodeType;
import com.common.template.NodeTemplate;
import com.game.entity.Room;

public class HomeNode extends BaseNode {
	private static final Logger logger = LoggerFactory.getLogger(HomeNode.class);
	public HomeNode(short nodeId, Room room, NodeTemplate template) {
		this.nodeId = nodeId;
		this.room = room;
		this.template = template;
		this.location = new Location(template.getX(), template.getY());
		this.selfCamp = CampType.getType(template.getCampType());
		setEnemyCamp();
		this.hp = template.getHp();
		logger.info(String.format("创建 nodeId=%d %s %s %s", nodeId, this.selfCamp.getName(), this.getNodeType().getName(), this.location));
	}
	
	@Override
	public NodeType getNodeType() {
		return NodeType.Home;
	}

	@Override
	public void dead(BaseNode attackNode) {
		logger.info(String.format("节点%d杀死 nodeId=%d 基地 ", attackNode.nodeId, this.nodeId));
		room.remove(this.nodeId);
		room.gameOver();
	}
}
