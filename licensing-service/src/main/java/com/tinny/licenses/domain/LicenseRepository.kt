package com.tinny.licenses.domain

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.stream.Stream

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 16:39
 * @description :
 * @modified :
 */
@Repository
interface LicenseRepository : MongoRepository<License, String> {
    /*
    * findByOrganizationId
    * */
    fun findByOrganizationId(organizationId: String): Stream<License>
}