package com.houkai.disruptor;

import com.houkai.util.SleepUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Disruptor内存里用于存放元素的高效率队列
 */
public class Main04_ProducerType {
    public static void main(String[] args) throws IOException {
        //事件工厂
        LongEventFactory factory= new LongEventFactory();

        //必须是2的乘幂
        int bufferSize=1024;

        //Disruptor构造方法  ProducerType有SINGLE和MULTI两种 指在单线程还是多线程下产生sequence
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,//用single相当于颞部对于序列访问没有加锁,会把消息覆盖,导致数据不准确
                new BlockingWaitStrategy());

        //连接处理器
        disruptor.handleEventsWith(new  LongEventHandler());
        //启动disruptor,启动所有线程
        disruptor.start();

        //从disruptor获取ringbuffer用于发布消息
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final int threadCount=50;
        CyclicBarrier barrier= new CyclicBarrier(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final  long threadNum=i;
            service.submit(()->{
                System.out.printf("Thread  %s ready to start! \n",threadNum);

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    ringBuffer.publishEvent((event,sequence)->{
                        event.setValue(threadNum);
                        System.out.println("生产了: "+threadNum);
                    });
                }

            });
        }
        service.shutdown();
        SleepUtil.sleepSecond(3);
        System.out.println(LongEventHandler.count);

    }
}
