package org.xtf2009.concurrent.c_026;

import java.util.concurrent.Executor;

/**
 * 认识Executor
 */
public class T01_MyExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
//        new Thread(command).start();
        command.run();
    }

    public static void main(String[] args){
        new T01_MyExecutor().execute(()->{
            System.out.println("Hello Executor!");
        });
    }
}
