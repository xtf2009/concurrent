package org.xtf2009.concurrent.basic;

import java.util.concurrent.*;

public class CallableDemo{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        System.out.println(Thread.currentThread().getName() + " created a future");
        Future future = executor.submit(() -> Thread.currentThread().getName() + " callable returned");
        String str = (String)future.get();
        System.out.println(Thread.currentThread().getName() + " get the return value:" +str);
    }
}
