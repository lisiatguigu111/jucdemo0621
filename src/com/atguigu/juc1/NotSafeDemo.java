package com.atguigu.juc1;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotSafeDemo {
    public static void main(String[] args) {
        /*线程不安全的
        List<String> list1 = new ArrayList<>();*/
        //常用线程安全的
        //写时复制
        List<String> list1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i <=20 ; i++) {
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list1);
            },String.valueOf(i)).start();
        }
      /*  //线程安全的  但不常用
        List<String> vector = new Vector<>();
        Collections.synchronizedList(new ArrayList<>());*/
    }
}
