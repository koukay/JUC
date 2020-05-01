package com.houkai.c_024_FromVectorToQueue;

import com.houkai.util.SleepUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * 有1000张火车票,每张票都有编号
 * 同时有10个窗口对外发售
 * 程序可能会出现的问题
 * 超卖,对车票集合加锁,可以解决超卖问题,但是效率不高
 */
public class TicketSeller3 {
    static List<String> tickets= new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    synchronized (tickets){
                        if (tickets.size()<=0)break;
//                        SleepUtil.sleepMillis(10);
                        System.out.println("销售了---- "+tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
