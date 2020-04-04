package com.tinny.alrigothm.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/1 - 20:40
 * @description :
 * @modified :
 */
public class Four {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            List<Integer> nums = new ArrayList<>();
            String s = scanner.nextLine();
            for (int i = 0; i < s.length(); i++) {
                nums.add(s.charAt(i) - '0');
            }

            int length = nums.size();

            if (length != 2 && length != 5 && length != 8 && length != 11 && length != 14) {
                System.out.println("no");
                return;
            }

            if (isFinish(nums)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    public static boolean isFinish(List<Integer> list) {
        if (list.size() == 2) {
            return list.get(0).equals(list.get(1));
        }

        List<DThree> three = findThree(list);
        if (three.isEmpty()) {
            return false;
        }

        for (DThree d : three) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (i != d.a && i != d.b && i != d.c) {
                    temp.add(list.get(i));
                }
            }
            if (isFinish(temp)) {
                return true;
            }
        }

        return false;
    }

    public static List<DThree> findThree(List<Integer> list) {
        List<DThree> result = new ArrayList<>();

        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    Integer li = list.get(i);
                    Integer lj = list.get(j);
                    Integer lk = list.get(k);

                    if (li.equals(lj) && li.equals(lk)) {
                        result.add(new DThree(i, j, k));
                    }
                    if ((lk - lj == 1) && (lj - li == 1)) {
                        result.add(new DThree(i, j, k));
                    }
                }
            }
        }

        return result;
    }


    static class DThree {
        public int a;
        public int b;
        public int c;

        public DThree(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}

