package com.houkai.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 让t2先启动,加锁等待
 * t1不断添加,当size=5的时候唤醒t2
 * t1判断size==5,等待,唤醒t2,t2执行完之后唤醒t1,让t1继续执行
 */
public class T04_NotifyFreeLock {
    volatile List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        T04_NotifyFreeLock c= new T04_NotifyFreeLock();
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
                    //通知t1继续执行
                    lock.notify();
            }
        },"t2").start();
        SleepUtil.sleepSecond(1);
        new Thread(()->{
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                    if (c.size()==5){
                        lock.notify();
                        //释放锁,让t2执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SleepUtil.sleepMillis(100);
                }
            }
        },"t1").start();

    }
}
