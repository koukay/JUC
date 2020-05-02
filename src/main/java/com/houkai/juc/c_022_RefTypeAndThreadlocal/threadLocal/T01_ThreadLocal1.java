package com.houkai.juc.c_022_RefTypeAndThreadlocal.threadLocal;

import com.houkai.util.SleepUtil;

/**
 *linda
 * 线程之间发生了相互修改的问题,解决方案用threadlocal
 */
public class T01_ThreadLocal1 {
    volatile static Person p= new Person();

    public static void main(String[] args) {
        new Thread(()->{
                SleepUtil.sleepSecond(2);
                System.out.println(p.name);
        }).start();
        new Thread(()->{
                SleepUtil.sleepSecond(1);
                p.name="linda";
        }).start();
    }
}
class Person{
    String name="houkai";
}