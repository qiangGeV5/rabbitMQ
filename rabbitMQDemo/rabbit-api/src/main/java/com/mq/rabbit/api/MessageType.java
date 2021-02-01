package com.mq.rabbit.api;

public class MessageType {

    /**
     * 迅速消息：不需要保障消息的可靠性，也不需要做comfire确认
     */
    public static final String RAPID = "0";

    /**
     * 确认消息：不需要保障消息的可靠性，但是会做confirm确认
     */
    public static final String CONFIRM = "1";

    /**
     * 可靠消息：一定保证消息的100%可靠性投递，不允许有任何消息丢失
     * ps：保证数据库和所发出的消息是原子性的（最终一致）
     */
    public static final String RELIANT = "2";


}

