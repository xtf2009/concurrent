package org.xtf2009.concurrent.c_026;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for(int i=0; i<2; i++){
            service.execute(()->{
                try{
                    TimeUnit.MILLISECONDS.sleep(501);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        //60s后闲置的线程会被销毁
        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);

        service.shutdown();
    }

}
