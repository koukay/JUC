package com.houkai.c_003;

/**
 * 方法加锁
 */
public class T {
    private  int count=10;
    public synchronized void m(){
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

}
