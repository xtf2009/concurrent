package org.xtf2009.concurrent.c_003;

public class T {

    private int count = 10;

    public synchronized void m(){//等同于在方法的代码执行时要synchronized (this)
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

}
