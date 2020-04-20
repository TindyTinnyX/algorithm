package com.tinny.project.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.RedisScript
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

/**
 * @author : Tinny
 * @date : Created on 2020/4/5 - 19:07
 * @description :
 * @modified :
 */
@Component
class RedisLock(private val redisTemplate: StringRedisTemplate) {

    /*
    * 加锁
    * */
    fun lock(key: String, requestId: String, expire: Long): Boolean = redisTemplate.opsForValue()
            .setIfAbsent(key, requestId, expire, TimeUnit.SECONDS) ?: false

    /*
    * 释放锁
    * */
    fun unlock(key: String, requestId: String): Boolean {
        val script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end"
        val result = redisTemplate.execute(RedisScript.of(script, Int::class.java), listOf(key), requestId)
        return result == 1
    }
}