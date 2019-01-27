package org.xtf2009.concurrent.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞式队列实现生产者/消费者模式
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException{
        for(int i=0; i<10; i++){
            strs.put("a" + i);
        }

        strs.put("aaa");//满了就会等待，程序阻塞
//        strs.add("aaa");
//        strs.offer("aaa");//offer不报异常，只返回boolean值告知加入是否成功
//        strs.offer("aaa", 1 , TimeUnit.SECONDS);

        System.out.println(strs);
    }

}
