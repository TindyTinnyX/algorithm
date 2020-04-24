package com.tinny.licenses.domain

import com.tinny.common.mongo.entity.Entity
import lombok.Builder
import lombok.ToString
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 16:36
 * @description :
 * @modified :
 */

@Document
@ToString
@Builder
data class Organization(val name: String? = null, val contactName: String? = null, val contactEmail: String? = null, val contactPhone: String? = null) : Entity<Organization>()
