package com.houkai.juc.c_025;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 读比较多,写比较少的情况下用CopyOnWriteArrayList
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists=
                new CopyOnWriteArrayList<>();
//        new Vector<>();
//        new ArrayList<>();//会有并发问题
        Random r= new Random();
        Thread ths[]= new Thread[100];

        for (int i = 0; i < ths.length; i++) {
            Runnable task= new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a"+r.nextInt(100000));
                    }
                }
            };
            ths[i]=new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths){
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
