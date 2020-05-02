package com.houkai.juc.c_026_00_interview;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 交叉打印1a2b3c4d5e
 * ArrayBlockingQueue多线程阻塞操作
 * q1.put("ok"); q1放入一个值
 * q2.take();   开始q2没放,所以t1阻塞,值到t2打印之后放入值
 *
 * q1.take(); q1刚开始没放,所以t2阻塞,当q1放入时执行打印
 * q2.put("ok");q2放入一个值
 */
public class T04_00_BlockingQueue {
    static BlockingQueue<String> q1= new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> q2= new ArrayBlockingQueue<>(1);
    static Thread t1,t2;
    public static void main(String[] args) {
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
       t1=new Thread(()->{
           for (char c : aI) {
               System.out.println(c);
               try {
                   q1.put("ok");
                   q2.take();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }


       },"t1");
       t2=new Thread(()->{
           for (char c : aC) {

               try {
                   q1.take();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(c);
               try {
                   q2.put("ok");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
