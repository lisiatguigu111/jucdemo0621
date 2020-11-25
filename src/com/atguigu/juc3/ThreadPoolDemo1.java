package com.atguigu.juc3;

import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池三种创建方式
public class ThreadPoolDemo1 {
    public static void main(String[] args) {

        //一池N线程
       // ExecutorService executorService = Executors.newFixedThreadPool(3);
        //一池一线程
       // ExecutorService executorService = Executors.newSingleThreadExecutor();
        //一池可扩容线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            //从线程池获取线程
            for (int i = 1; i <=10 ; i++) {
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
