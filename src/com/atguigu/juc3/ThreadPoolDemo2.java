package com.atguigu.juc3;

import java.util.concurrent.*;

//自定义线程池创建方式
public class ThreadPoolDemo2 {
    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(
        2,
        5,
        3L,
        TimeUnit .SECONDS,
        new ArrayBlockingQueue<Runnable>(3),
       Executors.defaultThreadFactory(),
       new ThreadPoolExecutor.DiscardPolicy());
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
