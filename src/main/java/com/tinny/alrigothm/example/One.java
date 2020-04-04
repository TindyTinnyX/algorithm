package com.tinny.alrigothm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author : Tinny
 * @date : Created on 2020/3/1 - 13:49
 * @description :
 * @modified :
 */
@Slf4j
public class One {
    protected static final Set<String> permitOps;

    static {
        permitOps = new HashSet<>();
        permitOps.add("+");
        permitOps.add("-");
        permitOps.add("*");
        permitOps.add("/");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            char[] inputChars = scanner.nextLine().toCharArray();

            StringBuilder stringBuilder = new StringBuilder();
            for (char aChar : inputChars) {
                if (' ' != aChar) {
                    stringBuilder.append(aChar).append(" ");
                }
            }

            String[] formatInput = stringBuilder.toString().split("\\s");
            try {
                log.debug("" + calc(formatInput));
            } catch (RuntimeException e) {
                log.debug("error");
            }
        }
    }

    public static int calc(String[] formatInput) {
        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();

        Arrays.stream(formatInput).forEach(s -> {
            if ("(".equals(s)) {
            } else if (permitOps.contains(s)) {
                ops.push(s);
            } else if (")".equals(s)) {
                String op = ops.pop();
                Integer valB = vals.pop();

                switch (op) {
                    case "+":
                        vals.push(vals.pop() + valB);
                        break;
                    case "-":
                        vals.push(vals.pop() - valB);
                        break;
                    case "*":
                        vals.push(vals.pop() * valB);
                        break;
                    case "/":
                        if (valB == 0) {
                            throw new RuntimeException();
                        }
                        vals.push(vals.pop() / valB);
                        break;
                    default:
                        throw new RuntimeException();
                }
            } else {
                vals.push(Integer.parseInt(s));
            }
        });

        if (!vals.isEmpty()) {
            return vals.pop();
        } else {
            return 0;
        }
    }
}
