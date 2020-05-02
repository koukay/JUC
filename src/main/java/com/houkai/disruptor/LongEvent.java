package com.houkai.disruptor;

/**
 * 定义Event队列中需要处理的元素
 * Disrupyor数据结构是环形队列
 * 每一个消息都认为是一个事件
 * 所以在环形队列里存的是event
 */
public class LongEvent {
    private long value;

    public LongEvent() {
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
