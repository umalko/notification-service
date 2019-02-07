package com.mavs.notificationservice.listener.handler;

import com.mavs.activity.dto.ActivityRelationshipDto;
import com.mavs.notificationservice.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class NewEmailActivityHandler extends AbstractEmailActivityHandler<ActivityRelationshipDto> {

    private static final String NEW_RELATIONSHIP_EMAIL_SUBJECT = "New relationship!";
    private static final String NEW_RELATIONSHIP_EMAIL_TEXT_FORMAT = "You have new relationship with: '%s'!";

    public NewEmailActivityHandler(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected String getSubject() {
        return NEW_RELATIONSHIP_EMAIL_SUBJECT;
    }

    @Override
    protected String getBody(ActivityRelationshipDto activityDto) {
        return String.format(NEW_RELATIONSHIP_EMAIL_TEXT_FORMAT, activityDto.getPersonRelationshipEmail());
    }

    @Override
    protected String getReceiver(ActivityRelationshipDto activityDto) {
        return activityDto.getPersonEmail();
    }

    @Override
    protected Class<ActivityRelationshipDto> getActivityDtoClassType() {
        return ActivityRelationshipDto.class;
    }
}
