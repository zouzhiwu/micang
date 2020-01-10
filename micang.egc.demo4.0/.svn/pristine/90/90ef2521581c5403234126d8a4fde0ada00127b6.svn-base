package com.game.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.helper.TimeHelper;
import com.game.config.AppConfig;

public class JobScheduler {
	private final static Logger logger = LoggerFactory.getLogger(JobScheduler.class);
	public final static int interval = 50;
	private static SchedulerFactory schedulerFactory;
	public final static int max_count = 0;
	public static SchedulerFactory factory = new StdSchedulerFactory();
	
	public static void init() {
		String configPath = AppConfig.getConfigPath();
		String quartzConfigPath = String.format("%s/quartz.properties", configPath);
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(quartzConfigPath));
			schedulerFactory = new StdSchedulerFactory(properties);
		} catch (IOException e) {
			logger.error(e.toString());
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * 创建执行轮询的作业
	 * @param jobKey			作业主键
	 * @param delayTime			延迟时间，毫秒
	 * @param repeatInterval	间隔时间，毫秒
	 * @param dataMap		参数集合
	 * @param jobClass		作业类
	 */
	public static void createRepeatJob(JobKey jobKey, long delayTime, long repeatInterval
			, Map<String, Object> dataMap, Class <? extends Job> jobClass) {
		createRepeatJob(jobKey, delayTime, repeatInterval, 0, dataMap, jobClass);
	}
	
	/**
	 * 创建执行轮询的作业
	 * @param jobKey			作业主键
	 * @param delayTime			延迟时间，毫秒
	 * @param repeatInterval	间隔时间，毫秒
	 * @param repeatCount		轮询次数
	 * @param dataMap		参数集合
	 * @param jobClass		作业类
	 */
	public static void createRepeatJob(JobKey jobKey, long delayTime, long repeatInterval, int repeatCount
			, Map<String, Object> dataMap, Class <? extends Job> jobClass) {
		long firstTime = TimeHelper.getMilliTime() + delayTime;
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobKey.getName(), jobKey.getGroup())
					.build();
	        Trigger trigger = TriggerBuilder.newTrigger()
	        		.withIdentity(jobKey.getName(), jobKey.getGroup())
	                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(repeatInterval).withRepeatCount(repeatCount - 1))
	                .startAt(new Date(firstTime))
	                .build();
	        JobDataMap jobDataMap = jobDetail.getJobDataMap();
	        if (!dataMap.containsKey("index")) {
	        	dataMap.put("index", 0);
	        }
			jobDataMap.putAll(dataMap);
	        scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * 创建执行单次的作业
	 * @param jobKey		作业主键
	 * @param delayTime		延迟时间，毫秒
	 * @param dataMap		参数集合
	 * @param jobClass		作业类
	 */
	public static void createSingleJob(JobKey jobKey, long delayTime, Map<String, Object> dataMap, Class <? extends Job> jobClass) {
		long firstTime = TimeHelper.getMilliTime() + delayTime;
		long repeatInterval = delayTime;
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					.withIdentity(jobKey.getName(), jobKey.getGroup())
					.build();
	        Trigger trigger = TriggerBuilder.newTrigger()
	        		.withIdentity(jobKey.getName(), jobKey.getGroup())
	                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(repeatInterval).withRepeatCount(0))
	                .startAt(new Date(firstTime))
	                .build();
	        JobDataMap jobDataMap = jobDetail.getJobDataMap();
			jobDataMap.putAll(dataMap);
	        scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
	}
	
	public static JobDetail getJobDetail(JobKey jobKey) {
		JobDetail jobDetail = null;
		try {
			jobDetail = factory.getScheduler().getJobDetail(jobKey);
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return jobDetail;
	}
	
	public static Trigger getTrigger(TriggerKey triggerKey) {
		Trigger trigger = null;
		try {
			trigger = factory.getScheduler().getTrigger(triggerKey);
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return trigger;
	}
	
	public static Scheduler getScheduler() {
		Scheduler scheduler = null;
		try {
			scheduler = factory.getScheduler();
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return scheduler;
	}
	
	public static boolean isHasJob(JobKey jobKey) {
		boolean result = false;
		try {
			Scheduler scheduler = factory.getScheduler();
			result = scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	public static boolean stopJob(JobKey jobKey) {
		boolean result = false;
		try {
			Scheduler scheduler = factory.getScheduler();
			result = scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	public static boolean stopGroup(String groupName) {
		boolean result = false;
		try {
			Scheduler scheduler = factory.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.groupStartsWith(groupName);
			Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
			if (CollectionUtils.isNotEmpty(jobKeySet)) {
				List<JobKey> jobKeyList = new ArrayList<JobKey>(jobKeySet);
				result = scheduler.deleteJobs(jobKeyList);
			}
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	/**
	 * 跳过本轮询，并且以当前时间为起点继续轮询
	 */
	public static void refreshJob(JobKey jobKey) {
		try {
			Scheduler scheduler = factory.getScheduler();
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			if (jobDataMap.containsKey("index")) {
				int index = (int)jobDataMap.get("index");
				jobDataMap.put("index", ++index);
			}
			TriggerKey triggerKey = new TriggerKey(jobKey.getName(), jobKey.getGroup());
			Trigger oldTrigger = scheduler.getTrigger(triggerKey);
			TriggerBuilder<? extends Trigger> tb = oldTrigger.getTriggerBuilder();
			Trigger trigger = tb.startNow().build();
			scheduler.deleteJob(jobKey);
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
	}
	
	public static void triggerJob(JobKey jobKey) {
		try {
			Scheduler scheduler = factory.getScheduler();
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
	}
	
}
