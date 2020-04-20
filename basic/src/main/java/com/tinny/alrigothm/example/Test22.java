package com.tinny.alrigothm.example;

import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/4 - 18:59
 * @description :
 * @modified :
 */
public class Test22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        char preChar = input.charAt(0);
        int times = 1;

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append(preChar);

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == preChar) {
                times++;
            } else {
                preChar = c;
                if (times > 1) {
                    resultBuilder.append(times);
                }
                times = 1;
                resultBuilder.append(c);
            }
        }

        if (times > 1) {
            resultBuilder.append(times);
        }

        System.out.println(resultBuilder.toString());
    }
}
