package com.houkai.juc.c_026_00_interview;

/**
 * 交叉打印1a2b3c4d5e
 * 用wait notify方法,要加锁才生效
 */
public class T06_00_sync_wait_notify {
    static Thread t1,t2;
    public static void main(String[] args) {
        final Object o= new Object();
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
       t1=new Thread(()->{
           synchronized (o){
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

       },"t1");
       t2=new Thread(()->{
           synchronized (o){
               for (char c : aC) {
                   System.out.println(c);
                   try {
                       o.notify();
                       o.wait();//让出锁
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               o.notify();//因为t1最后处于等待状态,所以必须释放锁
           }

       },"t2");
       t1.start();
       t2.start();
    }
}
