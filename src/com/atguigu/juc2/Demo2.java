package com.atguigu.juc2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Demo2 {
    public static void main(String[] args) {
        //runnable创建接口
        new Thread(new MyRunnable(), "aa");

        //Callable方式创建线程
        //new FutureTask对象，构造传入的是Callable实现类的对象
        //FutureTask<Integer> futureTask = new FutureTask(new MyCallable());
        //FutureTask是Runnable接口实现类
        // new Thread(futureTask,"bb").start();


        //使用lam表达式简化
        FutureTask<Integer> futureTask1 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"执行了...");
            TimeUnit.SECONDS.sleep(1);
            return 1024;
        });
        FutureTask<Integer> futureTask2 = new FutureTask(()->{
           System.out.println(Thread.currentThread().getName()+"come in callable");
           TimeUnit.SECONDS.sleep(1);
           return 2048;
        });


        new Thread(futureTask1, "A1").start();

        new Thread(futureTask1,"A2").start();

        //判断是否结束
        while (!futureTask1.isDone()){
            System.out.println("wait");
        }

        try {
            //得到返回值
            Integer integer = futureTask1.get();
            System.out.println("integer = " + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {

    }
}
class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"执行了");
        return 200;
    }
}
