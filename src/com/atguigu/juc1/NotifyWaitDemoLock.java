package com.atguigu.juc1;

/*
创建两个线程
创建一个初始变量 值是0
实现一个线程对变量+1，另外一个线程对变量-1
交替进行10次

使用lock实现
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyWaitDemoLock {
    public static void main(String[] args) {
        ShareDataOneLock shareDataOne = new ShareDataOneLock();
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
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
class ShareDataOneLock{
    //定义变量初始值是0
    private int number = 0 ;
    //创建可重入锁
    Lock lock = new ReentrantLock();
    //创建condition
    private Condition condition = lock.newCondition();

    //+1
    public  void increment() throws InterruptedException {

        lock.lock();

        try {
            //判断
            while (number != 0){
                //等待
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+" "+ number);
            //通知
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }


    //-1
    public  void decrement() throws InterruptedException {

       lock.lock();


        try {
            //判断
            while(number != 1){
                //等待
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+" "+ number);

            //通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

}
