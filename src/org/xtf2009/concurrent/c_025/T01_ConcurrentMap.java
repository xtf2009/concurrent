package org.xtf2009.concurrent.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读ConcurrentSkipListMap
 *
 * ConcurrentHashMap为什么效率比Hashtable高？
 * 因为Hashtable是锁定整个对象；而ConcurrentHashMap把容器分割开（默认16份），分开加锁
 * (1.8的实现已经抛弃了Segment分段锁机制，利用CAS+Synchronized来保证并发更新的安全，底层采用数组+链表+红黑树的存储结构。)
 */
public class T01_ConcurrentMap {

    public static void main(String[] args){

//        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String, String> map = new ConcurrentSkipListMap<>();//高并发并且插入的数据需要排序的时候用（插入时效率降低，查询效率提升）

        Map<String, String> map = new Hashtable<>();//线程安全，默认加锁，目前不常用，因为效率较低
        //Map<String, String> map = new HashMap<>();带锁的实现：//Collections.synchronizedMap();
        //TreeMap 非并发时需要排序的Map（或者SortedMap）

        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();

        for(int i=0; i<ths.length; i++){
            ths[i] = new Thread(()->{
                for(int j=0; j<10000; j++) map.put("a" + r.nextInt(100000),"a" + r.nextInt(100000));
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(o->o.start());
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

}
