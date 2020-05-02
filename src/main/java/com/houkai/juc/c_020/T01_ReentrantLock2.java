package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程加锁,只有当一个执行完之后才能执行另一个,但是可以在一个线程里面调用另一个
 * 使用ReentrantLock也可以实现同样的功能,但是必须手动释放锁
 */
public class T01_ReentrantLock2 {
    Lock lock= new ReentrantLock();
    void m1(){
        try {

            lock.lock();//synchronized (this)
            for (int i = 0; i < 10; i++) {
                SleepUtil.sleepSecond(1);
                System.out.println(i);
            }
        }finally {
            lock.unlock();
        }
    }
    void m2(){
        try {
            lock.lock();
            System.out.println("m2...");
        }finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        T01_ReentrantLock2 r1= new T01_ReentrantLock2();
        new Thread(r1::m1).start();
        SleepUtil.sleepSecond(1);
        new Thread(r1::m2).start();
    }
}
