package com.atguigu.juc1;
//两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z,
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//要求用线程间通信
public class TestThread {
        // 测试
        public static void main(String[] args) {
            ShareResourceTest shareResourceTest = new ShareResourceTest();
            new Thread(()->{
                shareResourceTest.setNumber();
            },"A").start();
            new Thread(()->{
                shareResourceTest.printLetter();
            },"B").start();
        }
    }

class ShareResourceTest {
    private int number = 1;
    Lock lock = new ReentrantLock();
    //创建condition
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    public void setNumber() {
        lock.lock();
        try {
            for (int i = 1; i < 53; i++) {
                while (number % 3 == 0) {
                    try {
                        c1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "线程" + i);
                number++;
                c2.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    public void printLetter(){
        lock.lock();
        try {
            for (char i = 'A'; i <= 'Z'; i++) {
                //打印字母的方法
                while (number%3!=0){
                    try {
                        c2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName()+"线程"+i);//改条件,唤醒下一个线程
                number++;
                c1.signal();

            }
        } finally {
            lock.unlock();
        }
    }
}
