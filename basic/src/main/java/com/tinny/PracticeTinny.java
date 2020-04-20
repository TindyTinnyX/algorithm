package com.tinny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PracticeTinny {

    public static void main(String[] args) {
        SpringApplication.run(PracticeTinny.class, args);
    }

}
