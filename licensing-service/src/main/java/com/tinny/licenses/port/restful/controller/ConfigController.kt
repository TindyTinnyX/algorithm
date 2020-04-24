package com.tinny.licenses.port.restful.controller

import com.tinny.licenses.port.config.ServiceConfig
import com.tinny.licenses.port.restful.controller.dto.ServiceConfigDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author : Tinny
 * @date : Created on 2020/4/24 - 10:18
 * @description :
 * @modified :
 */
@RestController
@RequestMapping("/v1/organizations/config")
@Api("license服务配置 - /v1/organizations/config")
open class ConfigController(private val serviceConfig: ServiceConfig) {

    @ApiOperation("获取配置")
    @GetMapping("")
    fun getConfig(): ServiceConfigDto {
        return ServiceConfigDto(serviceConfig.exampleProperty)
    }
}