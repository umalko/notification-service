package com.mavs.notificationservice.event;

import org.springframework.context.ApplicationEvent;

public class UserRegisteredEvent extends ApplicationEvent {

    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
