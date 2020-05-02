package com.houkai.juc.c_016_LockOptimization;

import java.util.concurrent.TimeUnit;

public class FineCoarseLock {
    int count =0;
    synchronized void m1(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 "+count);
    }
    void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            count++;
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 "+count);
    }

    public static void main(String[] args) {
        FineCoarseLock  f= new FineCoarseLock();
        for (int i = 0; i < 100; i++) {
            f.m1();
            f.m2();
        }
    }
}
