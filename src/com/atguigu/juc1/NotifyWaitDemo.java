package com.atguigu.juc1;

/*
创建两个线程
创建一个初始变量 值是0
实现一个线程对变量+1，另外一个线程对变量-1
交替进行10次
 */

public class NotifyWaitDemo {
    public static void main(String[] args) {
        ShareDataOne shareDataOne = new ShareDataOne();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();


        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}

// 资源类和操作方法     判断 干活  通知
//资源类
class ShareDataOne {
    //定义变量初始值是0
    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0) {
            //等待
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + " " + number);
        //通知
        this.notifyAll();
    }


    //-1
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (number != 1) {
            //等待
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + " " + number);

        //通知
        this.notifyAll();
        }

}
