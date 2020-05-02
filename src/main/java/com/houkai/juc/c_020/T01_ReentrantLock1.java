package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

/**
 * 多个线程加锁,只有当一个执行完之后才能执行另一个,但是可以在一个线程里面调用另一个
 */
public class T01_ReentrantLock1 {
    synchronized void m1(){
        for (int i = 0; i < 10; i++) {
            SleepUtil.sleepSecond(1);
            System.out.println(i);
            if (i==2) m2();
        }
    }
    synchronized void m2(){
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 r1= new T01_ReentrantLock1();
        new Thread(r1::m1).start();
        SleepUtil.sleepSecond(1);
        new Thread(r1::m2).start();
    }
}
