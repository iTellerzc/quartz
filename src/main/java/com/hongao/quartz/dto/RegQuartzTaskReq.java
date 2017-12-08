package com.hongao.quartz.dto;

import javax.validation.constraints.NotNull;

/**
 * 注册定时任务请求
 * 
 * @author iTeller_zc
 *
 */
public class RegQuartzTaskReq {
	
	/**
	 * 业务主键
	 */
	@NotNull
	private Long bizId;

    /**
     * 任务名称 建议 以jobGroup+":"+bizId组合
     */
	@NotNull
    private String jobName;

    /** 
     * 任务别名 
     */
    @NotNull
    private String aliasName;

    /** 
     * 任务分组 
     * @see com.hongao.duomiwan.quartz.consts.JobGroups
     */
    @NotNull
    private String jobGroup;

    /** 
     * 任务运行时间表达式 
     */
    @NotNull
    private String cronExpression;

    /** 
     * 是否异步 
     */
    private Boolean isSync;

    /** 
     * 任务描述 
     */
    private String description;

    /** 
     * 任务执行url 
     */
    private String  url;
    
	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Boolean getIsSync() {
		return isSync;
	}

	public void setIsSync(Boolean isSync) {
		this.isSync = isSync;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
}
