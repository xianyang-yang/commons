package com.sf.commons;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月29日 上午10:46:33		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class TestAutoReloadLog4j {
private static Logger logger = LoggerFactory.getLogger(TestAutoReloadLog4j.class);

	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = new Jedis("10.202.33.62", 26379);
		List<Map<String, String>> sentinelMasters = jedis.sentinelMasters();
		sentinelMasters.forEach(s ->System.out.println(s.get("name")));
	}
}
