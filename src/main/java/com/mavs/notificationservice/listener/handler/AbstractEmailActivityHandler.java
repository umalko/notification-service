package com.mavs.notificationservice.listener.handler;

import com.mavs.activity.dto.BaseActivityDto;
import com.mavs.activity.model.Activity;
import com.mavs.activity.util.ActivityUtil;
import com.mavs.notificationservice.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractEmailActivityHandler<T extends BaseActivityDto> implements ActivityHandler {

    private final EmailService emailService;

    public AbstractEmailActivityHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void handleActivity(Activity activity) {
        ActivityUtil.convertJsonToBaseActivityObject(activity.getJsonObject(), getActivityDtoClassType())
                .ifPresent(activityDto ->
                        emailService.sendSimpleMessage(getReceiver(activityDto), getSubject(), getBody(activityDto)));
    }

    protected abstract String getSubject();

    protected abstract String getBody(T activityDto);

    protected abstract String getReceiver(T activityDto);

    protected abstract Class<T> getActivityDtoClassType();
}
