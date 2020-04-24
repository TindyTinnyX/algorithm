package com.tinny.licenses.application

import com.tinny.licenses.domain.License
import com.tinny.licenses.domain.LicenseRepository
import org.springframework.stereotype.Service
import kotlin.streams.toList

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 17:15
 * @description :
 * @modified :
 */

@Service
class LicenseService(private val licenseRepository: LicenseRepository/*, private val serviceConfig: ServiceConfig*/) {

    fun getLicenseByOrg(organizationId: String): List<License> = licenseRepository.findByOrganizationId(organizationId).toList()
}
