package org.xtf2009.concurrent.c_004;

public class T {

    private static int count = 10;

    //synchronized用在静态方法上，相当于锁定的是T.class
    //T.class相当于Class类的一个对象
    public synchronized static void m(){ //这里等同于synchronized(org.xtf2009.concurrent.c_004.T.class)
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm(){
        synchronized(T.class){//静态方法无法synchronized(this)
            count --;
        }
    }

}
