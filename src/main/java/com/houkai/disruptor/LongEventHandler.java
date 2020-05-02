package com.houkai.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * EventHandler(消费者) 处理容器中的元素
 *
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public static long count;
    /**
     *
     * @param event
     * @param sequence ringbuffer的序号
     * @param endOfBatch 是否为最后一个元素
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("["+Thread.currentThread().getName()+"]" +event+"序号: "+sequence);

    }
}
