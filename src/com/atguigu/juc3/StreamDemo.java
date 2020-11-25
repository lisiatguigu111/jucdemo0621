package com.atguigu.juc3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stream流
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(1,20,"aa");
        User u2 = new User(2,30,"bb");
        User u3 = new User(3,40,"cc ");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);

        //
        list.stream().filter((p)->{
            return p.getId() % 2 == 0;
        }).filter((p)->{
            return p.getAge()>20;
        }).map((p)->{
            return p.getName().toUpperCase();
        }).limit(1).forEach(System.out::println);

    }
}
