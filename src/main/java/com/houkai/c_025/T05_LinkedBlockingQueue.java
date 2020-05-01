package com.houkai.c_025;

import com.houkai.util.SleepUtil;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingDeque有put存数据的方法和take取数据的方法,两个方法都是阻塞方法,读到空会等待,存满了也会等待
 * LinkedBlockingDeque链表实现BlockingDeque,是无界序列,可以装到你内存满为止
 * strs.put("a"+i);//如果满了就会等待
 * strs.take() 如果空了就会等待
 */
public class T05_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingDeque<>();
    static Random r = new Random();
    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a"+i);//如果满了就会等待
                    SleepUtil.sleepSecond(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take - "+strs.take());
                        //strs.take() 如果空了就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }
    }
}
