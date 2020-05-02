package com.houkai.juc.c_020_01_interview;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一种会一直卡着,另一个线程拿不到当前线程的变量值
 */
public class T01_withoutVolatile {
    List list = new ArrayList();
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_withoutVolatile c= new T01_withoutVolatile();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+i);
                SleepUtil.sleepSecond(1);
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
