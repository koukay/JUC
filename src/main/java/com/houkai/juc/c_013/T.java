package com.houkai.juc.c_013;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法加锁
 */
public class T {
    //保证线程间可见性
    volatile int count=100;
    synchronized void m(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.forEach((o)->{
                try {
                    o.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(t.count);
        }
    }
}
