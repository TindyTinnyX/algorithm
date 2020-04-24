package com.tinny.common.mongo.service

import com.tinny.common.mongo.entity.Entity
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 15:45
 * @description :
 * @modified :
 */
//@Component
//class BeforeSaveListener : AbstractMongoEventListener<Entity<out Any>>() {
//    override fun onBeforeConvert(event: BeforeConvertEvent<Entity<out Any>>) {
//        val now = LocalDateTime.now()
//
//        if (event.source.createTime == null) {
//            event.source.createTime = now
//        }
//
//        event.source.lastModifiedTime = now
//        super.onBeforeConvert(event)
//    }
//}