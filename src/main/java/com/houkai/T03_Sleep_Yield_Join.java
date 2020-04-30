package com.houkai;

/**
 * 线程的三个方法
 * Sleep
 * Yield
 * Join
 */
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    /**
     * 当前线程暂停一段时间,让别的线程执行,时间到了自动复活
     */
    static void testSleep(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("testSleep "+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 让出一下cup资源
     */
    static void testYield(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("testYield A "+i);
                if (i%10==0) Thread.yield();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("testYield B "+i);
                if (i%10==0) Thread.yield();
            }
        }).start();
    }

    /**
     * 让出cpu资源给指定线程,当其执行完毕我再执行
     */
    static void testJoin(){
        Thread t1=new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("testJoin A "+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("testJoin B " + i);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();
    }
}
