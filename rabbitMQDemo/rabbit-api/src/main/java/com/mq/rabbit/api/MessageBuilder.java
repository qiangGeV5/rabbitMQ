package com.mq.rabbit.api;

import com.mq.rabbit.api.exception.MessageRunTimeException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  构建者模式
 */
public class MessageBuilder {

    /**
     * 消息唯一id
     */
    private String messageId;

    /**
     * 消息主题
     */
    private String topic;

    /**
     * *.#消息路由规则
     */
    private String routingKey = "";

    /**
     * 消息附加属性
     */
    private Map<String,Object> attributes = new HashMap<>();

    /**
     * 延迟消息的参数配置
     */
    private int delayMills;

    /**
     * 消息类型:默认是confirm模式
     */
    private String messageType = MessageType.CONFIRM;

    private MessageBuilder(){

    }

    public static MessageBuilder create(){
        return new MessageBuilder();
    }

    public  MessageBuilder withMessageId(String messageId){
        this.messageId = messageId;
        return this;
    }

    public  MessageBuilder withTopic(String topic){
        this.topic = topic;
        return this;
    }

    public  MessageBuilder withRoutingKey(String routingKey){
        this.routingKey = routingKey;
        return this;
    }

    public  MessageBuilder withAttributes(Map<String,Object> attributes){
        this.attributes = attributes;
        return this;
    }

    public  MessageBuilder withAttribute(String key,Object value){
        this.attributes.put(key,value);
        return this;
    }

    public  MessageBuilder withDelayMills(int delayMills){
        this.delayMills = delayMills;
        return this;
    }

    public  MessageBuilder withMessageType(String messageType){
        this.messageType = messageType;
        return this;
    }

    public Message build(){
        if(StringUtils.isBlank(messageId)){
            messageId = UUID.randomUUID().toString();
        }

        if(StringUtils.isBlank(topic)){

            throw new MessageRunTimeException("this topic is null");
        }

        Message message = new Message(messageId, topic, routingKey, attributes, delayMills, messageType);
        return message;
    }

}
