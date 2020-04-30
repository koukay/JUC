package com.houkai.c_021_01_interview;

import com.houkai.util.SleepUtil;

import java.util.LinkedList;
/**
 * 写一个固定容量同步容器,拥有put()get()方法,以及getCount方法,
 * 能够支持两个生产者线程以及10个消费者线程阻塞调用
 * @param <T>
 */
public class MyContainer1<T> {
    final private LinkedList<T> lists= new LinkedList<>(); //初始化容器
    final private int MAX=10;//最大容量
    private int count=0;

    /**
     * 生产者
     * @param t
     */
     public synchronized void put(T t){
        while (lists.size()==MAX){//用while的目的是要保证list的size不超过最大容量
            try {
                this.wait();//如果size等于最大容量,,生产者等待,通知消费者执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
         lists.add(t);
         ++count;
         this.notifyAll();//通知消费者线程进行消费
     }

    /**
     * 消费者
     * @return
     */
     public synchronized T get(){
        T t= null;
        while (lists.size()==0){//用while的目的是要保证list的size不能为负数
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t=lists.removeFirst();//消费第一个
        count--;
        this.notifyAll();//通知生产者线程进行生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c= new MyContainer1();
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
