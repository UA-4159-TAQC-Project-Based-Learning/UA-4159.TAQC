package com.greencity.api;

import com.greencity.api.clients.UserClient;
import com.greencity.api.models.user.ProfileStatisticsResponse;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTestsAuthorized extends ApiTestRunnerWithUser {

    @Test
    public void profileStatisticsTest() {
        UserClient userClient = new UserClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        SoftAssert softAssert = new SoftAssert();
        Response response = userClient.getProfileStatistics(signInResponse.userId);
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        ProfileStatisticsResponse profileStatisticsResponse = response.as(ProfileStatisticsResponse.class);

        softAssert.assertEquals(profileStatisticsResponse.getAmountHabitsAcquired(),
                Integer.valueOf(0), "Amount of habits acquired is not correct");
        softAssert.assertEquals(profileStatisticsResponse.getAmountHabitsInProgress(), Integer.valueOf(0), "Amount of habits in progress is not correct");
        softAssert.assertEquals(profileStatisticsResponse.getAmountOrganizedAndAttendedEvents(), Integer.valueOf(0), "Amount of organized and attended events is not correct");
        softAssert.assertEquals(profileStatisticsResponse.getAmountPublishedNews(), Integer.valueOf(119), "Amount of published news is not correct");
        softAssert.assertAll();
    }
}
