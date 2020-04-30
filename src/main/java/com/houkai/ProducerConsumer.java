package com.houkai;

/**
 * 生产者消费者
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        SyncStack ss= new SyncStack();
        Producer p=new Producer(ss);
        Consumer c= new Consumer(ss);
        new Thread(p).start();
        new Thread(c).start();
    }
}

/**
 * 生产的对象
 */
class WoTou{
    int id;

    public WoTou(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WoTou{" +
                "id=" + id +
                '}';
    }
}

/**
 * 生产的容器
 */
class SyncStack{
    int index=0;
    WoTou [] arrWT=new WoTou[6];

    /**
     * 生产的动作
     * @param woTou
     */
    public synchronized void push(WoTou woTou){
        while (index==arrWT.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        arrWT[index]=woTou;
        index++;
    }

    /**
     * 消费的动作
     * @return
     */
    public synchronized WoTou pop(){
        while (index==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        return arrWT[index];
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    SyncStack ss=null;

    public Producer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            WoTou woTou= new WoTou(i);
            ss.push(woTou);
            System.out.println("生产了: "+woTou);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费的对象
 */
class Consumer implements Runnable{
    SyncStack ss=null;

    public Consumer(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            WoTou woTou= ss.pop();
            System.out.println("消费了: "+woTou);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}