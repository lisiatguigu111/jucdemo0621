package com.atguigu.juc2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class Demo6 {
    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            final int num = i;
            new Thread(()->{
            myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(3);

        for (int i = 1; i <=5 ; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{
    //模拟缓存效果，不断向map放数据。从map获取数据
    private volatile Map<String,Object> map = new HashMap<String,Object>();
    //创建读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //向map放数据的方法
    public void put(String key,Object value){
        //添加写锁
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"正在往缓存写入数据。。。");
            TimeUnit.MILLISECONDS.sleep(200);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"已经写完了数据。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放写锁
            readWriteLock.writeLock().unlock();
        }
    }
    //从map获取数据方法
    public Object get (String key){
        //添加读锁
        readWriteLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName()+"正在从缓存读取数据");
            TimeUnit.MILLISECONDS.sleep(200);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"从缓存中读取到了数据"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }
        return result;
    }
}
