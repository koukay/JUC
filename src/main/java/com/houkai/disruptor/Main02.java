package com.houkai.disruptor;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

/**
 * Disruptor内存里用于存放元素的高效率队列
 */
public class Main02 {
    public static void main(String[] args) throws IOException {
        //事件工厂
        LongEventFactory factory= new LongEventFactory();

        //必须是2的乘幂
        int bufferSize=1024;

        //Disruptor构造方法
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(
                factory,
                bufferSize,
                DaemonThreadFactory.INSTANCE);

        //连接处理器
        disruptor.handleEventsWith(new LongEventHandler());
        //启动disruptor,启动所有线程
        disruptor.start();

        //从disruptor获取ringbuffer用于发布消息
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //----------------------------------translator1---------------------------------------------
        /*EventTranslator<LongEvent> translator1= new EventTranslator<LongEvent>() {
            @Override
            public void translateTo(LongEvent event, long sequence) {
                event.setValue(88888L);
            }
        };
        ringBuffer.publishEvent(translator1);*/
        //----------------------------------translator2---------------------------------------------
        /*EventTranslatorOneArg<LongEvent,Long> translator2= new EventTranslatorOneArg<LongEvent,Long>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0) {
                event.setValue(1);
            }
        };
        ringBuffer.publishEvent(translator2,7777L);*/
        //----------------------------------translator3---------------------------------------------
        /*EventTranslatorTwoArg<LongEvent,Long,Long> translator3= new EventTranslatorTwoArg<LongEvent,Long,Long>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0, Long arg1) {
                event.setValue(1);
            }
        };
        ringBuffer.publishEvent(translator3,771L,100L);*/
        //----------------------------------translator4---------------------------------------------
       /* EventTranslatorThreeArg<LongEvent,Long,Long,Long> translator4= new EventTranslatorThreeArg<LongEvent,Long,Long,Long>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Long arg0, Long arg1, Long arg2) {
                event.setValue(arg0+arg1+arg2);
            }

        };
        ringBuffer.publishEvent(translator4,10000L,10000L,10000L);*/
        //----------------------------------translator5---------------------------------------------
        EventTranslatorVararg<LongEvent> translator5= new EventTranslatorVararg<LongEvent>() {

            @Override
            public void translateTo(LongEvent event, long sequence, Object... args) {
                long result=0;
                for (Object o : args) {
                    long l= (long) o;
                    result+=l;
                }
                event.setValue(result);
            }

        };
        ringBuffer.publishEvent(translator5,10000L,10000L,10000L,10000L,10000L,10000L);
        System.in.read();
    }
}
