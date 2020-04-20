package com.tinny.network

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 15:23
 * @description :
 * @modified :
 */
@Component
@ConfigurationProperties(prefix = "system")
class SystemConfiguration {
    var socketServerIp = "127.0.0.1"
    var socketServerPort = 9901
    lateinit var socketSendFile: String
    var socketReceiveFile = "dest.application.yml"
    var socketReceivePath = ""
    var sendBufferSize = 1024
    var serverBufferSize = 10240
}