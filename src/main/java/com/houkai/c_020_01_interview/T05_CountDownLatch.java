package com.houkai.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 让t2先启动,加锁等待
 * t1不断添加,当size=5的时候唤醒t2
 * t1判断size==5,等待,唤醒t2,t2执行完之后唤醒t1,让t1继续执行
 */
public class T05_CountDownLatch {
    volatile List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        T05_CountDownLatch c= new T05_CountDownLatch();
        CountDownLatch latch= new CountDownLatch(1);
        new Thread(()->{
                System.out.println("t2 启动");
                    if (c.size()!=5){
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("t2 结束");
        },"t2").start();
        SleepUtil.sleepSecond(1);
        new Thread(()->{
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                    if (c.size()==5){
                        latch.countDown();
                        //释放锁,让t2执行
                        try {
                            latch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SleepUtil.sleepMillis(100);
                }
        },"t1").start();

    }
}
