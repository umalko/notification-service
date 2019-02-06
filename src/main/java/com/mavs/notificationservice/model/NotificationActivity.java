package com.mavs.notificationservice.model;

import com.mavs.activity.model.Activity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class NotificationActivity extends Activity {

    @Id
    @Override
    public String getId() {
        return super.getId();
    }

    public NotificationActivity(Activity activity) {
        super(activity);
    }
}
