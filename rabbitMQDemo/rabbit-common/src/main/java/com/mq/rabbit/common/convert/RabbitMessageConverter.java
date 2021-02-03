package com.mq.rabbit.common.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class RabbitMessageConverter implements MessageConverter {

    private GenericMessageConverter delegate;

    private final String delaultExprie = String.valueOf(24*60*60*1000);

    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter){
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;

    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        messageProperties.setExpiration(delaultExprie);

        return this.delegate.toMessage(o,messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.mq.rabbit.api.Message msg = (com.mq.rabbit.api.Message) this.delegate.fromMessage(message);
        return msg;
    }
}
