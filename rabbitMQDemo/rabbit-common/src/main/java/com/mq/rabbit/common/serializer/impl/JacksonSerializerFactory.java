package com.mq.rabbit.common.serializer.impl;

import com.mq.rabbit.api.Message;
import com.mq.rabbit.common.serializer.Serializer;
import com.mq.rabbit.common.serializer.SerializerFactory;

public class JacksonSerializerFactory implements SerializerFactory {

    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
