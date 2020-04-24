package com.tinny.licenses.port.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * @author : Tinny
 * @date : Created on 2020/4/23 - 16:58
 * @description :
 * @modified :
 */
@Configuration
open class SwaggerConfig {
    @Bean
    open fun interfaceApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfoBuilder()
                        .title("tinny")
                        .build())
                .groupName("tinny-controller")
                .forCodeGeneration(true)
                .select()
                .apis { true }
                .paths(PathSelectors.any())
                .build()
    }
}