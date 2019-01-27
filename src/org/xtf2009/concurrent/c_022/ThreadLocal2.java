package org.xtf2009.concurrent.c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，Synchronized是使用时间换空间
 * 比如在Hibernate中session就是存在于ThreadLocal中，避免Synchronized的使用
 *
 * 运行下面的程序，理解ThreadLocal
 */
public class ThreadLocal2 {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args){

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();

    }

}
