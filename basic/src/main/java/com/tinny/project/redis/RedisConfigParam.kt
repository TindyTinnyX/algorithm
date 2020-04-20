package com.tinny.project.redis

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author : Tinny
 * @date : Created on 2020/4/4 - 16:22
 * @description :
 * @modified :
 */
@Component
@ConfigurationProperties(prefix = "redis.sentinel")
class RedisConfigParam {
    var nodes: String? = null
    lateinit var master: String
    var maxTotal = 0
    var minIdle = 0
    var maxWaiMills = 0
    var timeBetweenEvictionRunsMillis = 0
    var testWhileIdle = false
    var testOnBorrow = false
    var testOnReturn = false
}