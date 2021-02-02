package com.mq.rabbit.api;

/**
 * 回调消息
 */
public interface SendCallback {

    void onSuccess();

    void onFailure();
}
