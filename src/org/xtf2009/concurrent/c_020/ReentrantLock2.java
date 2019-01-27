package org.xtf2009.concurrent.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用ReentrantLock可以完成同样的功能
 * 需要注意的是，必须必须必须要手动释放锁（重要的事情说三遍）
 * 使用sync锁定，遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此常在finally中进行锁的释放
 */
public class ReentrantLock2 {

    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();//相当于synchronized(this)
            for(int i=0; i<10; i++){
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        System.out.println("m2...");
        lock.unlock();
    }

    public static void main(String[] args){
        ReentrantLock2 r2 = new ReentrantLock2();
        new Thread(r2::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(r2::m2).start();
    }
}
