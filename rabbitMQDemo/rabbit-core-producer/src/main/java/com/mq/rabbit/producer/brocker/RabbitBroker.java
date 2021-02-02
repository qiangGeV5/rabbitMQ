package com.mq.rabbit.producer.brocker;

import com.mq.rabbit.api.Message;

/**
 * 具体发送不同种类的接口
 */
public interface RabbitBroker {

    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);

    void sendMessages();

}
