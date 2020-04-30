package com.houkai.c_001;

/**
 * new一个对象加锁
 */
public class T {
    private  int count=10;
    private  Object o=new Object();
    public  void m(){
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }

}
