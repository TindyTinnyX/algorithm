package com.tinny.network.nio.socketdemo

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 18:04
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class BlockReceiveServerTest {
    @Autowired
    private lateinit var blockReceiveServer: BlockReceiveServer

    @Test
    fun startServer() {
        blockReceiveServer.startServer()
    }
}