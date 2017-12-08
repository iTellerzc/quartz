package com.hongao.quartz.consts;

/**
 * 错误码
 * @author iTeller_zc
 *
 */
public class ErrorCodes {
	
	/**
	 * 获取触发器错误
	 */
	public static final String TRIGGER_ERROR = "quartz.001";
	
	/**
	 * 创建任务失败
	 */
	public static final String CREATE_TASK_ERROR = "quartz.002";
	
	/**
	 * 运行定时任务失败
	 */
	public static final String RUN_TASK_ERROR = "quartz.003";
	
	/**
	 * 暂定定时任务失败
	 */
	public static final String PAUSE_TASK_ERROR = "quartz.004";
	
	/**
	 * 恢复定时任务失败
	 */
	public static final String RESUME_TASK_ERROR = "quartz.005";
	
	/**
	 * 更新定时任务失败
	 */
	public static final String UPDATE_TASK_ERROR = "quartz.006";
	
	/**
	 * 删除定时任务失败
	 */
	public static final String DELETE_TASK_ERROR = "quartz.007";
	
	/**
	 * 不存在的job
	 */
	public static final String NOT_EXISTED_JOB = "quartz.008";
	
	/**
	 * 系统错误
	 */
	public static final String SYS_ERROR = "quartz.096";
	
	private ErrorCodes(){
		
	}
}
