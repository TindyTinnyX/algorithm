package com.tinny.network.nio.socketdemo

import com.tinny.network.SystemConfiguration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.DataInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.ServerSocket
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 17:32
 * @description :
 * @modified :
 */
@Component
class BlockReceiveServer {
    private val log = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    private lateinit var systemConfiguration: SystemConfiguration

    fun startServer() {
        val serverSocket = ServerSocket(systemConfiguration.socketServerPort)

        while (true) {
            log.debug("server listen at: ${systemConfiguration.socketServerPort}")

            val socket = serverSocket.accept()
            Thread {
                try {
                    socket.use { socket ->
                        DataInputStream(socket.getInputStream()).use { dis ->
                            val fileName = dis.readUTF()
                            val fileLength = dis.readLong()
                            val directory = File(systemConfiguration.socketReceivePath)
                            if (!directory.exists()) {
                                directory.mkdir()
                            }

                            File(directory.absolutePath + File.separatorChar + fileName).let {
                                FileOutputStream(it)
                            }.use { fos ->
                                val start = LocalDateTime.now()
                                log.debug("block IO 传输开始： ")

                                val bytes = ByteArray(1024)
                                var length = 0
                                while (dis.read(bytes, 0, bytes.size).also { length = it } != -1) {
                                    fos.write(bytes, 0, length)
                                    fos.flush()
                                }

                                log.debug("文件接收成功，fileName: $fileName")
                                log.debug("size: $fileLength")

                                log.debug("block IO takes: ${Duration.between(start, LocalDateTime.now()).toMillis()}")
                            }
                        }
                    }
                } catch (e: Exception) {
                    log.error(e.message, e)
                }
            }.start()
        }
    }
}