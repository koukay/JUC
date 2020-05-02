package com.houkai.juc.c_026_00_interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 交叉打印1a2b3c4d5e
 * Lock lock = new ReentrantLock();
 * Condition condition = lock.newCondition();
 *conditionT1.await(); t1等待
 *conditionT2.signal();唤醒t2
 */
public class T09_00_lock_condition {
    static Thread t1,t2;
    public static void main(String[] args) {
        char[] aI={'1','2','3','4','5','6'};
        char[] aC={'a','b','c','d','e','f'};
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();
       t1=new Thread(()->{
           try {
               lock.lock();
               for (char c : aI) {
                   System.out.println(c);
                   conditionT1.await();
                   conditionT2.signal();
               }

           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }


       },"t1");
       t2=new Thread(()->{
           try {
               lock.lock();
               for (char c : aC) {
                   System.out.println(c);
                   conditionT1.signal();
                   conditionT2.await();
               }
            } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }
       },"t2");
       t1.start();
       t2.start();
    }
}
