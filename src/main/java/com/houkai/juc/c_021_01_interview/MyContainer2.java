package com.houkai.juc.c_021_01_interview;

import com.houkai.util.SleepUtil;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器,拥有put()get()方法,以及getCount方法,
 * 能够支持两个生产者线程以及10个消费者线程阻塞调用
 * @param <T>
 */
public class MyContainer2<T> {
    final private LinkedList<T> lists= new LinkedList<>(); //初始化容器
    final private int MAX=10;//最大容量
    private int count=0;//表示当前容量

    private Lock lock= new ReentrantLock();
    private Condition producer=lock.newCondition();
    private Condition consumer=lock.newCondition();

    /**
     * 生产者
     * @param t
     */
     public void put(T t){
            try {
                lock.lock();
                while (lists.size()==MAX){//用while的目的是要保证list的size不能为负数
                    producer.await();//生产者等待
                }
                lists.add(t);
                ++count;
                consumer.signalAll();//通知消费者线程进行消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
     }

    /**
     * 消费者
     * @return
     */
     public T get(){
        T t= null;

            try {
                lock.lock();
                while (lists.size()==0) {//用while的目的是要保证list的size不能为负数
                    consumer.await();
                }
                t=lists.removeFirst();//消费第一个
                count--;
                producer.signalAll();//通知生产者线程进行生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c= new MyContainer2();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }
        SleepUtil.sleepSecond(2);
        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }
}
