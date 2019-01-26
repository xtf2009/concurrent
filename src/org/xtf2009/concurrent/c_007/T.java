package org.xtf2009.concurrent.c_007;

public class T {

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2(){
        System.out.println(Thread.currentThread().getName() + " m2 start...");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }

    /**
     * 问：同步方法与非同步方法是否可以同时调用？
     * 答：同步方法执行过程中，非同步方法是可以调用运行的。（因为只有同步方法运行时才需要申请锁）
     * @param args
     */
    public static void main(String[] args){
        T t = new T();

        new Thread(()->t.m1(),"t1").start();//Lambda表达式，表示new了一个Runnable对象，run方法里执行了t.m1方法
        new Thread(()->t.m2(),"t2").start();

        //两种写法是等价的
//        new Thread(t::m1,"t1").start();
//        new Thread(t::m2,"t2").start();

        /*new Thread(()->t.m1(),"t1").start();
        //等同于
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        },"t1").start();*/
    }
}
