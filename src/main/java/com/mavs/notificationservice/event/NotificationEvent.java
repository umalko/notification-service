package com.mavs.notificationservice.event;

import com.mavs.notificationservice.model.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotificationEvent extends ApplicationEvent {

    private NotificationType notificationType;

    public NotificationEvent(Object source, NotificationType notificationType) {
        super(source);
        this.notificationType = notificationType;
    }
}
