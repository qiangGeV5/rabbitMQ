package com.mq.rabbit.api;

/**
 * 消费者监听消息
 */
public interface MessageListener {

    void  onMessage(Message message);
}
