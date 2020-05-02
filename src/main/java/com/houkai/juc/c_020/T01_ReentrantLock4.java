package com.houkai.juc.c_020;

import com.houkai.util.SleepUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个线程加锁,只有当一个执行完之后才能执行另一个,但是可以在一个线程里面调用另一个
 * 使用ReentrantLock也可以实现同样的功能,但是必须手动释放锁
 * ReentrantLock比较强大,可以使用tryLock进行尝试锁定,不管锁定与否,方法继续执行,
 * synchronized如果搞不定就会阻塞,但是用ReentrantLock可以决定到底要不要等待
 */
public class T01_ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock= new ReentrantLock();
        Thread t1 = new Thread(()->{
            try {
                //加锁
                lock.lock();//synchronized (this)
                System.out.println("t1 start");
                SleepUtil.sleepSecond(Integer.MAX_VALUE);
                System.out.println("t1 end");
            }catch (Exception e){
                System.out.println("interrupted!");
            } finally{
                //finally解锁
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            try {
                //加锁
                lock.lockInterruptibly();//可以对interrupt方法做出响应
                System.out.println("t2 start");
                SleepUtil.sleepSecond(5);
                System.out.println("t2 end");
            }catch (InterruptedException e){
                System.out.println("interrupted!");
            } finally{
                //finally解锁
                lock.unlock();
            }
        });
        t2.start();
        SleepUtil.sleepSecond(1);
        t2.interrupt();//打断线程2的等待
    }
}
