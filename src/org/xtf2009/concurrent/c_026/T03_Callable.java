package org.xtf2009.concurrent.c_026;

import java.util.concurrent.Callable;

/**
 * Callable和Runnable都是设计出来被线程调用的
 * 但是Runnable的run方法是没有返回值的，而且不能抛出异常
 * 而Callable可以
 */
public class T03_Callable implements Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }
}
