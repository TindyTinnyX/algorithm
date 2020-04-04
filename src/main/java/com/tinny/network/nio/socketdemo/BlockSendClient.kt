package com.tinny.network.nio.socketdemo

import com.tinny.network.SystemConfiguration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.net.Socket
import javax.annotation.PostConstruct

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 15:12
 * @description :
 * @modified :
 */
@Component
@Scope/*(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)*/
class BlockSendClient {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    @Autowired
    private lateinit var systemConfiguration: SystemConfiguration

    @PostConstruct
    fun sendFile() {
        try {
            Socket(systemConfiguration.socketServerIp, systemConfiguration.socketServerPort).use { socket ->
                val file = File(this.javaClass.classLoader.getResource(systemConfiguration.socketSendFile)?.path!!)
                println(file.exists())
                if (file.exists()) {
                    FileInputStream(file).use { fis ->
                        DataOutputStream(socket.getOutputStream()).use { dos ->
                            dos.writeUTF("copy_${file.name}")
                            dos.flush()
                            dos.writeLong(file.length())
                            dos.flush()

                            logger.debug("===== 开始传输文件 =====")
                            val bytes = ByteArray(1024)
                            var length = 0
                            var progress = 0L

                            while (fis.read(bytes, 0, bytes.size).also { length = it } != -1) {
                                dos.write(bytes, 0, length)
                                dos.flush()
                                progress += length
                                logger.debug("| ${progress * 100 / file.length()}% |")
                            }
                            logger.debug("===== 文件传输成功 ========")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }
}