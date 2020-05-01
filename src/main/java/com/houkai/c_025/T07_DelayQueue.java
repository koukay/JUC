package com.houkai.c_025;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 可以实现时间上排序
 *
 */
public class T07_DelayQueue {
    static BlockingQueue<MyTask> tasks = new DelayQueue();
    static Random r = new Random();
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        MyTask t1= new MyTask("t1", start+1000);
        MyTask t2= new MyTask("t2", start+2000);
        MyTask t3= new MyTask("t3", start+100);
        MyTask t4= new MyTask("t4", start+1500);
        MyTask t5= new MyTask("t5", start+4000);
        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }

    static class MyTask implements Delayed{
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS );
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "runningTime=" + runningTime +
                    '}';
        }
    }
}
