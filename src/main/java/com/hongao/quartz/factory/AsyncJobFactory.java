package com.hongao.quartz.factory;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 异步任务工厂 暂时不支持
 * @author iTeller_zc
 *
 */
@Deprecated
public class AsyncJobFactory extends QuartzJobBean {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(AsyncJobFactory.class);
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.info("not support execute.");
    }
}
