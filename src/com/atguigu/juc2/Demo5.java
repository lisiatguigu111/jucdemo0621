package com.atguigu.juc2;

//JUC辅助类 Semaphore演示  信号灯

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 六辆车抢三个车位
 */
public class Demo5 {
    public static void main(String[] args) {
        //三个车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    //抢到
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"号车抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+"号车离开了车位!!!!!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
