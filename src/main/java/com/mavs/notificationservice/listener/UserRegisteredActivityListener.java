package com.mavs.notificationservice.listener;

import com.mavs.activity.model.Activity;
import com.mavs.notificationservice.event.NotificationEvent;
import com.mavs.notificationservice.listener.handler.ActivityHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;

@Async
@Component
public class UserRegisteredActivityListener implements ApplicationListener<NotificationEvent> {

    private static final String ACTIVITY_HANDLER_NAME_FORMAT = "%sActivityHandler";

    private final ApplicationContext context;

    public UserRegisteredActivityListener(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Handle event accordion to 'NotificationType'. 'NotificationType' + 'ActivityHandler' = NewUserActivityHandler.java
     * @param notificationEvent notification event. Can include any information intended for sending notification.
     */
    @Override
    public void onApplicationEvent(NotificationEvent notificationEvent) {
        String notificationType = UPPER_UNDERSCORE.to(LOWER_CAMEL, notificationEvent.getNotificationType().name());
        ActivityHandler activityHandler = (ActivityHandler) context.getBean(
                String.format(ACTIVITY_HANDLER_NAME_FORMAT, notificationType));

        activityHandler.handleActivity((Activity) notificationEvent.getSource());
    }
}
