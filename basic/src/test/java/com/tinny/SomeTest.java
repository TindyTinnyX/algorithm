package com.tinny;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author : Tinny
 * @date : Created on 2020/4/3 - 10:42
 * @description :
 * @modified :
 */
@RunWith(SpringRunner.class)
@Slf4j
public class SomeTest {
    @Test
    public void name() {
        List<Integer> list = List.of(1, 2, 3, 4);


        CompletableFuture[] hellos = list.stream().map(i -> CompletableFuture.runAsync(() -> {
            if (i == 2) {
                throw new RuntimeException("hello");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            log.debug("i is: {}", i);
        })
                .whenComplete((vi, throwable) -> {
                    if (throwable != null) {
                        log.error(throwable.getMessage(), throwable);
                    }
                }))
                .toArray(CompletableFuture[]::new);

        try {
            CompletableFuture.allOf(hellos).join();
        } catch (Exception e) {
            log.error("sss: " + e.getMessage(), e);
        }
    }
}
