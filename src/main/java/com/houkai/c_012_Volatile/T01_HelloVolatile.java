package com.houkai.c_012_Volatile;

import java.util.concurrent.TimeUnit;

/**
 * volatile
 * 1.保证线程间可见,当前线程修改,其他线程立即生效
 * 2.防止指令重排序,cpu为了优化效率,可能会对指令进行重新排序
 */
public class T01_HelloVolatile {
    /*volatile*/ boolean runing = true;//对比无valatile运行结果的区别
    void m(){
        System.out.println("m start");
        while (runing){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.runing=false;
    }
}
