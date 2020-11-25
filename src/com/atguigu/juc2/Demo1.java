package com.atguigu.juc2;

import java.util.concurrent.TimeUnit;

/**
 * 锁的八种情况演示
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {

        phone phone1 = new phone();
        phone phone2 = new phone();
        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        Thread.sleep(200);
        new Thread(()->{
            phone2.sendEmail();
        },"BB").start();

//        new Thread(()->{
//            phone1.sendWX();
//        },"CC").start();
    }
}

class phone{
    //发短信
    public static synchronized void sendSMS() throws Exception{
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("-------------SMS");
    }

    //发邮件
    public  synchronized void sendEmail() {
        System.out.println("-------------Email");
    }

    //普通的方法
    public void sendWX() {
        System.out.println("-------------WX");
    }
}
