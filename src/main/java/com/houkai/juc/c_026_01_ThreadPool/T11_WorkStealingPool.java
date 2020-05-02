package com.houkai.juc.c_026_01_ThreadPool;

import com.houkai.util.SleepUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2100));
        service.execute(new R(1200));
        service.execute(new R(4100));
        service.execute(new R(800));

        //由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }
    static class  R implements Runnable{
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            SleepUtil.sleepMillis(time);
            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }
}
