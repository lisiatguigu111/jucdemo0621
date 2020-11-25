package com.atguigu.juc1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按照顺序调用，实现 AA-BB-CC
 *
 * AA打印1-5  BB打印1-10  CC打印1-15
 *
 * 指定10轮
 */
class ThreadOrderDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print5(i);
            }
        },"print5").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print10(i);
            }
        },"print10").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                shareResource.print15(i);
            }
        },"print15").start();
    }
}

class ShareResource {
    //定义标记位
    private int number = 1; //1代表AA 2代表BB 3代表CC
    //创建可重入锁
    Lock lock = new ReentrantLock();
    //创建condition
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    //AA使用
    public void print5(int totalLoopNumber) {
        lock.lock();

        try {
            //判断
           while (number != 1){
               //等待
               c1.await();
           }
            //干活  打印1-5
            for (int i = 1; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+": :" + i+"::"+ "totalLoopNumber"+totalLoopNumber);
            }
            //修改标记位
            number = 2;
            //通知
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //BB使用
    public void print10(int totalLoopNumber){
        lock.lock();

        try {
           //判断
            while(number != 2){
                //等待
                c2.await();
            }
            //干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+": :" + i+"::"+ "totalLoopNumber"+totalLoopNumber);
            }
            //修改标记位
            number = 3;
            //通知
            c3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void print15(int totalLoopNumber){
        lock.lock();


        try {
            while (number != 3){
                c3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+": :" + i+"::"+ "totalLoopNumber"+totalLoopNumber);
            }
            number=1;
            c1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

