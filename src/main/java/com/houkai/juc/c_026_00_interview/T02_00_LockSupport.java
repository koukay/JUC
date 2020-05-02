package com.houkai.juc.c_026_00_interview;

import java.util.concurrent.locks.LockSupport;

/**
 * 交叉打印1a2b3c4d5e
 * 用lockSupport park和unpark方法
 */
public class T02_00_LockSupport {
    static Thread t1,t2;
    public static void main(String[] args) {
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
       t1=new Thread(()->{
           for (char c : aI) {
               System.out.println(c);
               LockSupport.unpark(t2);//叫醒t2
               LockSupport.park();//阻塞
           }
       },"t1");
       t2=new Thread(()->{
           for (char c : aC) {
               LockSupport.park();//阻塞
               System.out.println(c);
               LockSupport.unpark(t1);//叫醒t1
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
