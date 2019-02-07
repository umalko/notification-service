package com.mavs.notificationservice.listener.consumer;

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
@RabbitListener(queues = "END_RELATIONSHIP_QUEUE")
public class EndRelationshipConsumerListener extends AbstractConsumerListener<ActivityRelationshipDto> {

    public EndRelationshipConsumerListener(ActivityService activityService, ApplicationEventPublisher eventPublisher) {
        super(activityService, eventPublisher);
    }

    @Override
    protected NotificationType getNotificationType() {
        return NotificationType.END_RELATIONSHIP;
    }
}
