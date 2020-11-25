package com.atguigu.juc1;

public class LamDemo {
    public static void main(String[] args) {
        Foo foo = (int x ,int y )->{
            System.out.println("lam test");
            return x+y;
        };
        int add = foo.add(1, 1);
        System.out.println("add = " + add);

        foo.div(2,2);
        Foo.sub(4,1);


    }
}
@FunctionalInterface//函数式接口
interface Foo{
    public int add(int x ,int y );

    //default方法
    default int div(int x,int y) {

        return x/y;
    }
    //静态方法
    public static int sub(int x,int y){
        return x-y;
    }
}