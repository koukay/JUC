package com.houkai.c_020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock指定为公平锁,也可以不指定即为非公平锁
 * ReentrantLock可以替代synchronized,他也可以重入可以锁定,本身底层是cas的
 * tryLock自己来控制我锁不住该怎么办
 * lockInterruptibly,这个类你中间还可以被打断
 */
public class T01_ReentrantLock5 extends Thread{
    private  static ReentrantLock lock= new ReentrantLock(true);
    //指定为true表示为公平锁,所以两个线程交替执行,如果不指定,则一个线程执行完之后再执行另一个
    public void run(){
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+" 获得锁");
            }finally {
                //解锁
                lock.unlock();
            }

        }
    }
    public static void main(String[] args) {
        T01_ReentrantLock5 r1= new T01_ReentrantLock5();
        new Thread(r1,"one").start();
        new Thread(r1,"two").start();
    }
}
