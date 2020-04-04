package com.tinny.project.zookeeper

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock
import org.apache.zookeeper.CreateMode
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @author : Tinny
 * @date : Created on 2020/4/3 - 16:17
 * @description :
 * @modified :
 */
@Service
class ZooKeeperService(private val zooKeeperConfig: ZooKeeperConfig) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun isExistNode(path: String): Boolean {
        zooKeeperConfig.client.sync()

        return try {
            zooKeeperConfig.client.checkExists()
                    .forPath(path) != null
        } catch (e: Exception) {
            log.error(e.message, e)
            false
        }
    }

    fun createNode(createMode: CreateMode, path: String) {
        try {
            zooKeeperConfig.client.create()
                    .creatingParentsIfNeeded()
                    .withMode(createMode)
                    .forPath(path);
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }

    fun setNodeData(path: String, nodeData: String) {
        try {
            zooKeeperConfig.client.setData()
                    .forPath(path, nodeData.toByteArray())
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }

    fun createNodeAndData(createMode: CreateMode, path: String, nodeData: String) {
        try {
            zooKeeperConfig.client.create()
                    .creatingParentsIfNeeded()
                    .withMode(createMode)
                    .forPath(path, nodeData.toByteArray())
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }

    fun getNodeData(path: String): String? {
        return try {
            zooKeeperConfig.client.data.forPath(path)
                    .run {
                        String(this, Charsets.UTF_8)
                    }
        } catch (e: Exception) {
            log.error(e.message, e)
            null
        }
    }

    fun getNodeChild(path: String): List<String> {
        return try {
            zooKeeperConfig.client.children
                    .forPath(path)
        } catch (e: Exception) {
            log.error(e.message, e)
            listOf()
        }
    }

    fun deleteNode(path: String, recursive: Boolean) {
        try {
            when (recursive) {
                true -> zooKeeperConfig.client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path)
                else -> zooKeeperConfig.client.delete().guaranteed().forPath(path)
            }
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }

    fun getReadWriteLock(path: String): InterProcessReadWriteLock =
            InterProcessReadWriteLock(zooKeeperConfig.client, path)
}