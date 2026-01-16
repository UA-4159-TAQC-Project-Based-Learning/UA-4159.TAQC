package com.greencity.api.clients;

import com.greencity.api.models.preferences.SearchUserNotificationPreferencesRequest;
import io.restassured.response.Response;

public class UserNotificationPreferenceClient extends BaseClient {
    public UserNotificationPreferenceClient(String baseUrl) {
        super(baseUrl);
    }

    public UserNotificationPreferenceClient(String baseUrl, String token) {
        super(baseUrl);
        this.setToken(token);
    }

    public Response search(SearchUserNotificationPreferencesRequest request){
        return preparedRequest()
                .body(request)
                .post("/user-notification-preference/search");
    }

}
