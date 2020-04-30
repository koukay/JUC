package com.houkai.c_010;

/**
 *
 */
public class T {

    /**
     * synchronized是可重入的,调用m2执行完毕还会回到m1
     */
      synchronized void m1() {
        System.out.println(Thread.currentThread().getName()+" m1 start ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" m1 end ");
    }

    public static void main(String[] args) {
         new TT().m1();
    }
}

/**
 * 父类synchronized ,子类调用父类的时候必须得可重入,即必须是同一把锁
 */
class TT extends T{
      void m1() {
        System.out.println("child TT start");
        super.m1();
        System.out.println("child TT end");
    }
}
