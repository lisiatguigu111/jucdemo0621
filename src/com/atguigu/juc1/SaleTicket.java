package com.atguigu.juc1;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建票的资源类
class Ticket{
    //定义票数
    private int num = 30;

    //买票的方法
//    public synchronized void sale(){
//        //获取当前线程名称
//        if(num > 0 ){
//            System.out.println(Thread.currentThread().getName()+"卖出"+(num--)+"剩下"+num);
//        }
//
//    }
    //lock实现买票的方法
    public void sale(){
        //创建可重入锁
        Lock lock = new ReentrantLock();
        //上锁
        lock.lock();


        //解锁
        try {
            //获取当前线程名称
        if(num > 0 ){
            System.out.println(Thread.currentThread().getName()+"卖出"+(num--)+"剩下"+num);
        }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

//3个售票员卖30张票
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //使用lam表达式创建线程
       // new Thread(()->{for (int i = 1; i <= 40; i++) ticket.sale(); },"WW");


        //创建三个售票员（线程）
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        },"BB").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        },"CC").start();
    }
}
