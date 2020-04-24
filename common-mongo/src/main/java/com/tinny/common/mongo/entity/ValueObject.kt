package com.tinny.common.mongo.entity

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 15:51
 * @description :
 * @modified :
 */
interface ValueObject<T> {
    fun sameAs(valueObject: T)
}