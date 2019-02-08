package com.mavs.notificationservice.listener.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mavs.activity.dto.ActivityDto;
import com.mavs.activity.dto.ActivityRelationshipDto;
import com.mavs.activity.service.ActivityService;
import com.mavs.notificationservice.model.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableRabbit
@Configuration
@RabbitListener(queues = "NEW_RELATIONSHIP_QUEUE")
public class NewRelationshipConsumerListener extends AbstractConsumerListener<ActivityRelationshipDto> {

    public NewRelationshipConsumerListener(ActivityService activityService, ApplicationEventPublisher eventPublisher) {
        super(activityService, eventPublisher);
    }

    @Override
    protected NotificationType getNotificationType() {
        return NotificationType.NEW_RELATIONSHIP;
    }

    @Override
    protected TypeReference getTypeReferenceOfActivityDto() {
        return new TypeReference<ActivityDto<ActivityRelationshipDto>>() {};
    }
}
