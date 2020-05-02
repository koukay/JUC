package com.houkai.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

/**
 * Disruptor内存里用于存放元素的高效率队列
 */
public class Main03 {
    public static void main(String[] args) throws IOException {
        //事件工厂
        LongEventFactory factory= new LongEventFactory();

        //必须是2的乘幂
        int bufferSize=1024;

        //Disruptor构造方法
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                LongEvent::new,
                bufferSize,
                DaemonThreadFactory.INSTANCE);

        //连接处理器
        disruptor.handleEventsWith(((event, sequence, endOfBatch) -> System.out.println("Event: "+event) ));
        //启动disruptor,启动所有线程
        disruptor.start();

        //从disruptor获取ringbuffer用于发布消息
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event,sequence)->event.setValue(10000L));

        System.in.read();
    }
}
