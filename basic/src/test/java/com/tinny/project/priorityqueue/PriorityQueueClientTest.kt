package com.tinny.project.priorityqueue

import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.CompletableFuture
import kotlin.random.Random

/**
 * @author : Tinny
 * @date : Created on 2020/4/6 - 18:48
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class PriorityQueueClientTest {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val random = Random
    @Autowired
    lateinit var priorityQueueClient: PriorityQueueClient
    @Autowired
    lateinit var redisTemplate: StringRedisTemplate


    @Test
    fun taskConsumer() {
        while (true) {
            val size = redisTemplate.opsForList()
                    .size(BONUS_KEY)
            if (size != null && size > 0) {
                val range = redisTemplate.opsForList()
                        .range(BONUS_KEY, 0, size)

                if (!range.isNullOrEmpty()) {
                    range.map {
                        it.toDouble()
                    }
                            .sum()
                            .apply {
                                log.debug("bonus remain: ${"%.2f".format(this)}")
                            }
                } else {
                    log.debug("bonus remain nothing!")
                }

                repeat(3) {
                    CompletableFuture.runAsync {
                        Thread.sleep(random.nextLong(10, 500))
                        priorityQueueClient.taskConsumer()
                    }
                }

                Thread.sleep(300)
            } else {
                break
            }
        }
    }
}