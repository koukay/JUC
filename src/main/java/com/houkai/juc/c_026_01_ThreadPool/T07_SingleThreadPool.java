package com.houkai.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个单线程的线程池
 * 保证任务顺序执行
 */
public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService s = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j=i;
            s.execute(()->{
                System.out.println(j+Thread.currentThread().getName());
            });
        }
    }
}
