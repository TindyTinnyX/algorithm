package com.tinny.common.mongo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

/**
 * @author : Tinny
 * @date : Created on 2020/4/20 - 16:31
 * @description :
 * @modified :
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationPropertiesScan
open class CommonMongoApplication {
//    @Bean
    open fun dotMongoConverter(mongoDbFactory: MongoDbFactory, mongoMappingContext: MongoMappingContext): MappingMongoConverter {
        return MappingMongoConverter(DefaultDbRefResolver(mongoDbFactory), mongoMappingContext)
                .apply {
                    this.setMapKeyDotReplacement("__")
                }
    }

//    @Bean
//    @ConditionalOnProperty(prefix = "common.mongo.db-setting", name = ["enable-transaction"], havingValue = "true")
    open fun transactionManger(mongoDbFactory: MongoDbFactory): MongoTransactionManager =
            MongoTransactionManager(mongoDbFactory)
}

fun main(args: Array<String>) {
    SpringApplicationBuilder(CommonMongoApplication::class.java)
            .run(*args)
}