package com.greencity.api;

import com.greencity.api.clients.UserClient;
import com.greencity.api.models.user.ProfileStatisticsResponse;
import com.greencity.api.models.user.UserFindByEmailResponse;
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
        softAssert.assertEquals(profileStatisticsResponse.getAmountPublishedNews(), Integer.valueOf(49), "Amount of published news is not correct");
        softAssert.assertAll();
    }

    @Test
    public void findUserByEmailTest() {
        UserClient userClient = new UserClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        SoftAssert softAssert = new SoftAssert();
        Response response = userClient.findUserByEmail(testValueProvider.getUserEmail());
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        UserFindByEmailResponse userFindByEmailResponse = response.as(UserFindByEmailResponse.class);

        softAssert.assertEquals(userFindByEmailResponse.name,"Testuser2025", "Email is not correct");
        softAssert.assertEquals(userFindByEmailResponse.email,testValueProvider.getUserEmail(), "UserName is not correct");
        softAssert.assertEquals(userFindByEmailResponse.role,"ROLE_USER", "Role is not correct");
        softAssert.assertEquals(userFindByEmailResponse.userStatus,"VERIFIED", "UserStatus is not correct");
        softAssert.assertEquals(userFindByEmailResponse.ownSecurity.user.id, (int)testValueProvider.getUserId(), "UserId is not correct");
        softAssert.assertNotNull(userFindByEmailResponse.ownSecurity.password, "Password is null");

        softAssert.assertAll();
    }
}
