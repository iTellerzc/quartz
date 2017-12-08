package com.hongao.quartz.service;

import com.hongao.parent.exception.HaBizException;
import com.hongao.quartz.dto.RegQuartzTaskReq;

/**
 * 定时任务服务
 * @author iTeller_zc
 *
 */
public interface HaQuartzService {
	
	/**
	 * 注册定时任务
	 * @param regQuartzTaskReq
	 * @throws HaBizException
	 */
	void register(RegQuartzTaskReq regQuartzTaskReq) throws HaBizException;
	
	/**
	 * 删除定时任务
	 * @param jobGroup
	 * @param bizId
	 */
	void deleteQuartzJob(String jobGroup, Long bizId) throws HaBizException;
}
