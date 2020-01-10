package com.game.node;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.entity.Location;
import com.common.enumerate.MapNodeType;
import com.common.enumerate.NodeType;
import com.common.helper.TimeHelper;
import com.common.util.GameUtil;
import com.game.entity.Task;
import com.game.service.MapService;

/**
 * 具备移动性质的抽象节点
 */
public abstract class AbsMoveNode extends AbsUpdateVisionNode {
	private static final Logger logger = LoggerFactory.getLogger(AbsMoveNode.class);
	
	/**移动速度 */
	public short mvs;
	
	/**最后移动的时间戳，单位：毫秒 */
	private long lastMoveTime;
	
	/**是否正在移动*/
	public boolean isMoving;
	
	public void startMove() {
		if (hp > 0) {
			Task currTask = this.task;
			if (currTask != null) {
				isMoving = true;
				way = autoSearchWay(currTask.target.location);
				if (room.addMoveNode(this)) {
					// 通知客户端开始移动
//					Message msg = new Message();
//					msg.setMsgcd(MessageCode.msg_fight_move_start);
//					msg.putInt(room.getGameTime());
//					msg.putShort(nodeId);
//					msg.putShort((short)location.x);
//					msg.putShort((short)location.y);
//					msg.putShort((short)currTask.target.location.x);
//					msg.putShort((short)currTask.target.location.y);
//					MsgHelper.sendBroadcast(msg, room);
					logger.info(String.format("nodeId=%d 开始移动 location=%s target.location=%s", nodeId, location, currTask.target.location));
					lastMoveTime = TimeHelper.getMilliTime();
				}
			}
		}
		
	}

	public boolean stopMove() {
		if (room.removeMoveNode(this)) {
			isMoving = false;
			// 通知客户端停止移动
//			Message msg = new Message();
//			msg.setMsgcd(MessageCode.msg_fight_move_stop);
//			msg.putInt(room.getGameTime());
//			msg.putShort(nodeId);
//			msg.putShort((short)location.x);
//			msg.putShort((short)location.y);
//			MsgHelper.sendBroadcast(msg, room);
			logger.info(String.format("nodeId=%d 停止移动 location=%s", nodeId, location));
			return true;
		} else {
			return false;
		}
	}
	
    public void onMove() {
        if (way.size() > 0) {
            Location subTargetLocation = way.get(0);
            if (subTargetLocation != null) {
            	long currTime = TimeHelper.getMilliTime();
            	long timeLength = currTime - lastMoveTime;
            	double d1 = Math.sqrt(Math.pow(subTargetLocation.x - this.location.x, 2) + Math.pow(subTargetLocation.y - this.location.y, 2));
            	double angle = Math.atan2(subTargetLocation.y - this.location.y, subTargetLocation.x - this.location.x);
            	double addx = Math.cos(angle) * mvs * timeLength / 1000.0;
            	double addy = Math.sin(angle) * mvs * timeLength / 1000.0;
            	double d2 = Math.sqrt(Math.pow(addx, 2) + Math.pow(addy, 2));
            	if (d2 >= d1) {
            		way.remove(subTargetLocation);
                    this.location.x = subTargetLocation.x;
                    this.location.y = subTargetLocation.y;
            	} else {
            		this.location.x = GameUtil.round(this.location.x + addx, 2);
                    this.location.y = GameUtil.round(this.location.y + addy, 2);
            	}
            	lastMoveTime = currTime;
            	logger.info(String.format("nodeId=%d location=%s", nodeId, this.location));
            }
        } else {
        	stopMove();
        	this.task.isDone = true;
        }
    }
    
	private List<Location> autoSearchWay(Location targetLocation) {
    	float distance = GameUtil.distance(targetLocation, this.location);
		logger.info(String.format("nodeId=%d 自动寻路 location=%s targetLocation=%s distance=%s aoe=%s", this.nodeId, this.location, targetLocation, distance, aoe));
		List<Location> locationList = null;
    	// 如果当前位置与目标位置距离超过射程，则寻路
    	if (distance > 0) {
    		NodeType nodeType = getNodeType();
    		switch (nodeType) {
			case Hero:
				locationList = MapService.searchWay(this.location, targetLocation, MapNodeType.White);
				break;
			case Soldier:
				SoldierNode soldierNode = (SoldierNode)this;
    			locationList = GameUtil.getWayList(this.location, selfCamp, soldierNode.wayType);
				break;
			default:
				break;
			}
    	}
    	return locationList;
    }
	
//	private void printWayList(List<Location> way) {
//		if (way.size() == 0) {
//			logger.info("nodeId=" + nodeId + " way.size=" + way.size());
//		} else {
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < way.size(); i++) {
//				Location location = way.get(i);
//				sb.append(String.format(",%s", location.toString()));
//			}
//			sb.deleteCharAt(0);
//			logger.info("nodeId=" + nodeId + " wayList:" + sb.toString());
//		}
//	}
	
}
