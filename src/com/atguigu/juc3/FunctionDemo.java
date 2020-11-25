package com.atguigu.juc3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionDemo {
    public static void main(String[] args) {
       /* //消费型
        Consumer<String> consumer = (t)->{
            System.out.println(t);
            };
        consumer.accept("abcd");
*/
/*
        //供给型
        Supplier<String> supplier =()->{
            return "www";
        };
        String s = supplier.get();
        System.out.println("s = " + s);*/

        //函数型
/*        Function<String,String> function =(t)->{
            System.out.println(t);
            return "aaaa";
        };
        String apply = function.apply("0621");
        System.out.println("apply = " + apply);*/

        //断言型
        Predicate<String> predicate = (t)->{
            System.out.println("t = " + t);
            return false;
        };
        boolean test = predicate.test("123");
        System.out.println("test = " + test);
    }
}
