package com.tinny.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author : Tinny
 * @date : Created on 2020/4/17 - 18:20
 * @description :
 * @modified :
 */
@SpringBootApplication
@RefreshScope
public class LicenseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenseApplication.class, args);
    }
}
