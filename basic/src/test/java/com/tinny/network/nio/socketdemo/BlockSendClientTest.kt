package com.tinny.network.nio.socketdemo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.CompletableFuture

/**
 * @author : Tinny
 * @date : Created on 2020/3/29 - 17:29
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class BlockSendClientTest {
    @Autowired
    private lateinit var blockSendClient: BlockSendClient

    @Test
    fun sendFile() {
        (0..5).map {
            CompletableFuture.runAsync(blockSendClient::sendFile)
        }.run {
            val array = Array<CompletableFuture<Void>?>(this.size) { null }
            this.forEachIndexed { index, completableFuture ->
                array[index] = completableFuture
            }
            CompletableFuture.allOf(*array).join()
        }
    }
}