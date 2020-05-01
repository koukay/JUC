package com.houkai.c_023_02_FromHashtableToCHM;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Collections.synchronizedMap(new HashMap<>());让不安全的类变得安全
 * 100个线程,100个线程去取key value数据,一个线程取10000个,总共1000000个,100个线程,每个线程往里面插1万个
 */
public class T03_TestSynchronizedHashMap {
    static Map<UUID,UUID> m= Collections.synchronizedMap(new HashMap<>());
    static int count=Constants.COUNT;
    static UUID[] keys=new UUID[count];
    static UUID[] values=new UUID[count];
    static final int THREAD_COUNT= Constants.THREAD_COUNT;
    static {
        for (int i = 0; i < count; i++) {
            keys[i]=UUID.randomUUID();
            values[i]=UUID.randomUUID();

        }
    }
    static class MyThread extends Thread{
        int start;
        int gap=count/THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run(){
            for (int i = start; i < start+gap; i++) {
                m.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //100个线程数组
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            //启动100个线程,每个线程设置一个起始值,每个线程操作一定数量的数(100),然后将数放入hashtable中
            threads[i]=
                    new MyThread(i*(count/THREAD_COUNT));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                //线程依次执行,保证同时是一个线程在执行
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(m.size());

        //-----------------------------------
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
