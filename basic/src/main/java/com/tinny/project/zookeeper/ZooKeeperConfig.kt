package com.tinny.project.zookeeper

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.CuratorFrameworkFactory
import org.apache.curator.retry.ExponentialBackoffRetry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @author : Tinny
 * @date : Created on 2020/4/3 - 16:03
 * @description :
 * @modified :
 */
@Component
class ZooKeeperConfig(private val zooKeeperConfigParam: ZooKeeperConfigParam) {
    lateinit var client: CuratorFramework
        private set

    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun init() {
        val retryPolicy = ExponentialBackoffRetry(zooKeeperConfigParam.baseSleepTimeMs, zooKeeperConfigParam.maxRetries)

        client = CuratorFrameworkFactory.builder()
                .connectString(zooKeeperConfigParam.server)
                .connectionTimeoutMs(zooKeeperConfigParam.connectionTimoutMs)
                .sessionTimeoutMs(zooKeeperConfigParam.sessionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build()

        client.start()
        log.debug("zookeeper 初始化完成...")
    }

    fun close() {
        client.close()
    }
}