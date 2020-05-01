package com.houkai.c_025;

import com.houkai.util.SleepUtil;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 面试常问:Queue和list区别在哪里
 * queue添加了offer,peek,poll,take,put这些对线程友好的方法即等待或阻塞方法
 * ArrayBlockingQueue
 */
public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            try {
                strs.put("a"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        strs.put("aaa");//满了就会等待,程序阻塞
//        strs.add("aaa");
//        strs.offer("aaa");
//        strs.offer("aaa",11, TimeUnit.SECONDS);
        System.out.println(strs);
    }
}
