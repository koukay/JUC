package com.houkai.c_004;

/**
 * 方法加锁
 */
public class T {
    private static int count=10;
    public synchronized void m(){
            count--;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }
    public void mm(){
        synchronized (T.class){
            count--;
        }
    }

}
