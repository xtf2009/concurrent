package org.xtf2009.concurrent.c_010;

public class TT extends T {

    @Override
    synchronized void m() {
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
