package com.tinny.project.redis

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author : Tinny
 * @date : Created on 2020/4/5 - 19:59
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RedisLockTest {
    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    @Test
    fun lock() {
        val get = redisTemplate.opsForValue()
                .get("name")
    }

    @Test
    fun unlock() {
    }
}