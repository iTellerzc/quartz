package com.hongao.quartz.doll;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * quartz基础测试
 * @author iTeller_zc
 *
 * @date 2017年12月8日 下午3:59:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ha-doll-quartz-test.xml"})
public abstract class HaDollQuartzBaseTest {

}
