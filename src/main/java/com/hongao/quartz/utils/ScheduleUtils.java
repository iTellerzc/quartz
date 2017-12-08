package com.hongao.quartz.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hongao.parent.exception.HaBizException;
import com.hongao.quartz.consts.ErrorCodes;
import com.hongao.quartz.consts.QuartzJobConsts;
import com.hongao.quartz.factory.SyncJobFactory;
import com.hongao.quartz.factory.AsyncJobFactory;
import com.hongao.quartz.model.QuartzJob;


/**
 * 定时任务工具类
 * 
 * @author iTeller_zc
 *
 */
public class ScheduleUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleUtils.class);
	
	 /**
     * 获取触发器key
     * 
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        return TriggerKey.triggerKey(jobName, jobGroup);
    }

    /**
     * 获取表达式触发器
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @return cron trigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobName, String jobGroup) throws HaBizException{
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            return (CronTrigger) scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            LOG.error("获取定时任务CronTrigger出现异常", e);
            throw new HaBizException(ErrorCodes.TRIGGER_ERROR, "获取定时任务CronTrigger出现异常");
        }
    }

    /**
     * 创建任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     * @throws HaBizException 
     */
    public static void createScheduleJob(Scheduler scheduler, QuartzJob scheduleJob) throws HaBizException {
        createScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),
            scheduleJob.getCronExpression(), scheduleJob.getIsSync(), scheduleJob);
    }

    /**
     * 创建定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     * @throws HaBizException 
     */
    public static void createScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, boolean isSync, Object param) throws HaBizException {
        //同步或异步
        Class<? extends Job> jobClass = isSync ? AsyncJobFactory.class : SyncJobFactory.class;

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();

        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();

        String jobTrigger = trigger.getKey().getName();

        QuartzJob scheduleJob = (QuartzJob)param;
        scheduleJob.setJobTrigger(jobTrigger);

        //放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(QuartzJobConsts.JOB_PARAM_KEY, scheduleJob);
        jobDetail.getJobDataMap().put(QuartzJobConsts.BIZ_ID, scheduleJob.getBizId());

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error("创建定时任务失败", e);
            throw new HaBizException(ErrorCodes.CREATE_TASK_ERROR, "创建定时任务失败");
        }
    }

    /**
     * 运行一次任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @throws HaBizException 
     */
    public static void runOnce(Scheduler scheduler, String jobName, String jobGroup) throws HaBizException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("运行一次定时任务失败", e);
            throw new HaBizException(ErrorCodes.RUN_TASK_ERROR, "运行一次定时任务失败");
        }
    }

    /**
     * 暂停任务
     * 
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @throws HaBizException 
     */
    public static void pauseJob(Scheduler scheduler, String jobName, String jobGroup) throws HaBizException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("暂停定时任务失败", e);
            throw new HaBizException(ErrorCodes.PAUSE_TASK_ERROR, "暂停定时任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @throws HaBizException 
     */
    public static void resumeJob(Scheduler scheduler, String jobName, String jobGroup) throws HaBizException {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            LOG.error("恢复定时任务失败", e);
            throw new HaBizException(ErrorCodes.RESUME_TASK_ERROR, "恢复定时任务失败");
        }
    }

    /**
     * 获取jobKey
     *
     * @param jobName the job name
     * @param jobGroup the job group
     * @return the job key
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }

    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param scheduleJob the schedule job
     * @throws HaBizException 
     */
    public static void updateScheduleJob(Scheduler scheduler, QuartzJob scheduleJob) throws HaBizException {
        updateScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup(),
            scheduleJob.getCronExpression(), scheduleJob.getIsSync(), scheduleJob);
    }

    /**
     * 更新定时任务
     *
     * @param scheduler the scheduler
     * @param jobName the job name
     * @param jobGroup the job group
     * @param cronExpression the cron expression
     * @param isSync the is sync
     * @param param the param
     * @throws HaBizException 
     */
    public static void updateScheduleJob(Scheduler scheduler, String jobName, String jobGroup,
                                         String cronExpression, boolean isSync, Object param) throws HaBizException {
        //同步或异步
//        Class<? extends Job> jobClass = isSync ? AsyncJobFactory.class : SyncJobFactory.class;

        try {
//            JobDetail jobDetail = scheduler.getJobDetail(getJobKey(jobName, jobGroup));

//            jobDetail = jobDetail.getJobBuilder().ofType(jobClass).build();

            //更新参数 实际测试中发现无法更新
//            JobDataMap jobDataMap = jobDetail.getJobDataMap();
//            jobDataMap.put(ScheduleJobVo.JOB_PARAM_KEY, param);
//            jobDetail.getJobBuilder().usingJobData(jobDataMap);

            TriggerKey triggerKey = ScheduleUtils.getTriggerKey(jobName, jobGroup);

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            // 忽略状态为PAUSED的任务，解决集群环境中在其他机器设置定时任务为PAUSED状态后，集群环境启动另一台主机时定时任务全被唤醒的bug
            if(!triggerState.name().equalsIgnoreCase("PAUSED")){
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            LOG.error("更新定时任务失败", e);
            throw new HaBizException(ErrorCodes.UPDATE_TASK_ERROR, "更新定时任务失败");
        }
    }

    /**
     * 删除定时任务
     *
     * @param scheduler
     * @param jobName
     * @param jobGroup
     * @throws HaBizException 
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobName, String jobGroup) throws HaBizException {
        try {
            scheduler.deleteJob(getJobKey(jobName, jobGroup));
        } catch (SchedulerException e) {
            LOG.error("删除定时任务失败", e);
            throw new HaBizException(ErrorCodes.DELETE_TASK_ERROR, "删除定时任务失败");
        }
    }
    
    /**
     * 定时任务是否已存在
     * @param jobGroup
     * @param jobName
     * @return
     */
    public static boolean existedJob(Scheduler scheduler, String jobGroup, String jobName) throws HaBizException{
    	JobKey jobKey = getJobKey(jobName, jobGroup);
    	try {
			return scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			LOG.error("不存在该定时任务", e);
			throw new HaBizException(ErrorCodes.NOT_EXISTED_JOB, "删除定时任务失败");
		}
    }
}
