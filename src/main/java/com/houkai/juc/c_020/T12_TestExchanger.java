package com.houkai.juc.c_020;

import java.util.concurrent.Exchanger;

/**
 * 交换器,两个线程之间交换数据
 * t1 T2
 * t2 T1
 */
public class T12_TestExchanger {
    static  Exchanger<String> exchanger=new Exchanger();

    public static void main(String[] args) {
        new Thread(()->{
            String s="T1";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t1").start();
        new Thread(()->{
            String s="T2";
            try {
                s=exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t2").start();
    }
}
