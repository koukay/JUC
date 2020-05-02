package com.houkai.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 定义Event工厂用于填充队列
 * event产生需要用到event工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
