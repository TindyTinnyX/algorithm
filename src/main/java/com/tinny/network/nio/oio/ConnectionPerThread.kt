package com.tinny.network.nio.oio

import com.tinny.network.nio.SERVER_BUFFER_SIZE
import com.tinny.network.nio.SERVER_PORT
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 14:59
 * @description :
 * @modified :
 */

class Handler(private val socket: Socket) : Runnable {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun run() {
        while (true) {
            try {
                val input = ByteArray(SERVER_BUFFER_SIZE)
                socket.getInputStream().read(input)
                val output = ByteArray(SERVER_BUFFER_SIZE)

                socket.getOutputStream().write(output)
            } catch (ex: IOException) {
                log.error(ex.message, ex)
            }
        }
    }
}

object ConnectionPerThread {
    @JvmStatic
    fun main(args: Array<String>) {
        val serverSocket = ServerSocket(SERVER_PORT)
        while (!Thread.interrupted()){
            val socket = serverSocket.accept()
            val handler = Handler(socket)

            Thread(handler).start()
        }
    }
}