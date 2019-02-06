package com.mavs.notificationservice.listener;

import com.mavs.activity.dto.ActivityUserDto;
import com.mavs.activity.model.Activity;
import com.mavs.activity.util.ActivityUtil;
import com.mavs.notificationservice.event.UserRegisteredEvent;
import com.mavs.notificationservice.service.EmailService;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class UserRegisteredActivityListener implements ApplicationListener<UserRegisteredEvent> {

    private static final String REGISTER_EMAIL_SUBJECT = "Registration info!";
    private static final String REGISTER_EMAIL_TEXT = "You are registered in the best system!";

    private final EmailService emailService;

    public UserRegisteredActivityListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(UserRegisteredEvent userRegisteredEvent) {
        Activity activity = (Activity) userRegisteredEvent.getSource();
        ActivityUtil.convertJsonToBaseActivityObject(activity.getJsonObject(), ActivityUserDto.class)
                .ifPresent(activityUserDto ->
                        emailService.sendSimpleMessage(activityUserDto.getEmail(),
                                REGISTER_EMAIL_SUBJECT, REGISTER_EMAIL_TEXT));
    }
}
