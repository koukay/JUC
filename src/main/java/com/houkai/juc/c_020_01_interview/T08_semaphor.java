package com.houkai.juc.c_020_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class T08_semaphor {
    static  Thread t2=null;
    static  Thread t1=null;

    volatile List list = new ArrayList();
    public void add (Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        T08_semaphor c= new T08_semaphor();
        Semaphore s= new Semaphore(1);

        t1=new Thread(()->{
            try {
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1");
        t2=new Thread(()->{
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t2");
        t1.start();

    }
}
