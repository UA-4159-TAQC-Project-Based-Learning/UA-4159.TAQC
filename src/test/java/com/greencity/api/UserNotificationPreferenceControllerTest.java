package com.greencity.api;

import com.greencity.api.clients.UserNotificationPreferenceClient;
import com.greencity.api.models.preferences.SearchUserNotificationPreferencesRequest;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserNotificationPreferenceControllerTest extends ApiTestRunnerWithUser {

    @Test
    public void searchUserNotificationPreference() {
        UserNotificationPreferenceClient client =
                new UserNotificationPreferenceClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        SoftAssert softAssert = new SoftAssert();
        SearchUserNotificationPreferencesRequest request = new SearchUserNotificationPreferencesRequest();
        request.setUserEmail(testValueProvider.getUserEmail());
        request.setEmailPreference("SYSTEM");
        request.setEmailPreferencePeriodicity("IMMEDIATELY");
        Response response = client.search(request);
        //this test is always falling with 403 due to server error
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        softAssert.assertAll();
    }

}
