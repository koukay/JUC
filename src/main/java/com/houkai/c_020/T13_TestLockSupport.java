package com.houkai.c_020;

import com.houkai.util.SleepUtil;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park();暂停
 * LockSupport.unpark(t);取消暂停,可以先于park之前执行
 */
public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i==5){
                    LockSupport.park();
                }
                SleepUtil.sleepSecond(1);
            }
        });
        t.start();

//        SleepUtil.sleepSecond(8);
        LockSupport.unpark(t);
    }
}
