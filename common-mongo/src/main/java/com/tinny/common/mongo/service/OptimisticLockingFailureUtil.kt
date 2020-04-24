package com.tinny.common.mongo.service

import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.function.Supplier

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 15:52
 * @description :
 * @modified :
 */
class OptimisticLockingFailureUtil {

    companion object {
        private var log = LoggerFactory.getLogger(OptimisticLockingFailureUtil::class.java)

        fun optimisticLockingOperate(runnable: Runnable, timeout: Long = 2) {
            try {
                CompletableFuture.runAsync {
                    while (true) {
                        try {
                            runnable.run()
                            break
                        } catch (ex: OptimisticLockingFailureException) {
                            log.error("handle error: ${ex.message}", ex)
                        }
                    }
                }
                        .get(timeout, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                log.error("数据库执行超时!", e)
                throw RuntimeException("数据库执行超时！")
            } catch (e: ExecutionException) {
                log.error("数据库执行超时！", e)
                throw RuntimeException("数据库执行超时!")
            } catch (e: TimeoutException) {
                log.error("数据库执行超时！", e)
                throw RuntimeException("数据库执行超时!")
            }
        }

        fun <T> optimisticLockingOperate(supplier: Supplier<T>, timeout: Long = 2): T? {
            try {
                return CompletableFuture.supplyAsync {
                    while (true) {
                        try {
                            return@supplyAsync supplier.get()
                        } catch (ex: OptimisticLockingFailureException) {
                            log.error("handle error: ${ex.message}", ex)
                        }
                    }
                    null
                }
                        .get(timeout, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                log.error("数据库执行超时!", e)
                throw RuntimeException("数据库执行超时！")
            } catch (e: ExecutionException) {
                log.error("数据库执行超时！", e)
                throw RuntimeException("数据库执行超时!")
            } catch (e: TimeoutException) {
                log.error("数据库执行超时！", e)
                throw RuntimeException("数据库执行超时!")
            }
        }
    }
}