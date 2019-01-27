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
 *
 * 使用ReentrantLock可以“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLock3 {

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

    /**
     * 使用reyLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以根据tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
     */
    void m2(){

//        boolean locked = lock.tryLock();
//        System.out.println("m2..." + locked);
//        if(locked) lock.unlock();

        boolean locked = false;

        try{
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args){
        ReentrantLock3 r3 = new ReentrantLock3();
        new Thread(r3::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(r3::m2).start();
    }

}
