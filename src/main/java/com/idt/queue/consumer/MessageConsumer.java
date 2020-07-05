package com.idt.queue.consumer;

public interface MessageConsumer {
    //消费者
    public void handleMessage(Object message);
}
