package com.sf.commons.redis;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Pool;

/**
 * 描述：
 * 
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           	   		PERSON          REASON
 *  1    2016年1月28日 下午6:01:14		204401          Create
 * ****************************************************************************
 * </pre>
 * 
 * @author xianyang.yang
 * @since 1.0
 */
public class JedisSentinelConnectionFactory extends JedisConnectionFactory{
	private static final Method SET_TIMEOUT_METHOD;
	private static final Method GET_TIMEOUT_METHOD;
	private RedisSentinelConfiguration sentinelConfig;
	private JedisPoolConfig poolConfig;
	
	public JedisSentinelConnectionFactory(RedisSentinelConfiguration sentinelConfig, JedisPoolConfig poolConfig) {
		this.sentinelConfig = sentinelConfig;
		this.poolConfig = poolConfig != null ? poolConfig : new JedisPoolConfig();
	}
	

	static {

		// We need to configure Jedis socket timeout via reflection since the method-name was changed between releases.
		Method setTimeoutMethodCandidate = ReflectionUtils.findMethod(JedisShardInfo.class, "setTimeout", int.class);
		if (setTimeoutMethodCandidate == null) {
			// Jedis V 2.7.x changed the setTimeout method to setSoTimeout
			setTimeoutMethodCandidate = ReflectionUtils.findMethod(JedisShardInfo.class, "setSoTimeout", int.class);
		}
		SET_TIMEOUT_METHOD = setTimeoutMethodCandidate;

		Method getTimeoutMethodCandidate = ReflectionUtils.findMethod(JedisShardInfo.class, "getTimeout");
		if (getTimeoutMethodCandidate == null) {
			getTimeoutMethodCandidate = ReflectionUtils.findMethod(JedisShardInfo.class, "getSoTimeout");
		}

		GET_TIMEOUT_METHOD = getTimeoutMethodCandidate;
	}
//	@Override
//	protected Pool<ShardedJedis> createRedisSentinelPool(RedisSentinelConfiguration config) {
//		List<JedisShardInfo> shards;
//		return new ShardedJedisPool(getPoolConfig() != null ? getPoolConfig() : new JedisPoolConfig(), shards);
//	}
	@Override
	public void afterPropertiesSet() {
		if (getShardInfo() == null) {
			setShardInfo( new JedisShardInfo(getHostName(), getPort()));

			if (StringUtils.hasLength(getPassword())) {
				getShardInfo().setPassword(getPassword());
			}

			if (getTimeout() > 0) {
				ReflectionUtils.invokeMethod(SET_TIMEOUT_METHOD, getShardInfo(), getTimeout());
			}
		}

	}

	private Pool<Jedis> createPool() {
		if (isRedisSentinelAware()) {
			return createRedisSentinelPool(this.sentinelConfig);
		}
		return createRedisPool();
	}
}
