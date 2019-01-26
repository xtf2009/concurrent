package org.xtf2009.concurrent.c_012;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程之间可见
 * A B线程都用到同一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程也未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从堆内存读到t1的工作区，在运行过程中直接使用这个copy，并不会每次都去读取堆内存，这样，当
 * 主线程修改running的值之后，t1线程也感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * (加了volatile，主内存数据修改时会通知所有线程：其线程缓冲区的数据已经过期，需要重新来主内存读取刷新)
 *
 * 要保证线程之间的可见性，就需要对线程间共同访问的变量加volatile。
 *
 * 不用volatile就只能加synchronized,而会使执行效率大幅降低，能用volatile的时候就不要加锁，程序并发性会提高很多。
 *
 * 可以阅读这篇文章进行更深入的理解
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *
 * synchronized既有可见性，又有原子性
 * volatile只是保证可见性
 */
public class T {

    volatile boolean running = true;//对比一下有无volatile的情况下，整个程序运行结果的区别

    void m(){
        System.out.println("m start");
        while (running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args){
        T t = new T();

        new Thread(t::m,"t1").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        t.running = false;
    }

}
