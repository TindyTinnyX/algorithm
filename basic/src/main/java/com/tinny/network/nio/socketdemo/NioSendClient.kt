package com.tinny.network.nio.socketdemo

import com.tinny.network.SystemConfiguration
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.File
import java.net.URLDecoder

/**
 * @author : Tinny
 * @date : Created on 2020/3/31 - 16:45
 * @description :
 * @modified :
 */
@Component
class NioSendClient(private val systemConfiguration: SystemConfiguration) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun sendFile() {
        val srcPath = ClassLoader.getSystemClassLoader().getResource(systemConfiguration.socketSendFile)!!.path
                .let {
                    URLDecoder.decode(it, "utf-8")
                }
        log.debug("srcPath is: $srcPath")
        log.debug("destFile is: ${systemConfiguration.socketReceiveFile}")

        val file = File(srcPath)
        if (!file.exists()) {
            log.debug("file is not exist!")
            return
        }


    }
}