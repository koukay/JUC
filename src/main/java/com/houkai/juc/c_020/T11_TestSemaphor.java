package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.concurrent.Semaphore;

/**
 * 限流用的
 * Semaphore s= new Semaphore(2); 允许两个线程同时执行
 * Semaphore s= new Semaphore(1); 允许1个线程同时执行
 * Semaphore s= new Semaphore(2,true);true设置公平
 */
public class T11_TestSemaphor {
    public static void main(String[] args) {
        int a=15;
        Semaphore s= new Semaphore(3,true);

        for (int i = 0; i <a ; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName()+" start running...");
                    SleepUtil.sleepMillis(200);
                    System.out.println(Thread.currentThread().getName()+" end running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            }).start();
        }
        /*new Thread(()->{
            try {
                s.acquire();
                System.out.println("t1 running...");
                SleepUtil.sleepMillis(2000);
                System.out.println("t1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                s.release();
            }
        }).start();
        new Thread(()->{
            try {
                s.acquire();
                System.out.println("t2 running...");
                SleepUtil.sleepMillis(2000);
                System.out.println("t2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
