package com.houkai.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T14_MyRejectedHandler {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(4, 4, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(), new MyHandler());
        service.execute(()->{
            System.out.println("----------------");
        });
    }
    static  class MyHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //log("r rejected")
            //save r kafka mysql redis
            //try 3 times
            if(executor.getQueue().size() < 10000) {
                System.out.println("rejectedExecution done");
                //try put again();
            }
        }
    }
}
