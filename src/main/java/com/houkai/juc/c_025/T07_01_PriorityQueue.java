package com.houkai.juc.c_025;

import java.util.PriorityQueue;

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
