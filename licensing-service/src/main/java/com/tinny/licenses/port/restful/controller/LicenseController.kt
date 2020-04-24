package com.tinny.licenses.port.restful.controller

import com.tinny.licenses.application.LicenseService
import com.tinny.licenses.domain.License
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 17:11
 * @description :
 * @modified :
 */
@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
@Api("license服务 - v1/organizations/{organizationId}/licenses")
class LicenseController(private val licenseService: LicenseService) {
    @GetMapping
    @ApiOperation("获取license")
    fun getLicenses(@PathVariable organizationId: String): List<License> =
            licenseService.getLicenseByOrg(organizationId)
}