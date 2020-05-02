package com.houkai.juc.c_026_01_ThreadPool;

import com.houkai.util.SleepUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个缓存线程池
 */
public class T08_CachedPool {
    public static void main(String[] args) {
        ExecutorService s = Executors.newCachedThreadPool();
        System.out.println(s);
        for (int i = 0; i < 2; i++) {
            s.execute(()->{
                SleepUtil.sleepMillis(500);
                System.out.println( Thread.currentThread().getName());
            });
            System.out.println(s);
            SleepUtil.sleepMillis(80);
            System.out.println(s);
        }
    }
}
