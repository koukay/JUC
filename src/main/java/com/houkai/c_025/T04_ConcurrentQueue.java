package com.houkai.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue
 * strs.offer("a"+i);//add() 添加值
 * strs.poll() //拿到并并移除第一个值
 * strs.peek() //拿到第一个值
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            strs.offer("a"+i);//add() 添加值
        }
        System.out.println(strs);
        System.out.println(strs.size());

        System.out.println(strs.poll());//拿到并弹出第一个值
        System.out.println(strs.size());

        System.out.println(strs.peek());//拿到第一个值
        System.out.println(strs.size());
    }
}
