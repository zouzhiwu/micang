package com.redis.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateDemo {
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("123", "hello");
		map.put("abc", 456);
		redisTemplate.opsForHash().putAll("hash", map);
		Map<Object, Object> ans = redisTemplate.opsForHash().entries("hash");
		System.out.println("ans: " + ans);
	}
}
