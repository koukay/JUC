package com.houkai.c_018_Atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Atomic: 100000000 time 1717
 * doLock: 100000000 time 3332
 * LongAdder: 100000000 time 832
 * 三种比较,LongAdder最快,Atomic次之,加锁最慢
 * 加锁有可能去操作系统申请重量级锁,所以效率偏低
 * Atomic底层原理是CAS
 * LongAdder内部是分段锁,分段执行最后汇总,所以效率高
 *
 */
public class T02_AtomicVsSyncVsLongAdder {
    static AtomicLong count1= new AtomicLong();
    static long count2=0L;
    static LongAdder count3= new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[]threads= new Thread[1000];
        //-------------------AtomicLong--------------------------------------
        doAtomic(threads);
        //-------------------long加锁--------------------------------------
        doLock(threads);
        //-------------------LongAdder--------------------------------------
        doLongAdder(threads);
    }

    private static void doLongAdder(Thread[] threads) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("LongAdder: "+count3.longValue()+" time "+(end-start));
    }

    private static void doLock(Thread[] threads) throws InterruptedException {
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < 100000; j++) {
                                synchronized (lock){
                                    count2++;
                                }
                            }
                        }
                    }
            );
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("doLock: "+count2+" time "+(end-start));
    }

    private static void doAtomic(Thread[] threads) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i]= new Thread(()-> {
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Atomic: "+count1.get()+" time "+(end-start));
    }
}
