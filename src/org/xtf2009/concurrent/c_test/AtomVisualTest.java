package org.xtf2009.concurrent.c_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomXXX类可以保证可见性吗？请写一个程序来证明
 */
public class AtomVisualTest {

    AtomicBoolean running = new AtomicBoolean(false);

    void m(){
        System.out.println("m start");
        while (running.get()){

        }
        System.out.println("m end");
    }

    public static void main(String[] args){
        AtomVisualTest test = new AtomVisualTest();

        new Thread(test::m,"test").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        test.running.set(false);
    }
}
