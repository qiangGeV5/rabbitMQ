package com.rabbit.consumer.component;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceive {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",declare = "true"),
            exchange = @Exchange(name = "exchange-1",
            durable = "true",
            type = "topic",
            ignoreDeclarationExceptions = "true"),
            key = "springboot.*"

    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{

        //	1. 收到消息以后进行业务端消费处理
        System.err.println("-----------------------");
        System.err.println("消费消息:" + message.getPayload());

        //  2. 处理成功之后 获取deliveryTag 并进行手工的ACK操作, 因为我们配置文件里配置的是 手工签收
        //	spring.rabbitmq.listener.simple.acknowledge-mode=manual
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);

    }
}
