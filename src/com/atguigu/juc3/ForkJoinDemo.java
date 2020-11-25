package com.atguigu.juc3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTaskDemo myTaskDemo = new MyTaskDemo(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTaskDemo);
        System.out.println(forkJoinTask.get());

        forkJoinPool.shutdown();

    }
}
//创建类  继承RecursiveTask类
class MyTaskDemo extends RecursiveTask<Integer>{

    //两个进行相加的数字差不超过10
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    //创建有参构造
        public MyTaskDemo(int begin, int end){
                this.begin=begin;
                this.end=end;
        }


    @Override
    protected Integer compute() {
            //把两个值相减，作比较，是否小于等于10
            if((end-begin)<= ADJUST_VALUE){
                //直接相加
                //11+12+13+..+20
                for (int i = begin; i <= end ; i++) {
                    result += i;
                }
            }else {
                //继续拆分
                int mid = (begin+end)/2;
                //二分查找  左边
                MyTaskDemo myTaskDemo1 = new MyTaskDemo(begin,mid);
                //右边
                MyTaskDemo myTaskDemo2 = new MyTaskDemo(mid+1,end);
               //创建分支
                myTaskDemo1.fork();
                myTaskDemo2.fork();
                //合并
                result = myTaskDemo1.join()+myTaskDemo2.join();
            }
        return result;
    }
}
