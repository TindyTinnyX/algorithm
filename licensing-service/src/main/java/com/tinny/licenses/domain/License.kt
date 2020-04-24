package com.tinny.licenses.domain

import com.tinny.common.mongo.entity.Entity
import org.springframework.data.mongodb.core.mapping.Document


/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 14:46
 * @description :
 * @modified :
 */
@Document
data class License(val organizationId: String? = null, val productName: String? = null, val licenseType: String? = null, val licenseMac: Int? = null, val comment: String? = null) : Entity<License>()
