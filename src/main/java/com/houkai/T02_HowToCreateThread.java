package com.houkai;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方式
 * 启动线程的5种方式
 */
public class T02_HowToCreateThread {

    static class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("hello MyRun");
        }
    }
    static class MyThread extends Thread{
        public void run() {
            System.out.println("hello MyThread");
        }
    }
    static class MyCall implements Callable<String>{
         int id=10;
         String name="Hello future";

        public MyCall() {
        }

        public MyCall(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            System.out.println("hello MyCall");
            return "seccess";
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("Hello lambda");
        }).start();
        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCall());
        Thread t = new Thread(stringFutureTask);
        t.start();

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("hello ThreadPool");
        });
        service.shutdown();
    }
}
