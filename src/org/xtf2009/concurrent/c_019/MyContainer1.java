package org.xtf2009.concurrent.c_019;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 曾经的面试题：
 * 实现一个容器，提供两个方法，add,size
 * 写两个线程，线程1添加10各元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 分析下面这个程序，能完成这个功能吗？
 */
public class MyContainer1 {

    //添加volatile使线程2可以收到通知
    volatile List list = new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        MyContainer1 c = new MyContainer1();

        new Thread(()->{
            for(int i=0; i<10; i++){
                c.add(new Object());
                System.out.println("add " + i);
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                //两个问题：
                //没有同步，可能线程1加到第6个了才会break，不是很精确
                //线程2的死循环很浪费CPU
                if(c.size() == 5){
                    break;
                }
            }
            System.out.println("t2结束");
        },"t2").start();
    }

}
