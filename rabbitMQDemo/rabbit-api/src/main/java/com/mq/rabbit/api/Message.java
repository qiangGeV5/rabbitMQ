package com.mq.rabbit.api;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Data
public class Message implements Serializable {

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

    public Message() {
    }

    public Message(String messageId, String topic, String routingKey, Map<String, Object> attributes, int delayMills, String messageType) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delayMills = delayMills;
        this.messageType = messageType;
    }

    public Message(String messageId, String topic, String routingKey, Map<String, Object> attributes, int delayMills) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delayMills = delayMills;
    }
}
