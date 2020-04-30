package com.houkai.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 一个栅栏,循环使用,什么时候人满了,栅栏打开,放大家过关
 * CyclicBarrier循环栅栏,一直监听,到达指定容量,就放行
 * barrier.await();监听是否到达设置的容量20,够20人就发一次车,不到就一直等着
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
//        CyclicBarrier barrier= new CyclicBarrier(20);
//        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));
       CyclicBarrier barrier= new CyclicBarrier(20,new Runnable(){

            @Override
            public void run() {
                System.out.println("满人,发车--"+Thread.currentThread().getName());
            }
        });
        for (int i = 0; i < 105; i++) {
            new Thread(()->{
                try {
//                    System.out.println(Thread.currentThread().getName());
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
