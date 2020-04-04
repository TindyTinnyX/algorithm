package com.tinny.alrigothm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/2 - 5:19
 * @description :
 * @modified :
 */
@Slf4j
public class Josephus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        // initialize the queue
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                queue.add(queue.remove());
            }
            log.debug("value: {}", queue.remove());
        }
    }
}
