package com.game.node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.common.entity.Location;
import com.common.util.GameUtil;

/**
 * 具备视野性质的抽象节点
 */
public abstract class AbsUpdateVisionNode extends AbsAttackNode {
//	private static final Logger logger = LoggerFactory.getLogger(AbsAttackNode.class);
    
	/**视野内的节点 */
    public List<BaseNode> visionList = new ArrayList<BaseNode>();
	
	/**视野半径 */
	public short vr;
	
	public abstract void onAiLogic();
    
	public boolean startUpdateVision() {
		if (hp > 0) {
			return room.addUpdateVisionNode(this);
		} else {
			return false;
		}
	}
	
	public boolean stopUpdateVision() {
		return room.removeUpdateVisionNode(this);
	}
	
    public void onUpdateVision() {
    	List<BaseNode> nodeList = room.getAllList();
//    	logger.info(String.format("nodeId=%d enemyCamp=%s", nodeId, enemyCamp.getName()));
//    	printNodeList(nodeList);
    	for (BaseNode node : nodeList) {
    		if (node.nodeId != this.nodeId) {
    			double distance = GameUtil.distance(node.location, this.location);
//        		logger.info(String.format("this.nodeId=%d node.nodeId=%d distance=%s vr=%d", nodeId, node.nodeId, distance, vr));
        		if (visionList.contains(node)) {
        			if (distance > vr) {
            			visionList.remove(node);
            		}
        		} else {
        			if (distance <= vr) {
            			visionList.add(node);
            		}
        		}
    		}
    	}
    }
    
    /**
     * 在视野中查找最近距离的非己方节点
	* @return
	*/
	protected BaseNode getMinDistance() {
		List<BaseNode> enemyList = new ArrayList<BaseNode>();
		visionList.forEach(node -> {
			if (node.selfCamp != selfCamp) {
				enemyList.add(node);
			}
		});
		if (enemyList.size() == 1) {
			BaseNode node = enemyList.get(0);
			return node;
		} else if (enemyList.size() > 1) {
			final Location myLocation = this.location;
			// 距离排序
			Collections.sort(enemyList, new Comparator<BaseNode>() {
			    @Override
			    public int compare(BaseNode node1, BaseNode node2) {
			    	return GameUtil.distance(node1.location, myLocation) > GameUtil.distance(node2.location, myLocation) ? 1 : -1;
			    }
			});
			// 选择最近距离的节点攻击
			BaseNode node = enemyList.get(0);
			return node;
		} else {
			return null;
		}
	}
	
	protected BaseNode getTargetNodeTemplateInVision() {
		for (BaseNode node : visionList) {
			if (node.template == task.target.nodeTemplate) {
				return node;
			}
		}
		return null;
	}
    
//	private void printNodeList(List<BaseNode> nodeList) {
//		if (nodeList.size() == 0) {
//			logger.info("nodeId=" + nodeId + " nodeList.size=" + nodeList.size());
//		} else {
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < nodeList.size(); i++) {
//				BaseNode node = nodeList.get(i);
//				sb.append(String.format(",%s%s", node.nodeId, node.location));
//			}
//			sb.deleteCharAt(0);
//			logger.info("nodeId=" + nodeId + " nodeList:" + sb.toString());
//		}
//	}
}
