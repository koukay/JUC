package com.houkai.juc.c_026_01_ThreadPool;


import com.houkai.util.SleepUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class T06_00_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            SleepUtil.sleepMillis(500);
            return 1000;
        });
        new Thread(task).start();
        System.out.println(task.get());
    }

}
