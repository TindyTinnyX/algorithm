package com.tinny.common.mongo.entity

import lombok.ToString
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import java.time.LocalDateTime

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 15:20
 * @description :
 * @modified :
 */
@ToString
open class Entity<T> {
    companion object {
        const val P_ID = "id"
        const val P_CREATE_TIME = "createTime"
        const val P_LAST_MODIFIED_TIME = "lastModifiedTime"
    }

    @Id
    private var id = ObjectId().toHexString()

    @Version
    private var _version_: String? = null

    /*
    * 创建时间
    * */
    var createTime: LocalDateTime? = null
    /*
    * 最新修改时间
    * */
    var lastModifiedTime: LocalDateTime? = null

    fun getId() = this.id

    fun get_version_() = this._version_

    open fun sameIdentityAs(t: T): Boolean {
        if (t == null) return false
        if (t is Entity<*>) {
            return this.getId().equals(t.getId())
        }
        return false
    }
}