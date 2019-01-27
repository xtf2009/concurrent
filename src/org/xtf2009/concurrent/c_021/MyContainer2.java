package org.xtf2009.concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程及10个消费者线程的阻塞调用
 *
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition可以更加精确的指定哪些线程被唤醒
 *
 */
public class MyContainer2<T> {

    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t){
        try{
            lock.lock();
            while (list.size() == MAX){
                producer.await();
            }

            list.add(t);
            ++ count;
            consumer.signalAll();//通知消费者线程进行消费
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  T get(){
        T t = null;
        try{
            lock.lock();
            while (list.size() == 0){
                consumer.await();
            }
            t = list.removeFirst();
            count --;
            producer.signalAll();//通知生产者进行生产
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

}
