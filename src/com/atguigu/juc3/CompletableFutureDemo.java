package com.atguigu.juc3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//异步回调
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
    /*    //同步
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture1");
        });
        completableFuture1.get();*/


        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");

            return 1024;
        });

        completableFuture2.whenComplete((t,u)->{
            System.out.println("-------t="+t);
            System.out.println("-------u="+u);
        }).get();

    }
}
