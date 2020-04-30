package com.houkai.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 让t2先启动,加锁等待
 * t1不断添加,当size=5的时候唤醒t2
 * 可是notify不释放锁,所以t1还是会执行
 */
public class T03_NotifyHoldingLock {
    volatile List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        T03_NotifyHoldingLock c= new T03_NotifyHoldingLock();
        final Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2 启动");
                    if (c.size()!=5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("t2 结束");
            }
        },"t2").start();
        SleepUtil.sleepSecond(1);
        new Thread(()->{
            synchronized (lock){
                System.out.println("t1 启动");
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                    if (c.size()==5){
                        lock.notify();
                    }
                    SleepUtil.sleepMillis(100);
                }
                System.out.println("t1 结束");
            }
        },"t1").start();

    }
}
