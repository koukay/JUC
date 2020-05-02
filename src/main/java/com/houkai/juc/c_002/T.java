package com.houkai.juc.c_002;

/**
 * 当前对象加锁
 */
public class T {
    private  int count=10;
    public  void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }

}
