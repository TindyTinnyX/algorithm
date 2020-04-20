package com.tinny.alrigothm.example;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author : Tinny
 * @date : Created on 2020/3/7 - 22:03
 * @description :
 * @modified :
 */
public class Test33 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

        int numberOfJob = scanner.nextInt();
        int numberOfPerson = scanner.nextInt();

        int[] personDi = new int[numberOfPerson];

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < numberOfJob; i++) {
            int di = scanner.nextInt();
            int pi = scanner.nextInt();
            if (!treeMap.containsKey(di) || treeMap.get(di) < pi) {
                treeMap.put(di, pi);
            }
        }

        for (int j = 0; j < numberOfPerson; j++) {
            int pi = scanner.nextInt();
            personDi[j] = pi;
            treeMap.putIfAbsent(pi, 0);
        }

        int maxSub = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> e : treeMap.entrySet()) {
            Integer k = e.getKey();
            Integer v = e.getValue();
            if (v < maxSub) {
                treeMap.put(k, maxSub);
            } else {
                maxSub = v;
            }
        }

        for (int value : personDi) {
            writer.println(treeMap.get(value));
        }

        writer.flush();
    }
}
