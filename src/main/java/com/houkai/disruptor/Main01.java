package com.houkai.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * Disruptor内存里用于存放元素的高效率队列
 */
public class Main01 {
    public static void main(String[] args) {
        //事件工厂
        LongEventFactory factory= new LongEventFactory();

        //必须是2的乘幂
        int bufferSize=1024;

        //Disruptor构造方法
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                bufferSize,
                Executors.defaultThreadFactory());

        //连接处理器
        disruptor.handleEventsWith(new LongEventHandler());
        //启动disruptor,启动所有线程
        disruptor.start();

        //从disruptor获取ringbuffer用于发布消息
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //找到下一个可用的位置
        long sequence = ringBuffer.next();
        try {

            //拿到event实例
            LongEvent longEvent = ringBuffer.get(sequence);
            //设置值
            longEvent.setValue(8888L);
        }finally {
            //发布生产
            ringBuffer.publish(sequence);
        }
    }
}
