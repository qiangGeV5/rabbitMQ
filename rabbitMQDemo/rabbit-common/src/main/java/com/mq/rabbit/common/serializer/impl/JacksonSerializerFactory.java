package com.mq.rabbit.common.serializer.impl;

import com.mq.rabbit.api.Message;
import com.mq.rabbit.common.serializer.Serializer;
import com.mq.rabbit.common.serializer.SerializerFactory;

public class JacksonSerializerFactory implements SerializerFactory {
    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
