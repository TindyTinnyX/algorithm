package com.tinny.licenses.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 17:29
 * @description :
 * @modified :
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class LicenseTest {
    private val organizationId = "test_organizationId"

    @Autowired
    private lateinit var licenseRepository: LicenseRepository

    @Test
    fun addData() {
        (0..100).map {
            License(organizationId)
        }.apply {
            licenseRepository.saveAll(this)
        }
    }
}