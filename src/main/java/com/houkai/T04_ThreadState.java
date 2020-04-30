package com.houkai;

/**
 * 线程的状态
 * new
 * runnable(ready,running)
 * runnable(waitting) wait,join,LockSupport.park()
 * runnable(timedWaitting)sleep,wait(time),join(time)
 * runnable(block)同步代码块下没得到锁就是阻塞状态
 * terminated
 */
public class T04_ThreadState {
    static class MyThread extends Thread{
        public void run() {
            System.out.println("1  "+this.getState());//RUNNABLE
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(5);
                    System.out.println("2  "+this.getState());//RUNNABLE
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3  "+this.getState());//RUNNABLE
            }
            System.out.println("4  "+this.getState());//RUNNABLE
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        System.out.println("5  "+t.getState());//NEW
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("6  "+t.getState());//TERMINATED
    }
}
