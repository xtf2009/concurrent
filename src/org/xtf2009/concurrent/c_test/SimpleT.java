package org.xtf2009.concurrent.c_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class SimpleT {

    AtomicInteger threadCount = new AtomicInteger(0);

    public void m(){
        threadCount.incrementAndGet();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
