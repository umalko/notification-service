package com.mavs.notificationservice.listener.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavs.activity.dto.ActivityDto;
import com.mavs.activity.dto.BaseActivityDto;
import com.mavs.activity.service.ActivityService;
import com.mavs.activity.util.ActivityUtil;
import com.mavs.notificationservice.event.NotificationEvent;
import com.mavs.notificationservice.model.NotificationActivity;
import com.mavs.notificationservice.model.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public abstract class AbstractConsumerListener<T extends BaseActivityDto> {

    private final ActivityService activityService;
    private final ApplicationEventPublisher eventPublisher;

    protected AbstractConsumerListener(ActivityService activityService, ApplicationEventPublisher eventPublisher) {
        this.activityService = activityService;
        this.eventPublisher = eventPublisher;
    }

    @RabbitHandler
    public void onReceive(@Payload String jsonObject) {
        transformJsonObject(jsonObject).ifPresent(activityDto ->
                ActivityUtil.convertToActivity(activityDto).ifPresent(activity -> {
                    activityService.save(new NotificationActivity(activity));
                    eventPublisher.publishEvent(new NotificationEvent(activity, getNotificationType()));
                }));
    }

    private Optional<ActivityDto<T>> transformJsonObject(String jsonObject) {
        try {
            ActivityDto<T> activityDto = new ObjectMapper().readValue(jsonObject, getTypeReferenceOfActivityDto());
            log.info("Activity object comes to Notification Service: {}", activityDto);
            return Optional.of(activityDto);
        } catch (IOException e) {
            log.error("Activity oject wasn't parsed correctly! Object: " + jsonObject, e);
        }
        return Optional.empty();
    }

    protected abstract NotificationType getNotificationType();

    protected abstract TypeReference getTypeReferenceOfActivityDto();
}
