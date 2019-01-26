package org.xtf2009.concurrent.c_002;

public class T {

    private int count = 10;

    public void m(){
        //synchronized锁定的是一个对象，而不是代码块
        synchronized (this){ //任何线程要执行下面的代码，必须先拿到this的锁
            count --;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
