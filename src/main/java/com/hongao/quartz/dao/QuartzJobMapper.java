package com.hongao.quartz.dao;

import org.apache.ibatis.annotations.Param;

import com.hongao.quartz.model.QuartzJob;

/**
 * 定时任务 DAO
 * @author iTeller_zc
 *
 */
public interface QuartzJobMapper {
	
	/**
	 * 新增定时任务
	 * @param quartzJob
	 */
	void addQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 查询
	 * @param jobGroup
	 * @param bizId
	 * @return
	 */
	QuartzJob existed(@Param(value="jobGroup") String jobGroup, @Param(value="bizId") Long bizId);
	
	/**
	 * 更新定时任务
	 * @param quartzJob
	 */
	void updateQuartzJob(QuartzJob quartzJob);
	
	/**
	 * 失效任务
	 * @param quartzJobId
	 */
	void invalidQuartzJob(@Param(value="id") Long quartzJobId);
}
