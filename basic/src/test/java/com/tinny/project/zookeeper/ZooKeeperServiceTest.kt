package com.tinny.project.zookeeper

import org.apache.zookeeper.CreateMode
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author : Tinny
 * @date : Created on 2020/4/3 - 16:44
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ZooKeeperServiceTest {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    private lateinit var zooKeeperService: ZooKeeperService

    @Test
    fun isExistNode() {
        zooKeeperService.isExistNode("/testNode")
                .apply {
                    log.debug("result is: $this")
                }
    }

    @Test
    fun createNode() {
        zooKeeperService.createNode(CreateMode.PERSISTENT, "/testNode")
        log.debug("result is: ${zooKeeperService.isExistNode("/testNode")}")
    }

    @Test
    fun setNodeData() {
        zooKeeperService.setNodeData("/testNode", "hello, world")
        log.debug("result is: ${zooKeeperService.getNodeData("/testNode")}")
    }

    @Test
    fun createNodeAndData() {
        zooKeeperService.createNodeAndData(CreateMode.PERSISTENT, "/testNode/test2/data", "tinny, tindy")
        log.debug("result is: ${zooKeeperService.getNodeData("/testNode/test2/data")}")
    }

    @Test
    fun getNodeData() {
    }

    @Test
    fun getNodeChild() {
        zooKeeperService.getNodeChild("/testNode").apply {
            log.debug("result is: $this")
        }
    }

    @Test
    fun deleteNode() {
        zooKeeperService.deleteNode("/testNode/test2/data", true)
    }

    @Test
    fun getReadWriteLock() {
    }
}