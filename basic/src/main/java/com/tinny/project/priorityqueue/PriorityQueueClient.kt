package com.tinny.project.priorityqueue

import com.tinny.project.redis.RedisLock
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author : Tinny
 * @date : Created on 2020/4/6 - 17:44
 * @description :
 * @modified :
 */
@Service
class PriorityQueueClient(private val redisTemplate: StringRedisTemplate, private val redisLock: RedisLock) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun taskConsumer() {
        val uuid = UUID.randomUUID()

        if (redisLock.lock(BONUS_KEY + "lock", uuid.toString(), 2)) {
            try {
                redisTemplate.opsForList()
                        .leftPop(BONUS_KEY)
                        .apply {
                            when (this) {
                                null -> log.debug("nothing remain for bonus: ${Thread.currentThread().name}")
                                else -> log.debug("${Thread.currentThread().name} get ${this.toDouble()}")
                            }
                        }
            } finally {
                redisLock.unlock(BONUS_KEY + "lock", uuid.toString())
            }
        }
    }
}