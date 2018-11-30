package com.mavs.notificationservice.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@EnableRabbit
@Configuration
@RabbitListener(queues = "USER_QUEUE")
public class MessageQueueConsumer {

    @RabbitHandler
    public void onReceive(@Payload String message) {
        log.warn("************************");
        log.warn("----: {}", message);

    }
}
