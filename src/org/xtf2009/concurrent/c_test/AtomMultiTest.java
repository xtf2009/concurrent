package org.xtf2009.concurrent.c_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序证明AtomXXX类的多个方法并不构成原子性
 */
public class AtomMultiTest {

    public static void main(String[] args){
        AtomNumber atomNumber = new AtomNumber();

        CountDownLatch latch = new CountDownLatch(5);

        //起5个线程，每个线程累加10次，每次加2，则理论上来讲最后结果是100
        for(int i=0; i<5; i++){
            new Thread(()->{
                for(int j=0; j < 1000; j++){
                    atomNumber.inc();
                }
                latch.countDown();
            }).start();
        }

        try{
            latch.await();
            System.out.println("Final number:" + atomNumber.get());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

class AtomNumber{

    AtomicInteger num = new AtomicInteger(0);

    public void inc(){
        num.incrementAndGet();
        int temp = num.get();
        try {
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //在线程睡眠的这29毫秒里，如果num被别的线程修改了，则下一次的累加不会执行
        if(temp == num.get()){
            num.incrementAndGet();
        }
    }

    public int get(){
        return num.get();
    }



}
