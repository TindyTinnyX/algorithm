package com.tinny.project.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer

/**
 * @author : Tinny
 * @date : Created on 2020/4/7 - 11:57
 * @description :
 * @modified :
 */
@Configuration
open class RedisConfiguration {

    @Bean
    open fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> {
        val redisTemplate = RedisTemplate<Any, Any>()

        val fastJsonRedisSerializer = FastJsonRedisSerializer(Any::class.java)
        redisTemplate.valueSerializer = fastJsonRedisSerializer
        redisTemplate.hashValueSerializer = fastJsonRedisSerializer
        redisTemplate.keySerializer = RedisSerializer.string()
        redisTemplate.hashKeySerializer = RedisSerializer.string()

        redisTemplate.connectionFactory = redisConnectionFactory
        return redisTemplate
    }
}