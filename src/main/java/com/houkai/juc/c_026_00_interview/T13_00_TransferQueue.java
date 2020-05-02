package com.houkai.juc.c_026_00_interview;

import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 交叉打印1a2b3c4d5e
 *  TransferQueue<Character> queue = new LinkedTransferQueue<>();
 *  queue.transfer(c);传输到另一个线程
 *  queue.take()获取都是阻塞方法
 */
public class T13_00_TransferQueue {
    static Thread t1,t2;
    public static void main(String[] args) throws IOException {
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};

        TransferQueue<Character> queue = new LinkedTransferQueue<>();

       t1=new Thread(()->{
           for (char c : aI) {
               try {
                   queue.transfer(c);
                   System.out.println(queue.take());
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }


       },"t1");
       t2=new Thread(()->{
           for (char c : aC) {
               try {
                   System.out.println(queue.take());
                   queue.transfer(c);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
