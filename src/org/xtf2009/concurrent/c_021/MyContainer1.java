package org.xtf2009.concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 *
 */
public class MyContainer1<T> {

    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    public synchronized void put(T t){
        while (list.size() == MAX){//想想为什么用while而不是用if？
            try{
                /*来自Effective Java
                wait()在99.9%的情况下和while在一起用，而不是和if在一起用
                因为线程被notify唤醒后，会在wait后继续执行，如果使用if做判断，则不会再检查wait的判断条件，这时候别的线程对共享资源做了更改，
                就可能会出现同步异常
                比如这里，如果使用if，则wait()完直接add(t)，如果在这个过程中其他线程已经做个这个操作，就会出现同步异常。*/
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        list.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
    }

    public synchronized T get(){
        T t;
        while (list.size() == 0){
            try{
                this.wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count --;
        this.notifyAll();//通知生产者进行生产
        return t;
    }

    public synchronized int getCount(){
        return count;
    }

    public static void main(String[] args){
        MyContainer1<String> c = new MyContainer1<>();
        //启动消费者线程
        for(int i=0; i<10; i++){
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            },"c" + i).start();
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++){
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
