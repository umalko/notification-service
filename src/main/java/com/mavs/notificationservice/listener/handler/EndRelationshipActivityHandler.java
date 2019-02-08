package com.mavs.notificationservice.listener.handler;

import com.mavs.activity.dto.ActivityRelationshipDto;
import com.mavs.notificationservice.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class EndRelationshipActivityHandler extends AbstractEmailActivityHandler<ActivityRelationshipDto> {

    private static final String END_RELATIONSHIP_EMAIL_SUBJECT = "End relationship!";
    private static final String END_RELATIONSHIP_EMAIL_TEXT_FORMAT = "You have end relationship with: '%s'!";

    public EndRelationshipActivityHandler(EmailService emailService) {
        super(emailService);
    }

    @Override
    protected String getSubject() {
        return END_RELATIONSHIP_EMAIL_SUBJECT;
    }

    @Override
    protected String getBody(ActivityRelationshipDto activityDto) {
        return String.format(END_RELATIONSHIP_EMAIL_TEXT_FORMAT, activityDto.getPersonRelationshipEmail());
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
