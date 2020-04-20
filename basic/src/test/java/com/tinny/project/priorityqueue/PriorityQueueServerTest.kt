package com.tinny.project.priorityqueue

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author : Tinny
 * @date : Created on 2020/4/6 - 18:06
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class PriorityQueueServerTest {
    @Autowired
    lateinit var priorityQueueServer: PriorityQueueServer

    @Test
    fun producer() {
        priorityQueueServer.producer()
    }
}