package com.tinny.alrigothm.example;

import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/1 - 19:42
 * @description :
 * @modified :
 */
public class Three<T> {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();

        for (int i = 1; i < total; i++) {
            int num = total;
            int A = 0;
            int B = 0;

            while (num > 0) {
                int x = Math.min(i,num);

                A += x;
                num -= x;
                B += num / 10;
                num -= num / 10;
            }

            if (A >= B) {
                System.out.println(i);
                return;
            }
        }
    }
}
