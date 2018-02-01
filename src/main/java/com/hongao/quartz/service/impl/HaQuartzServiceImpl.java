package com.hongao.quartz.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.hongao.parent.exception.HaBizException;
import com.hongao.quartz.consts.ErrorCodes;
import com.hongao.quartz.dao.QuartzJobMapper;
import com.hongao.quartz.dto.RegQuartzTaskReq;
import com.hongao.quartz.model.QuartzJob;
import com.hongao.quartz.service.HaQuartzService;
import com.hongao.quartz.utils.ScheduleUtils;

/**
 * 定时服务实现
 * @author iTeller_zc
 *
 */
@Service
public class HaQuartzServiceImpl implements HaQuartzService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    /** 
     * 调度工厂Bean
     */
    @Autowired
    private Scheduler scheduler;
    
	@Autowired
	private QuartzJobMapper quartzJobMapper;
	
	@Override
	public void register(RegQuartzTaskReq regQuartzTaskReq) throws HaBizException {
		logger.info("reg quartz job req is:{} .", JSONObject.toJSONString(regQuartzTaskReq));
		QuartzJob scheduleJob = new QuartzJob();
		try {
			BeanUtils.copyProperties(scheduleJob, regQuartzTaskReq);
		} catch (Exception e) {
			logger.error("bean copy meet error!");
			throw new HaBizException(ErrorCodes.SYS_ERROR, "sys error!");
		} 
		
		QuartzJob dbQuartzJob = quartzJobMapper.existed(regQuartzTaskReq.getJobGroup(), regQuartzTaskReq.getBizId());
		if(dbQuartzJob != null){
			logger.warn("jobName:{}, jobGoup:{} has existed.", regQuartzTaskReq.getJobName(), regQuartzTaskReq.getJobGroup());
			ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			scheduleJob.setId(dbQuartzJob.getId());
			quartzJobMapper.updateQuartzJob(scheduleJob);
		}else{
			logger.warn("jobName:{}, jobGoup:{} not existed, register it.", regQuartzTaskReq.getJobName(), regQuartzTaskReq.getJobGroup());
			ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
			quartzJobMapper.addQuartzJob(scheduleJob);
		}
	}

	@Override
	public void deleteQuartzJob(String jobGroup, Long bizId) throws HaBizException {
		QuartzJob quartzJob = quartzJobMapper.existed(jobGroup, bizId);
		if(quartzJob == null){
			logger.error("not existed quartz job,jobGroup={}, bizId={}.", jobGroup, bizId);
			//throw new HaBizException(ErrorCodes.NOT_EXISTED_JOB, "not existed job");
			return;
		}
		ScheduleUtils.deleteScheduleJob(scheduler, quartzJob.getJobName(), jobGroup);
		quartzJobMapper.invalidQuartzJob(quartzJob.getId());
	}
}
