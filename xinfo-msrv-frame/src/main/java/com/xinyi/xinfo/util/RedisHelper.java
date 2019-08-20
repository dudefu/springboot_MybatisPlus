package com.xinyi.xinfo.util;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * <p>
 * redis工具类
 * </p>
 * 
 * @author tao.Li
 * @Date 2018-5-8
 */
public class RedisHelper {
	
	private RedisTemplate<String, String> redisTemplate;
	private ValueOperations<String, String> operations;
	private long expire = 1000*50;

	@Autowired
	public RedisHelper(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.operations = redisTemplate.opsForValue();
	}

	public String setLock(String key) {
		String uuid = UUID.randomUUID().toString();// 用于释放锁
		boolean status = false;
		try {
			while (true) {
				status = operations.setIfAbsent(key, uuid);
				if (status) {// 获取到了锁
					redisTemplate.expire(key, expire, TimeUnit.SECONDS);// 设置超时时间
					break;
				} else {// 获取锁失败的时候，判断一下是否没有超时时间，如果没有，那么可能这个是其他客户端获取的时候失败。加一个超时时间
					long expiretemp = redisTemplate.getExpire(key);
					if (expiretemp == -1)
						redisTemplate.expire(key, expire, TimeUnit.SECONDS);// 设置超时时间
					Thread.sleep(expire);
				}
			}
		} catch (Exception e) {
			return "-1";
		}
		return uuid;
	}
	
	public String lock(String key) {
		String uuid = UUID.randomUUID().toString();// 用于释放锁
		boolean status = false;
		try {
				status = operations.setIfAbsent(key, uuid);
				if (status) {// 获取到了锁
					redisTemplate.expire(key, expire, TimeUnit.SECONDS);// 设置超时时间
				} else {
					uuid = "";
				}
		} catch (Exception e) {
			return "-1";
		}
		return uuid;
	}
	
	// 释放锁：
	public boolean releaseLock(String key, String uuid) {
		/**
		 * 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
		 * 先判断一下当前持有锁的是否是当前的进程，如若不是，则放弃锁
		 */
		String value = operations.get(key).toString();
		if (!value.equals(uuid))
			return true;
		redisTemplate.delete(key);
		return false;
	}

}
