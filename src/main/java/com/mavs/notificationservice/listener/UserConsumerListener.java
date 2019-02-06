package com.mavs.notificationservice.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavs.activity.dto.ActivityDto;
import com.mavs.activity.dto.ActivityUserDto;
import com.mavs.activity.service.ActivityService;
import com.mavs.activity.util.ActivityUtil;
import com.mavs.notificationservice.event.UserRegisteredEvent;
import com.mavs.notificationservice.model.NotificationActivity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@EnableRabbit
@Configuration
@RabbitListener(queues = "USER_QUEUE")
public class UserConsumerListener {

    private final ActivityService activityService;
    private final ApplicationEventPublisher eventPublisher;

    public UserConsumerListener(ActivityService activityService, ApplicationEventPublisher eventPublisher) {
        this.activityService = activityService;
        this.eventPublisher = eventPublisher;
    }

    @RabbitHandler
    public void onReceive(@Payload String jsonObject) {
        transformJsonObject(jsonObject).ifPresent(activityDto ->
                ActivityUtil.convertToActivity(activityDto).ifPresent(activity -> {
                    activityService.save(new NotificationActivity(activity));
                    eventPublisher.publishEvent(new UserRegisteredEvent(activity));
                }));
    }

    private Optional<ActivityDto<ActivityUserDto>> transformJsonObject(String jsonObject) {
        try {
            ActivityDto<ActivityUserDto> activityDto = new ObjectMapper().readValue(jsonObject,
                    new TypeReference<ActivityDto<ActivityUserDto>>() {
                    });
            log.info("Activity object comes to Notification Service: {}", activityDto);
            return Optional.of(activityDto);
        } catch (IOException e) {
            log.error("Activity oject wasn't parsed correctly! Object: " + jsonObject, e);
        }
        return Optional.empty();
    }
}
