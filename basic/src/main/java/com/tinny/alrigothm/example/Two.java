package com.tinny.alrigothm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : Tinny
 * @date : Created on 2020/3/1 - 15:08
 * @description :
 * @modified :
 */
@Slf4j
public class Two {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int person = Integer.parseInt(scanner.nextLine());

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < person; i++) {
            String[] inputs = scanner.nextLine().split(" ");
            students.add(new Student(inputs[0], Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])));
        }

        students.stream()
                .sorted((s1, s2) -> {
                    if (s1.total() != s2.total()) {
                        return Integer.compare(s2.total(), s1.total());
                    } else if (s1.yuWen != s2.yuWen) {
                        return Integer.compare(s2.yuWen, s1.yuWen);
                    } else if (s1.shuXue != s2.shuXue) {
                        return Integer.compare(s2.shuXue, s1.shuXue);
                    } else {
                        return 1;
                    }
                })
                .forEach(s -> System.out.println(s.name + " " + s.yuWen + " " + s.shuXue + " " + s.yingYu));
    }
}

class Student {
    public String name;
    public int yuWen;
    public int shuXue;
    public int yingYu;

    public Student(String name, int yuWen, int shuXue, int yingYu) {
        this.name = name;
        this.yuWen = yuWen;
        this.shuXue = shuXue;
        this.yingYu = yingYu;
    }

    public int total() {
        return yuWen + shuXue + yingYu;
    }
}
