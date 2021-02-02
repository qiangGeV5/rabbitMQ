package com.mq.rabbit.producer.brocker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.mq.rabbit.api.Message;
import com.mq.rabbit.api.MessageType;
import com.mq.rabbit.api.exception.MessageException;
import com.mq.rabbit.api.exception.MessageRunTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * rabbit 池化封装
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback{

    private Map<String/*topic*/, RabbitTemplate> rabbitMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    @Autowired
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {

        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if (rabbitTemplate != null){
            return rabbitTemplate;
        }

        log.info("#RabbitTemplateContainer.getTemplate# topic:{}",topic);
        RabbitTemplate newRabbitTemplate = new RabbitTemplate(connectionFactory);
        newRabbitTemplate.setExchange(topic);
        newRabbitTemplate.setRoutingKey(message.getRoutingKey());
        newRabbitTemplate.setRetryTemplate(new RetryTemplate());

        //message序列化方式
//        newRabbitTemplate.setMessageConverter(messageConverter);

        String messageType = message.getMessageType();
        if (!MessageType.RAPID.equals(messageType)){
            newRabbitTemplate.setConfirmCallback(this);
        }

        rabbitMap.putIfAbsent(topic,newRabbitTemplate);


        return rabbitMap.get(topic);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        //消息应答
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));

        if(b){
            log.info("send message is ok,confirm messageId:{} ,sendTime:{}",messageId,sendTime);
        }else {
            log.error("send message is Fail,confirm messageId:{} ,sendTime:{}",messageId,sendTime);
        }
    }
}
