package com.houkai.c_025;

import java.util.Random;
import java.util.concurrent.*;

/**
 * SynchronousQueue容量为0
 * 用于两个线程之间传内容,不是存东西的
 * tasks.take()//阻塞,等待生产者生产
 * tasks.put("aaa");//阻塞,等待消费者消费
 * tasks.add("bbb");//不能add,add会报错
 */
public class T08_SynchronusQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> tasks = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
       /* new Thread(()->{
            try {
                System.out.println(tasks.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println(tasks.take());//阻塞,等待生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
        tasks.put("aaa");//阻塞,等待消费者消费
//        tasks.put("bbb");
//        tasks.add("bbb");
        System.out.println(tasks.size());
    }

}
