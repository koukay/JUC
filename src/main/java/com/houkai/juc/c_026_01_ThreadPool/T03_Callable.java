package com.houkai.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //callable有返回值
        Callable<String> call= new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "hello collable";
            }
        };
        //创建线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //启动线程
        Future<String> submit = service.submit(call);
        System.out.println(submit.get());
        //关闭线程池
        service.shutdown();
    }
}
