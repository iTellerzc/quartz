package com.hongao.quartz.game;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongao.parent.exception.HaBizException;
import com.hongao.quartz.consts.JobGroups;
import com.hongao.quartz.dto.RegQuartzTaskReq;
import com.hongao.quartz.service.HaQuartzService;

/**
 * 
 * @author iTeller_zc
 *
 * @date 2017年12月8日 下午4:01:47
 */
public class TestHaGameQuartzService extends HaGameQuartzBaseTest{
	
	@Autowired
	private HaQuartzService haQuartzService;
	
	@Test
	public void testRegister() throws HaBizException, InterruptedException{
		RegQuartzTaskReq regQuartzTaskReq = new RegQuartzTaskReq();
		regQuartzTaskReq.setAliasName("测试");
		regQuartzTaskReq.setBizId(1L);
		regQuartzTaskReq.setCronExpression("0/1 * * * * ?");
		regQuartzTaskReq.setDescription("测试 job");
		regQuartzTaskReq.setIsSync(false);
		regQuartzTaskReq.setJobGroup(JobGroups.TEST);
		regQuartzTaskReq.setJobName(JobGroups.TEST + ":" + 1L);
		regQuartzTaskReq.setUrl("http://localhost:8080/test/test");
		haQuartzService.register(regQuartzTaskReq);
		Thread.sleep(1000*60*10);
	}
}
