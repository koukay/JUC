package com.houkai.c_020;
/**
 * 倒计时,什么时候计数完了,门栓打开,程序向下执行
 * CountDownLatch 门栓,设置容量
 * latch.countDown();计数,每个线程每执行一次-1
 * latch.await();达到容量后等待,让latch慢慢-1
 *
 * 线程里面处理业务逻辑,完成之后countDown-1
 */

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {
    public static void main(String[] args) {
//        usingJoin();
        usingCountDownLatch();
    }
    private static void usingCountDownLatch(){
        Thread[] threads= new Thread[10];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                /*int result=0;
                for (int j = 0; j < 100; j++) {
                    result+=j;
                    System.out.println(j);
                }*/
                latch.countDown();
                System.out.println("usingCountDownLatch ---"+latch.getCount());
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }
    private static void usingJoin(){
        Thread[] threads= new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                int result=0;
                for (int j = 0; j < 100; j++) {
                    result+=j;
                    System.out.println(j);
                }
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }
}
