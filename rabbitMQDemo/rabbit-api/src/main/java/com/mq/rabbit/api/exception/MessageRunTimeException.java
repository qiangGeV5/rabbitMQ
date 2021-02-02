package com.mq.rabbit.api.exception;

public class MessageRunTimeException extends RuntimeException {

    public MessageRunTimeException(){
        super();
    }

    public MessageRunTimeException(String message){
        super(message);
    }

    public MessageRunTimeException(Throwable cause){
        super(cause);
    }

    public MessageRunTimeException(String message, Throwable cause){
        super(message,cause);
    }
}
