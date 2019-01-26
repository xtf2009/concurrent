package org.xtf2009.concurrent.c_001;

public class T {

    private int count = 10;
    private Object o = new Object();//指向了堆内存的一个对象

    public void m(){
        //一个线程拿到锁则其他线程无法拿到锁，因此叫做互斥锁
        synchronized (o){
            //任何线程要执行下面的代码，必须先拿到o的锁
            count --;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
