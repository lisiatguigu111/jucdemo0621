package com.atguigu.juc2;

import java.util.concurrent.CountDownLatch;

//演示JUC辅助类  countDownLatch 减少技术
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"号同学离开了");
                    //值-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t****** 班长关门走人，main线程是班长");

    }
}
