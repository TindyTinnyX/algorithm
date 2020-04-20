package com.tinny.project.priorityqueue

import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import kotlin.math.min
import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/4/6 - 17:28
 * @description :
 * @modified :
 */
@Service
class PriorityQueueServer(private val redisTemplate: StringRedisTemplate) {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val random = Random

    fun producer() {
        var distribute = 9980

        redisTemplate.delete(BONUS_KEY)

        repeat((1..19).count()) {
            val fee = random.nextInt(0, min(1000, distribute)) + 1
            val doubleFee = fee / 100.0

            log.debug("bonus_$it is: $doubleFee")
            redisTemplate.opsForList()
                    .rightPush(BONUS_KEY, doubleFee.toString())
            distribute -= fee - 1
        }
        val doubleFee = (distribute + 1) / 100.0
        redisTemplate.opsForList()
                .rightPush(BONUS_KEY, doubleFee.toString())
        log.debug("bonus_20 is: $doubleFee")
    }
}