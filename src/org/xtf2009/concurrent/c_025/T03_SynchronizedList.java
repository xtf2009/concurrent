package org.xtf2009.concurrent.c_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T03_SynchronizedList {

    public static void main(String[] args){
        List<String> strs = new ArrayList<>();
        List<String> strsSync = Collections.synchronizedList(strs);//返回的是一个加了锁的List
    }

}
