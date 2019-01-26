package org.xtf2009.concurrent.c_010;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁。也就是说
 * synchronized获得的锁是可以重入的。
 * 这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
public class T {

    synchronized void m(){
        System.out.println("m start");
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args){
        //之类调用父类的同步方法，此时锁定的实际上是一个TT的实例对象
        new TT().m();
    }

}
