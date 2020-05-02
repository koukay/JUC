package com.houkai.juc.c_006;

/**
 * 方法加锁
 */
public class T implements Runnable{
    //保证线程间可见性
    private int count=100;

    /**
     * synchronized既保证原子性又保证线程间可见性
     */
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+" count = "+count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t,"THREAD "+i).start();
        }
    }
}
