package org.xtf2009.concurrent.c_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序，证明AtomXXX类比synchronized更高效
 */
public class AtomSyncTest {

    public static void main(String[] args){

        CountDownLatch latch = new CountDownLatch(10);

        long startTime = System.currentTimeMillis();
        System.out.println("AtomThreads Start!");
        for(int i = 0;  i < 10;i ++){
            new Thread(()-> {
                while (AtomT.get() < 100000000){
                    AtomT.inc();
                }
                latch.countDown();
            }).start();
        }

        try{
            latch.await();
            long endTime = System.currentTimeMillis();
            System.out.println("AtomThreads End,count = " + AtomT.t.toString());
            System.out.println("Atom cost time:" + (endTime - startTime));
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        CountDownLatch latch2 = new CountDownLatch(10);
        startTime = System.currentTimeMillis();
        System.out.println("SyncThreads Start!");
        for(int i = 0;  i < 10;i ++){
            new Thread(()-> {
                while (SyncT.get() < 100000000){
                    SyncT.inc();
                }
                latch2.countDown();
            }).start();
        }

        try{
            latch2.await();
            long endTime = System.currentTimeMillis();
            System.out.println("SyncThreads End,count = " + SyncT.t);
            System.out.println("Sync cost time:" + (endTime - startTime));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
     }

}

class AtomT {

    public static AtomicInteger t = new AtomicInteger(0);

    public static void inc(){
        t.incrementAndGet();
    }

    public static int get(){
        return t.get();
    }

}

class SyncT {

    public static int t = 0;

    public static synchronized  void inc(){
        t++;
    }

    public  static int get(){
        return t;
    }

}
