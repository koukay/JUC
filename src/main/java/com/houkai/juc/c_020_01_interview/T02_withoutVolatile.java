package com.houkai.juc.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 第二种,不加睡眠的话也是运行不成功的
 * 加了睡眠相当于给另一个线程有时间去获取size=5并停止线程2
 */
public class T02_withoutVolatile {
    volatile List list = new ArrayList();
//    volatile List list = Collections.synchronizedList(new ArrayList<>());
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }
    public static void main(String[] args) {
        T02_withoutVolatile c= new T02_withoutVolatile();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+i);
                SleepUtil.sleepMillis(100);
            }
        },"t1").start();
        new Thread(()->{
            while (true){
                if (c.size()==5){
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();
    }
}
