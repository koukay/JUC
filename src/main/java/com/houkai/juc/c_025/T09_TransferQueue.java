package com.houkai.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue传递多个,是之前多个queue的组合
 */
public class T09_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> tasks = new LinkedTransferQueue<>();
        new Thread(()->{
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        tasks.transfer("aaa");
        tasks.put("123");
        tasks.put("1q3");
        tasks.put("1e3");
        new Thread(()->{
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
