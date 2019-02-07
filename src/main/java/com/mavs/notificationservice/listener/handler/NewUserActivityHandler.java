package com.mavs.notificationservice.listener.handler;

import com.mavs.activity.dto.ActivityUserDto;
import com.mavs.notificationservice.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class NewUserActivityHandler extends AbstractEmailActivityHandler<ActivityUserDto> {

    private static final String REGISTER_EMAIL_SUBJECT = "Registration info!";
    private static final String REGISTER_EMAIL_TEXT = "You are registered in the best system!";

    public NewUserActivityHandler(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected String getSubject() {
        return REGISTER_EMAIL_SUBJECT;
    }

    @Override
    protected String getBody(ActivityUserDto activityDto) {
        return REGISTER_EMAIL_TEXT;
    }

    @Override
    protected String getReceiver(ActivityUserDto activityDto) {
        return activityDto.getEmail();
    }

    @Override
    protected Class<ActivityUserDto> getActivityDtoClassType() {
        return ActivityUserDto.class;
    }
}
