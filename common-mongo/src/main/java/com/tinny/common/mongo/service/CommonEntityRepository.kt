package com.tinny.common.mongo.service

import com.tinny.common.mongo.entity.Entity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.stream.Stream

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 16:28
 * @description :
 * @modified :
 */
interface CommonEntityRepository<T : Entity<T>> : MongoRepository<T, String> {
    @Query(value = "{}", fields = "{}")
    fun findAllIds(): Stream<T>

    @Query(value = "{}")
    fun findAllStream(): Stream<T>
}