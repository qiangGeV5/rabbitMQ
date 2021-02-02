package com.mq.rabbit.producer.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 */
@Configuration
@ComponentScan({"com.mq.rabbit.producer.*"})
public class RabbitProducerAutoConfiguration {
}
