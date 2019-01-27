package org.xtf2009.concurrent.c_022;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量
 */
public class ThreadLocal1 {

    volatile static Person person = new Person();

    public static void main(String[] args){

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(person.name);
        }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            person.name = "lisi";
        }).start();

    }

}