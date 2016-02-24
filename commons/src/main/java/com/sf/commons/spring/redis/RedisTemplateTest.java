package com.sf.commons.spring.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 描述：使用spring集成redis测试类
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月8日 下午8:42:09	    204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class RedisTemplateTest {
	private static Logger logger = LoggerFactory.getLogger(RedisTemplateTest.class);
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/sf/commons/spring/redis/redis-config.xml");
		StringRedisTemplate redisTemplate = context.getBean("redisTemplate", StringRedisTemplate.class);
		ValueOperations<String, String> redisOps = redisTemplate.opsForValue();
//		for (int i = 0; i < 10; i++) {
//			String key="yangnew_"+i;
//			redisOps.set(key, key);
//		}
		while(true){
			try{
				System.out.println(redisOps.get("yangnew_1"));
			}catch(Exception e){
				logger.error("error get redis",e);
			}
			Thread.sleep(2000);
		}
		
//		Person p = context.getBean("person",Person.class);
//		System.out.println(p);
//
//		System.out.println("end");
	}
}
