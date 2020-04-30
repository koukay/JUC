package com.houkai.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 *双重locksupport
 * t2!=5一直处于等待状态
 * t1=5的时候暂停并唤醒t2,t2执行完之后唤醒t1
 */
public class T07_LockSupport_withoutSleep {
    static  Thread t2=null;
    static  Thread t1=null;

    volatile List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        T07_LockSupport_withoutSleep c= new T07_LockSupport_withoutSleep();
        CountDownLatch latch= new CountDownLatch(1);
        t2=new Thread(()->{
                System.out.println("t2 启动");
                    if (c.size()!=5){
                        LockSupport.park();
                    }
                    System.out.println("t2 结束");
                    LockSupport.unpark(t1);
        },"t2");
        SleepUtil.sleepSecond(1);
        t1=new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                    if (c.size()==5){
                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }
                }
        },"t1");
        t2.start();
        t1.start();

    }
}
