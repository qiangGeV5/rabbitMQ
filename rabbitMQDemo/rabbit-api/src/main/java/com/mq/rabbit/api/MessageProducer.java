package com.mq.rabbit.api;

import com.mq.rabbit.api.exception.MessageRunTimeException;

import java.util.List;

public interface MessageProducer {

    /**
     * 消息发送 附带发送回调
     * @param message
     * @param sendCallback
     */
    void send(Message message,SendCallback sendCallback);

    /**
     * 消息发送
     * @param message
     * @throws MessageRunTimeException
     */
    void send(Message message)throws MessageRunTimeException;

    /**
     * 消息批量发送
     * @param messages
     * @throws MessageRunTimeException
     */
    void send(List<Message> messages) throws MessageRunTimeException;
}
