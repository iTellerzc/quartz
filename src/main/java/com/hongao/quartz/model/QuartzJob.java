package com.hongao.quartz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 * @author iTeller_zc
 *
 */
public class QuartzJob implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2062229688880929633L;

	/** 
	 * 任务id 
	 */
    private Long id;
    
    /**
     * 业务主键
     */
    private Integer bizId;

    /**
     * 任务名称
     */
    private String jobName;

    /** 
     * 任务别名 
     */
    private String aliasName;

    /** 
     * 任务分组
     */
    private String jobGroup;

    /** 
     * 触发器
     */
    private String jobTrigger;

    /** 
     * 任务状态
     */
    private String status;

    /** 
     * 任务运行时间表达式
     */
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
     * 创建时间 
     */
    private Date  crtDate;

    /** 
     * 修改时间 
     */
    private Date  updDate;

    /** 
     * 任务执行url 
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getBizId() {
		return bizId;
	}

	public void setBizId(Integer bizId) {
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

    public String getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSync() {
        return isSync;
    }

    public void setIsSync(Boolean isSync) {
        this.isSync = isSync;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
    
}
