package com.redis.test;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class RedisAtomicLongDemo {
	public static RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory fac = new JedisConnectionFactory();
		fac.setHostName("192.168.0.170");
		fac.setPort(6379);
		fac.setPassword("123456");
		fac.setTimeout(100000);
		fac.getPoolConfig().setMaxIdle(100);
		fac.getPoolConfig().setMaxTotal(300);
		fac.getPoolConfig().setMaxWaitMillis(1000);
		fac.getPoolConfig().setMinEvictableIdleTimeMillis(300000);
		fac.getPoolConfig().setNumTestsPerEvictionRun(3);
		fac.getPoolConfig().setTimeBetweenEvictionRunsMillis(60000);
		fac.getPoolConfig().setTestOnBorrow(true);
		fac.getPoolConfig().setTestWhileIdle(true);
		fac.afterPropertiesSet();
		return fac;
	}

	public static RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redis = new RedisTemplate<>();
		redis.setConnectionFactory(redisConnectionFactory);
		redis.afterPropertiesSet();
		return redis;
	}

	public static void main(String[] args) {
		RedisConnectionFactory factory = redisConnectionFactory();
		RedisTemplate<String, String> redisTemplate = redisTemplate(factory);
		Long roomId = getIncrement("roomId", redisTemplate);
		System.out.println(roomId);
	}
	
	public static Long getIncrement(String key, RedisTemplate<String, String> redisTemplate) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();
        return increment;
    }

}
