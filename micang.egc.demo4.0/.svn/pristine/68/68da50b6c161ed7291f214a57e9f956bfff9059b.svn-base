package com.game.node;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.msg.Message;
import com.common.entity.Location;
import com.common.enumerate.TargetType;
import com.common.helper.TimeHelper;
import com.common.util.GameUtil;
import com.game.common.MessageCode;
import com.game.entity.Room;
import com.game.entity.Task;
import com.game.helper.MsgHelper;

/***
 * 具备攻击性质的抽象节点
 */
public abstract class AbsAttackNode extends BaseNode {
	private static final Logger logger = LoggerFactory.getLogger(AbsAttackNode.class);
	
    /**移动路径 */
	protected List<Location> way = new ArrayList<Location>();
	
	/**任务列表*/
	public Task task;
	
	/**射程 */
	public short aoe;
	
	/**攻击速度 */
	public short ats;
	
	/**物理伤害*/
	public int pa;
	
	/**魔法伤害*/
	public int ma;
	
	/** 最后攻击时间*/
	public long lastAttackTime;
	
	/**是否正在攻击*/
	public boolean isAttacking;
	
    public boolean startAttack() {
    	lastAttackTime = TimeHelper.getMilliTime();
//    	logger.info(String.format("nodeId=%d 开始攻击 target.nodeId=%d ", nodeId, target.node.nodeId));
    	if (hp > 0) {
    		boolean isDone = room.addAttackNode(this);
    		if (isDone) {
    			isAttacking = true;
    		}
    		return isDone;
    	} else {
    		return false;
    	}
    }
    
    public boolean stopAttack() {
    	boolean isDone = room.removeAttackNode(this);
    	if (isDone) {
			isAttacking = false;
		}
    	return isDone;
    }
    
    public abstract void changeTask(Task newTask);
    
    public void onAttack(int pa, int ma) {
    	Task currTask = this.task;
    	if (currTask != null) {
    		if (!currTask.isDone) {
        		// 如果目标是节点，则计算攻击伤害
                if (currTask.target.type == TargetType.Node) {
            		synchronized(currTask.target.node) {
                    	// 如果当前位置和目标位置小于射程，则扣对方血量，否则停止攻击
                		if (GameUtil.distance(this.location, currTask.target.node.location) <= aoe) {
                			long currTime = TimeHelper.getMilliTime();
                			// 如果当前时间大于最后攻击时间+攻击周期，则允许攻击
                        	if (currTime >= lastAttackTime + ats) {
                        		lastAttackTime = currTime;
                        		// 是否命中
                        		boolean isHit = false;
                        		if (currTask.target.node.hp > 0) {
                        			// 扣血，并处理小于0的情况
                        			currTask.target.node.hp -= pa;
                        			isHit = true;
                        			logger.info(String.format("nodeId=%d 攻击 nodeId=%d hp=%d", nodeId, currTask.target.node.nodeId, currTask.target.node.hp));
                        			// 如果对方血量为0，则结束攻击
                        			if (currTask.target.node.hp < 0) {
                        				currTask.target.node.hp = 0;
                        			}
                        			sendHpMessage(room, this, currTask.target.node);
                        		}
                    			// 如果对方血量为0，则结束攻击
                    			if (currTask.target.node.hp <= 0) {
                    				// 停止攻击
                    				stopAttack();
                    				// 完成任务
                					currTask.isDone = true;
                    				// 如果命中目标，则死亡处理
                    				if (isHit) {
                    					// 阵亡处理
                    					currTask.target.node.dead(this);
                    				}
                    				// 如果当前节点是父类是可移动类BaseMoveNode，则把对方基地作为目标寻路
//                    				if (AbsMoveNode.class.isAssignableFrom(this.getClass())) {
//                    					HomeNode enemyHomeNode = room.getHomeNode(enemyCamp);
//                    					if (enemyHomeNode != null) {
//                    						if (enemyHomeNode.hp > 0) {
//                    							changeTask(new Target(enemyHomeNode));
//                    						}
//                    					}
//                    				}
                    			}
                        	}
                    	}
                	}
                } else {
                	logger.error(String.format("nodeId=%d 攻击攻击目标错误", this.nodeId));
                }
        	}
    	}
    	
    }
    
    private void sendHpMessage(Room room, BaseNode asNode, BaseNode beNode) {
    	Message msg = new Message();
		msg.setMsgcd(MessageCode.msg_fight_hp_notice);
		msg.putInt(room.getGameTime());
		msg.putShort(asNode.nodeId);
		msg.putShort((short)asNode.location.x);
		msg.putShort((short)asNode.location.y);
		msg.putInt(asNode.hp);
		msg.putShort(beNode.nodeId);
		msg.putShort((short)beNode.location.x);
		msg.putShort((short)beNode.location.y);
		msg.putInt(beNode.hp);
		MsgHelper.sendBroadcast(msg, room);
    }
    
//	private void printVisionList() {
//		if (visionList.size() == 0) {
//			logger.info("nodeId=" + nodeId + " visionList.size=" + visionList.size());
//		} else {
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < visionList.size(); i++) {
//				BaseNode node = visionList.get(i);
//				sb.append(String.format(",%s", node.nodeId));
//			}
//			sb.deleteCharAt(0);
//			logger.info("nodeId=" + nodeId + " visionList:" + sb.toString());
//		}
//	}
}
