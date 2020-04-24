package com.tinny.common.mongo.config

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author : Tinny
 * @date : Created on 2020/4/20 - 16:26
 * @description :
 * @modified :
 */
@ConfigurationProperties(prefix = "common.mongo.db-setting")
class MongoBaseConfiguration {
    var enableTransaction = false
}