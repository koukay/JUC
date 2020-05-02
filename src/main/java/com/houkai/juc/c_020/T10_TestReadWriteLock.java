package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁,即共享锁和排他锁
 * readLock共享锁
 * writeLock排它锁
 */
public class T10_TestReadWriteLock {
    static Lock lock= new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock readLock=readWriteLock.readLock();
    static Lock writeLock=readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            SleepUtil.sleepSecond(1);
            System.out.println("read over!");
            //模拟读取操作
        }finally {
            lock.unlock();
        }
    }
    public static void write(Lock lock,int v){
        try {
            lock.lock();
            SleepUtil.sleepSecond(1);
            value=v;
            System.out.println("write over!");
            //模拟写操作
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        doReadWriteLock();//三秒执行完读写
        doReentrantLock();//20秒才能执行完,读的时候是独占锁,必须得等待
    }

    private static void doReentrantLock() {
        Runnable readR = () -> read(lock);
        Runnable writeR = () -> write(lock, new Random().nextInt());
        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }
    private static void doReadWriteLock() {
        Runnable readR = () -> read(readLock);
        Runnable writeR = () -> write(writeLock, new Random().nextInt());
        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }

}
