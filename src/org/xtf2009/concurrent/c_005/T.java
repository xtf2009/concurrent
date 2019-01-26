package org.xtf2009.concurrent.c_005;

public class T implements Runnable{

    private int count = 10;

    /**
     * 一个synchronized的代码块是原子操作，不可分
     */
    @Override
    public /*synchronized*/ void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args){
        T t = new T();//不是一个线程一个T，而是多个线程共同访问一个T对象
        for(int i =0; i <5 ; i ++){
            new Thread(t, "THREAD" + i).start();
        }
    }
}
