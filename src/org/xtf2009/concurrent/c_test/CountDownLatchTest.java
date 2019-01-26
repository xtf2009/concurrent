package org.xtf2009.concurrent.c_test;

import java.util.concurrent.CountDownLatch;

/**
 * 写一个程序，在main线程中启动100个线程，100个线程完成后，主线程打印“完成”，使用join()和CountDownLatch都可以完成
 * 这个是使用join方法的方式
 */
public class CountDownLatchTest {

    public static void main(String[] args){
        SimpleT simpleT = new SimpleT();

        CountDownLatch latch = new CountDownLatch(100);

        for(int i=0; i<100; i ++){
            new Thread(()->{
                simpleT.m();
                latch.countDown();
            }).start();
        }

        try{
            latch.await();
            System.out.println("启动了共" + simpleT.threadCount.get() + "个线程");
            System.out.println("完成");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
