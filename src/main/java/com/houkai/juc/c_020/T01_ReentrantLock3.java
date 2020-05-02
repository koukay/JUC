package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程加锁,只有当一个执行完之后才能执行另一个,但是可以在一个线程里面调用另一个
 * 使用ReentrantLock也可以实现同样的功能,但是必须手动释放锁
 * ReentrantLock比较强大,可以使用tryLock进行尝试锁定,不管锁定与否,方法继续执行,
 * synchronized如果搞不定就会阻塞,但是用ReentrantLock可以决定到底要不要等待
 */
public class T01_ReentrantLock3 {
    Lock lock= new ReentrantLock();
    void m1(){
        try {
            //加锁
            lock.lock();//synchronized (this)
            for (int i = 0; i < 10; i++) {
                SleepUtil.sleepSecond(1);
                System.out.println(i);
            }
        }finally {
            //finally解锁
            lock.unlock();
        }
    }

    /**
     * ReentrantLock比较强大,可以使用tryLock进行尝试锁定,不管锁定与否,方法继续执行,
     * 可以根据ReentrantLock返回值来判断是否锁定
     * 也可以指定truLock的时间
     */
    void m2(){
        /*SleepUtil.sleep(10);
        boolean locked= lock.tryLock();
        System.out.println("m2..."+locked);
        if (locked)lock.unlock();*/
        boolean locked =false;
        try {
            //尝试获取锁
            locked = this.lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..."+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //finally解锁
            if (locked)lock.unlock();
        }

    }

    public static void main(String[] args) {
        T01_ReentrantLock3 r1= new T01_ReentrantLock3();
        new Thread(r1::m1).start();
        SleepUtil.sleepSecond(1);
        new Thread(r1::m2).start();
    }
}
