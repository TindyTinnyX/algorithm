package com.tinny.project.redis

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.springframework.data.redis.serializer.RedisSerializer

/**
 * @author : Tinny
 * @date : Created on 2020/4/7 - 11:59
 * @description :
 * @modified :
 */
class FastJsonRedisSerializer<T>(private val clazz: Class<T>) : RedisSerializer<T> {
    private val defaultCharset = Charsets.UTF_8

    override fun serialize(t: T?): ByteArray? {
        if (t == null) {
            return byteArrayOf()
        }

        return JSON.toJSONString(t, SerializerFeature.WriteClassName).toByteArray(defaultCharset)
    }

    override fun deserialize(bytes: ByteArray?): T? {
        if (null == bytes || bytes.isEmpty()) {
            return null
        }

        return JSON.parseObject(String(bytes, defaultCharset), clazz)
    }
}