package com.tinny.licenses.port.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.stereotype.Component

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 16:41
 * @description :
 * @modified :
 */
@Component
@RefreshScope
class ServiceConfig {
    @Value("\${example.property}")
    lateinit var exampleProperty: String
}