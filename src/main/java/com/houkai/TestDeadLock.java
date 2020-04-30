package com.houkai;

/**
 * 死锁,两个锁对象相互有依赖,但是同时又不能获取到,处于等待状态,就会发生死锁
 * 解决办法,锁粒度扩大
 */
public class TestDeadLock implements Runnable {
    public int flag=1;
    static Object o1= new Object();
    static Object o2= new Object();
    /*@Override
    public void run() {
        System.out.println("flag= "+flag);
        if (flag==1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if (flag==0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("1");
                }
            }
        }
    }*/
    @Override
    public synchronized void run() {
        System.out.println("flag= "+flag);
        if (flag==1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    System.out.println("1");
        }
        if (flag==0){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    System.out.println("0");
        }
    }

    public static void main(String[] args) {
        TestDeadLock td1= new TestDeadLock();
        TestDeadLock td2= new TestDeadLock();
        td1.flag=1;
        td2.flag=0;
        new Thread(td1).start();
        new Thread(td2).start();
    }
}
