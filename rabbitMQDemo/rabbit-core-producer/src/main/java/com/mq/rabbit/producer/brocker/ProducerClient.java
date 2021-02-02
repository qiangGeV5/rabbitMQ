package com.mq.rabbit.producer.brocker;

import com.google.common.base.Preconditions;
import com.mq.rabbit.api.Message;
import com.mq.rabbit.api.MessageProducer;
import com.mq.rabbit.api.MessageType;
import com.mq.rabbit.api.SendCallback;
import com.mq.rabbit.api.exception.MessageRunTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 发送消息的实现类
 */

@Component
public class ProducerClient implements MessageProducer {


    @Autowired
    private RabbitBroker rabbitBroker;

    @Override
    public void send(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message.getTopic());
        String messageType = message.getMessageType();
        switch (messageType){
            case MessageType.RAPID:
                rabbitBroker.rapidSend(message);
                break;
            case MessageType.CONFIRM:
                rabbitBroker.confirmSend(message);
                break;
            case MessageType.RELIANT:
                rabbitBroker.reliantSend(message);
                break;
        default:
            break;
        }


    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {

    }

    @Override
    public void send(Message message, SendCallback sendCallback) {

    }
}
