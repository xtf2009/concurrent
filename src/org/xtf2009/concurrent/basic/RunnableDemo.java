package org.xtf2009.concurrent.basic;

public class RunnableDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("I am running in Thread:" + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Start main in Thread:"+ Thread.currentThread().getName());
        Thread t = new Thread(new RunnableDemo());//创建一个Thread类，传入RunnableDemo的实例
        t.start();
        System.out.println("End main in Thread:"+ Thread.currentThread().getName());
    }
}
