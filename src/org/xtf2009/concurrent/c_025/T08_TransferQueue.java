package org.xtf2009.concurrent.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException{
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

//        new Thread(()->{
//           try{
//               System.out.println(strs.take());
//           }catch (InterruptedException e){
//               e.printStackTrace();
//           }
//        }).start();

        strs.transfer("aaa");//找不到消费者时，线程会阻塞

        new Thread(()->{
            try{
                System.out.println(strs.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }

}
