package com.tinny.project.zookeeper

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author : Tinny
 * @date : Created on 2020/4/3 - 15:56
 * @description :
 * @modified :
 */
@ConfigurationProperties(prefix = "zoo.keeper")
@Component
class ZooKeeperConfigParam {
    var enabled = false
    var server = "127.0.0.1:2181"
    var namespace = ""
    var digest = ""
    var sessionTimeoutMs = 3000
    var connectionTimoutMs = 60000
    var maxRetries = 2
    var baseSleepTimeMs = 1000
}