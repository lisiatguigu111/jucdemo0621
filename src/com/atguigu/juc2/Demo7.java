package com.atguigu.juc2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 */
public class Demo7 {
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);

       /* //第一组 add放 remove取
       //add放元素如果超出队列的最大值，会抛出异常
       //remove获取队列元素，获取不到也会抛出异常
        //放
        System.out.println( blockingQueue.add("a"));
        System.out.println( blockingQueue.add("b"));
        System.out.println( blockingQueue.add("c"));
        //System.out.println( blockingQueue.add("d"));

        //取
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
*/
      /*  //第二种 offer poll
        //offer超过队列大小或返回false
        //poll获取队列元素，获取不到会返回null
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        //取
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null
*/

    /*  //第三种 put  take
    //向队列里面放元素，如果队列满了，阻塞状态
    //从队列获取元素，如果获取不到也是阻塞状态
       blockingQueue.put("a");
       blockingQueue.put("b");
       blockingQueue.put("c");
       blockingQueue.put("x");

       //取
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        */


    //第四种
        //向队列里面放元素，如果队列满了，阻塞状态，可以设置超时时间
        //从队列获取元素，如果获取不到也是阻塞状态
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d",3L, TimeUnit.SECONDS));


    }
}
