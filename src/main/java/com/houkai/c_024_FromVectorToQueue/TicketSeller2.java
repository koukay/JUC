package com.houkai.c_024_FromVectorToQueue;

import com.houkai.util.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 有1000张火车票,每张票都有编号
 * 同时有10个窗口对外发售
 * 程序可能会出现的问题
 * 超卖,while (tickets.size()>0){}满足条件,但是同时进来多个,就会超卖
 */
public class TicketSeller2 {
    static Vector<String> tickets= new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //虽然Vector的tickets.size()和tickets.remove(0)都是加锁的,但是调用了两个原子方法,所以也会出问题
                while (tickets.size()>0){
                    SleepUtil.sleepMillis(10);
                    System.out.println("销售了---- "+tickets.remove(0));
                }
            }).start();
        }
    }
}
