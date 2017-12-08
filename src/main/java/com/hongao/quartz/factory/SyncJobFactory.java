package com.hongao.quartz.factory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.hongao.quartz.consts.QuartzJobConsts;
import com.hongao.quartz.model.QuartzJob;

/**
 * 同步任务工厂
 * @author iTeller_zc
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SyncJobFactory extends QuartzJobBean {

	/* 日志对象 */
	private static final Logger logger = LoggerFactory.getLogger(SyncJobFactory.class);

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		QuartzJob scheduleJob = (QuartzJob) mergedJobDataMap.get(QuartzJobConsts.JOB_PARAM_KEY);
		String bizId = String.valueOf(mergedJobDataMap.get(QuartzJobConsts.BIZ_ID));
		logger.info("jobName:{}, bizId:{} .", scheduleJob.getJobName(), bizId);
		String url = scheduleJob.getUrl();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(QuartzJobConsts.BIZ_ID, bizId));
		CloseableHttpResponse response;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			response = httpclient.execute(httpPost);
			logger.info("resp:{} .", response.getStatusLine());
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
