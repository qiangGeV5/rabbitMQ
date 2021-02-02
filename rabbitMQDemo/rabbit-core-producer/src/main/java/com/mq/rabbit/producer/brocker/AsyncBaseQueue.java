package com.mq.rabbit.producer.brocker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class AsyncBaseQueue {
    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private static ExecutorService senderAsync =
            new ThreadPoolExecutor(
                    THREAD_SIZE,
                    QUEUE_SIZE,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_SIZE), new ThreadFactory() {
                            @Override
                            public Thread newThread(Runnable r) {
                                Thread thread = new Thread();
                                thread.setName("rabbitMq_client_async_sender");
                                return thread;
                            }
                        },
                    new RejectedExecutionHandler() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                            log.error("async sender is error rejected, runnable: {}，executor:{}",r,executor);
                        }
                    });

    public static void submit(Runnable runnable){
        senderAsync.submit(runnable);
    }
}
