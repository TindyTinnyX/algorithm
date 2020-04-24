package com.tinny.licenses.port.config

import org.springframework.boot.actuate.trace.http.HttpTraceRepository
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author : Tinny
 * @date : Created on 2020/4/24 - 15:25
 * @description :
 * @modified :
 */
@Configuration
open class ActuatorConfig {
    @Bean
    open fun httpTraceRepository(): HttpTraceRepository = InMemoryHttpTraceRepository()
}