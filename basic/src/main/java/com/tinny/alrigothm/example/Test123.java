package com.tinny.alrigothm.example;

import java.io.*;

/**
 * @author : Tinny
 * @date : Created on 2020/3/10 - 20:40
 * @description :
 * @modified :
 */
public class Test123 {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

        int n = Integer.parseInt(scanner.readLine());
        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(scanner.readLine());
        }

        int sum = input[0];
        int continueSum = input[0];

        for (int i = 1; i < n; i++) {
            continueSum = continueSum > 0 ? continueSum + input[i] : input[i];

            sum = Math.max(continueSum, sum);
        }

        writer.println(sum);
        writer.flush();
    }
}
