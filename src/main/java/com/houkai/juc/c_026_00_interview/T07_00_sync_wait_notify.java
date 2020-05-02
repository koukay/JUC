package com.houkai.juc.c_026_00_interview;


public class T07_00_sync_wait_notify {
    private static volatile boolean t2Started=false;
//    private static CountDownLatch latch= new CountDownLatch(1);
    public static void main(String[] args) {
        final Object o= new Object();
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
        new Thread(()->{
           /* try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized (o){
               while (!t2Started){
                   try {
                       o.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               for (char c : aI) {
                   System.out.println(c);
                   try {
                       o.notify();
                       o.wait();//让出锁
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();//因为t2最后处于等待状态,所以必须释放锁
           }

       },"t1").start();
       new Thread(()->{
           synchronized (o){
               for (char c : aC) {
                   System.out.println(c);
//                   latch.countDown();
                   t2Started=true;
                   try {
                       o.notify();
                       o.wait();//让出锁
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();//因为t1最后处于等待状态,所以必须释放锁
           }

       },"t2").start();
    }
}
