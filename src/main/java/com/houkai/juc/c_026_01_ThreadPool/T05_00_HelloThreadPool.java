package com.houkai.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的7个参数
 * 核心线程数
 * 最大线程数
 * 生存时间
 * 生存时间的单位
 * 任务队列,最多可以装4个任务
 * 线程工厂,可以指定线程名称
 * 拒绝策略,四种:
 * abort,抛异常
 * discard,扔掉,不抛异常
 * discardOldest,扔掉排队时间最久的
 * callerRuns调用者处理服务
 */
public class T05_00_HelloThreadPool {
    static class Task implements  Runnable{
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Task "+i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }

        /**
         * 线程池的7个参数
         * 核心线程数
         * 最大线程数
         * 生存时间
         * 生存时间的单位
         * 任务队列,最多可以装4个任务
         * 线程工厂,可以指定线程名称
         * 拒绝策略,四种:
         * abort,抛异常
         * discard,扔掉,不抛异常
         * discardOldest,扔掉排队时间最久的
         * callerRuns调用者处理服务
         * @param args
         */
        public static void main(String[] args) {
            ThreadPoolExecutor tpe=new ThreadPoolExecutor(
                    2,
             4,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(4),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy());
            for (int i = 0; i < 8; i++) {
                tpe.execute(new Task(i));
            }
            System.out.println(tpe.getQueue());
            tpe.execute(new Task(100));
            System.out.println(tpe.getQueue());
            tpe.shutdown();


        }
    }
}
