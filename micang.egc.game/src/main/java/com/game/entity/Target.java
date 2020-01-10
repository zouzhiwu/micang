package com.game.entity;

import com.common.entity.Location;
import com.common.enumerate.TargetType;
import com.common.template.NodeTemplate;
import com.game.node.BaseNode;

public class Target {
	public Target(BaseNode node) {
        this.type = TargetType.Node;
        this.node = node;
        this.location = node.location;
    }
	
	public Target(NodeTemplate nodeTemplate) {
        this.type = TargetType.Template;
        this.nodeTemplate = nodeTemplate;
    }
	
	public Target(Location location) {
        this.type = TargetType.Location;
        this.location = location;
    }

    /**目标类型**/
    public TargetType type;
    
    /**目标节点**/
    public BaseNode node;
    
    /**目标节点模板**/
    public NodeTemplate nodeTemplate;
    
    /**目标位置，PNG地图坐标**/
    public Location location;
}
