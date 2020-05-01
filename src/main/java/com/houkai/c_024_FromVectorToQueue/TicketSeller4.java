package com.houkai.c_024_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 有1000张火车票,每张票都有编号
 * 同时有10个窗口对外发售
 * 程序可能会出现的问题
 * ConcurrentLinkedDeque主要是为高并发多线程设计的
 */
public class TicketSeller4 {
    static Queue<String> tickets= new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if (s==null)break;
                    System.out.println("销售了=== "+s);

                }
            }).start();
        }
    }
}
