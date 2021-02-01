package com.rabbit.producer.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){

        /**
         *
         * @param correlationData 作为唯一标识
         * @param b ack 是否落盘成功
         * @param s 失败的异常信息
//         */
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {

            System.err.println("消息ack结果"+b+","+correlationData);

        }
    };

    /**
     * 对外发送方法
     * @param message 消息体
     * @param properties 额外的附加属性
     * @throws Exception
     */
    public void send(Object message, Map<String, Object> properties)throws Exception{

        MessageHeaders mhs = new MessageHeaders(properties);
        Message<Object> msg = MessageBuilder.createMessage(message, mhs);

        rabbitTemplate.setConfirmCallback(confirmCallback);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message) throws AmqpException {
                System.err.println("--> post to do :" + message);
                return message;
            }
        };

        rabbitTemplate.convertAndSend("exchange-1",
                "springboot.rabbit",
                msg,
                messagePostProcessor
                ,
                correlationData);
    }
}
