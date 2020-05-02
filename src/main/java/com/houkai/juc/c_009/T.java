package com.houkai.juc.c_009;

/**
 *
 */
public class T {

    /**
     * synchronized是可重入的,调用m2执行完毕还会回到m1
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName()+" m1 start ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println(Thread.currentThread().getName()+" m1 end ");
    }
    public void m2(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2  ");
    }

    public static void main(String[] args) {
        T t = new T();
        t.m1();
    }
}
