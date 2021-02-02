package com.mq.rabbit.producer.brocker;

import com.mq.rabbit.api.Message;
import com.mq.rabbit.api.MessageProducer;
import com.mq.rabbit.api.SendCallback;
import com.mq.rabbit.api.exception.MessageRunTimeException;

import java.util.List;

public class ProducerClient implements MessageProducer {
    @Override
    public void send(Message message, SendCallback sendCallback) {

    }

    @Override
    public void send(Message message) throws MessageRunTimeException {

    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {

    }
}
