package com.mq.rabbit.producer.brocker;

import com.mq.rabbit.api.Message;
import com.mq.rabbit.api.MessageType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送消息实现类
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    private RabbitTemplateContainer rabbitTemplateContainer;

    /**
     * 发送迅速消息
     * @param message
     */
    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSend(Message message) {

    }

    @Override
    public void sendMessages() {

    }

    /**
     * 发送消息的核心方法
     * @param message
     */
    private void sendKernel(Message message) {

        AsyncBaseQueue.submit((Runnable) () -> {
            CorrelationData correlationData = new CorrelationData(String.format("%s#%s",
                    message.getMessageId(), System.currentTimeMillis()));
            String routingKey = message.getRoutingKey();
            String topic = message.getTopic();
            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
            rabbitTemplate.convertAndSend(topic,routingKey,message,correlationData);
            log.error("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId:{}",message.getMessageId());
        });


    }
}
