package com.tinny.alrigothm.example;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/10 - 19:19
 * @description :
 * @modified :
 */
public class Test333 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));

        String input = scanner.nextLine();
        StringBuilder builder = new StringBuilder();
        List<String> words = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                builder.append(c);
            } else {
                String s = builder.toString();
                if (s.length() != 0) {
                    char first = Character.toUpperCase(s.charAt(0));
                    String rest = "";
                    if (s.length() >= 2) {
                        rest = s.substring(1);
                    }
                    words.add(first + rest);
                    builder = new StringBuilder();
                }
            }
        }

        String last = builder.toString();
        if (last.length() != 0) {
            String newWord = last.replace(last.charAt(0), Character.toUpperCase(last.charAt(0)));
            words.add(newWord);
        }

        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = words.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(" ");
            }
        }
        sb.append(".");
        writer.println(sb.toString());

        writer.flush();
        String s = "66666666664123+Who-32didn't love? Solo32..";
    }
}
