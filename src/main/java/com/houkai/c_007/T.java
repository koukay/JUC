package com.houkai.c_007;

/**
 *
 */
public class T {

    /**
     * synchronized既保证原子性又保证线程间可见性
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName()+" m1 start ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end ");
    }
    public void m2(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m2  ");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();
    }
}
