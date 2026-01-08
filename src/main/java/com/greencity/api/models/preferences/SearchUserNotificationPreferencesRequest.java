package com.greencity.api.models.preferences;

import lombok.Data;
import lombok.Getter;

@Data
public class SearchUserNotificationPreferencesRequest {

    @Getter
    private String userEmail;

    @Getter
    private String emailPreference;

    @Getter
    private String emailPreferencePeriodicity;
}
