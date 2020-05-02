package com.houkai.juc.c_008;

import java.util.concurrent.TimeUnit;

/**
 * 模拟银行账户
 * 对写业务加锁
 * 对读业务不加锁  读取的是脏数据,不准确
 *
 * 读写都加锁,读取的才是真数据
 */
public class Account {
    String name;
    double balance;


    public synchronized void setName(String name,double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->a.setName("houkai",10000)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("houkai"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("houkai"));
    }
}
