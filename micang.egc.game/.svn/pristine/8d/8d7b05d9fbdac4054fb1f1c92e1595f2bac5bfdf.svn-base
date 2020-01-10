package com.game.entity;

import org.apache.commons.lang3.StringUtils;

import com.common.enumerate.TargetType;
import com.common.enumerate.TaskType;

public class Task {
	// 目标
	public Target target;
	// 任务类型
	public TaskType type;
	// 后置任务
	public Task nextTask;
	// 是否完成
	public boolean isDone = false;
	
	public Task(Target target, TaskType type) {
		if (type == TaskType.Wait) {
			this.type = type;
			this.target = target;
		} if (type == TaskType.Free) {
			this.type = type;
		} else {
			if (target.type == TargetType.Location) {
				this.type = TaskType.Move;
			} else if (target.type == TargetType.Node) {
				this.type = TaskType.Attack;
			} else if (target.type == TargetType.Template) {
				this.type = type;
			}
			this.target = target;
		}
	}
	
	@Override
	public String toString() {
		String result = StringUtils.EMPTY;
		if (type == TaskType.Wait) {
			result = String.format("taskType=%s target.nodeTemplate.id=%d", type.getName(), target.nodeTemplate.getId());
		} if (type == TaskType.Free) {
			result = String.format("taskType=%s", type.getName());
		} else {
			if (target.type == TargetType.Location) {
				result = String.format("taskType=%s target.location=%s", type.getName(), target.location);
			} else if (target.type == TargetType.Node) {
				result = String.format("taskType=%s target.node.nodeId=%d", type.getName(), target.node.nodeId);
			} else if (target.type == TargetType.Template) {
				result = String.format("taskType=%s target.nodeTemplate.id=%d", type.getName(), target.nodeTemplate.getId());
			}
		}
		return result;
	}
}
