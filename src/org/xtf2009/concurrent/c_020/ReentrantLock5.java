package org.xtf2009.concurrent.c_020;

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
 *
 * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被打断
 *
 * ReentrantLock还可以指定为公平锁
 * 公平锁：等待时间最长的线程获得锁
 * 竞争锁：锁释放后，下一个获取到锁的线程是完全随机的，因此又叫非公平锁（JVM默认调度方式）
 * 竞争锁效率比公平锁高，因为调度器不需要检查线程的等待时间
 */

public class ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);//参数为true表示为公平锁，请对比输出结果

    @Override
    public void run() {
        for(int i=0; i<100;i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        ReentrantLock5 r5 = new ReentrantLock5();
        Thread th1 = new Thread(r5);
        Thread th2 = new Thread(r5);
        th1.start();
        th2.start();
    }
}
