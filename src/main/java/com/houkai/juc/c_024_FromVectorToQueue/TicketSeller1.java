package com.houkai.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 有10000张火车票,每张票都有编号
 * 同时有10个窗口对外发售
 * 程序可能会出现的问题
 * 超卖,while (tickets.size()>0){}满足条件,但是同时进来多个,就会超卖
 */
public class TicketSeller1 {
    static List<String> tickets= new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (tickets.size()>0){
                    System.out.println("销售了---- "+tickets.remove(0));
                }
            }).start();
        }
    }
}
