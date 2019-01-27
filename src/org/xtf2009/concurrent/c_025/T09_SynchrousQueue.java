package org.xtf2009.concurrent.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchrousQueue {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<String> strs = new SynchronousQueue<>();//容量为0

        new Thread(()->{
            try{
                System.out.println(strs.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");//阻塞等待消费者消费
//        strs.add("aaa");//无法add，因为容量为0
        System.out.println(strs.size());
    }

}
