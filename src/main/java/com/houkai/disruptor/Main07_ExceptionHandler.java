package com.houkai.disruptor;

import com.houkai.util.SleepUtil;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
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
public class Main07_ExceptionHandler {
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
                new SleepingWaitStrategy());//Sleep

       EventHandler h1=(a, b, c)->{
           System.out.println("消费者出现异常");
       };
        //连接处理器  多个消费者消费
        disruptor.handleEventsWith(h1);
        disruptor.handleExceptionsFor(h1).with(new ExceptionHandler<LongEvent>() {

            @Override
            public void handleEventException(Throwable ex, long sequence, LongEvent event) {
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("Excepyion start to handle! ");
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("Excepyion end to handle! ");
            }
        });


        //启动disruptor,启动所有线程
        disruptor.start();

        //从disruptor获取ringbuffer用于发布消息
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final int threadCount=10;
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
                for (int j = 0; j < 10; j++) {
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
