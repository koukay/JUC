package com.houkai.c_025;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 可以实现排序
 * 底层是二叉树
 *a
 * c
 * d
 * v
 * w
 */
public class T07_01_PriorityQueue {

    public static void main(String[] args) throws InterruptedException {
        PriorityQueue<String> tasks = new PriorityQueue();
        tasks.add("a");
        tasks.add("d");
        tasks.add("w");
        tasks.add("c");
        tasks.add("v");
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.poll());
        }
    }


}
