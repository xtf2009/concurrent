package org.xtf2009.concurrent.c_025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 写时复制容器copy on write
 * 每次写入的时候Copy一份新的对象，加上写入的数据，然后把对象的引用改到新对象上
 * 多线程环境下，写时效率低，读时效率高
 * （读的时候不用加锁）
 *
 * 适合写少读多的环境
 */
public class T02_CopyOnWriteList {

    public static void main(String[] args){
        List<String> list =
//                new ArrayList<>();//这个会出并发问题
//                new Vector<>();
                new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);

        for(int i=0; i<ths.length; i++){
            ths[i] = new Thread(()->{
                for(int j=0; j<1000; j++) list.add("a" + r.nextInt(10000));
                latch.countDown();
            });
        }

        long start = System.currentTimeMillis();

        Arrays.asList(ths).forEach(o->o.start());
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(list.size());
    }

}
