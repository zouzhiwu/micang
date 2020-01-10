package com.game.job.fight;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.common.template.NodeTemplate;
import com.game.entity.Room;
import com.game.factory.Context;
import com.game.service.FightService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CreateMonstersJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Room room = (Room)jobDataMap.get("room");
		@SuppressWarnings("unchecked")
		List<NodeTemplate> monsterTemplateList = (List<NodeTemplate>)jobDataMap.get("monsterTemplateList");
		FightService fightService = Context.getBean(FightService.class);
		for (NodeTemplate monsterTemplate : monsterTemplateList) {
			fightService.generateMonster(room, monsterTemplate);
		}
	}
}
