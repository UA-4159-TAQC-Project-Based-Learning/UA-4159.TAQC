package com.greencity.api;

import com.greencity.api.clients.UserClient;
import com.greencity.api.models.user.Coordinates;
import com.greencity.api.models.user.EmailPreference;
import com.greencity.api.models.user.ProfileRequest;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UserProfileTest extends ApiTestRunnerWithUser {
    @Test
    public void saveUserProfileSuccessTest() {
        UserClient userClient = new UserClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        SoftAssert softAssert = new SoftAssert();
        ProfileRequest request = new ProfileRequest(
                "Orqa",
                List.of("https://www.facebook.com/"),
                "PUBLIC",
                "PUBLIC",
                "PUBLIC",
                new Coordinates(50.4501, 30.5234),
                List.of(new EmailPreference("SYSTEM", "IMMEDIATELY"))
        );
        Response response = userClient.putProfile(request);
        String body = response.asString();
        String contentType = response.getHeader("content-type");
        String cacheControl = response.getHeader("cache-control");

        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        softAssert.assertEquals(body, "User successfully updated.", "Response body is incorrect");
        softAssert.assertEquals(contentType, "text/plain;charset=UTF-8", "Content type is invalid" );
        softAssert.assertEquals(cacheControl, "no-cache, no-store, max-age=0, must-revalidate", "cacheControl is invalid");
        softAssert.assertAll();
    }
    @Test
    public void saveUserProfileBadRequestTest() {
        UserClient userClient = new UserClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        SoftAssert softAssert = new SoftAssert();
        ProfileRequest badrequest = new ProfileRequest(
                "Orqa",
                List.of("string"),
                "PUBLIC",
                "PUBLIC",
                "PUBLIC",
                new Coordinates(50.4501, 30.5234),
                List.of(new EmailPreference("SYSTEM", "IMMEDIATELY"))
        );
        Response response = userClient.putProfile(badrequest);
        String contentType = response.getHeader("content-type");
        String cacheControl = response.getHeader("cache-control");

        softAssert.assertEquals(response.getStatusCode(), 400, "Status code is not 400");

        String errorMessage = response.jsonPath().getString("message");
        softAssert.assertEquals(errorMessage, "The string could not be parsed as a URI reference.", "Response body message is incorrect");
        softAssert.assertEquals(contentType, "application/json", "Content type is invalid" );
        softAssert.assertEquals(cacheControl, "no-cache, no-store, max-age=0, must-revalidate", "cacheControl is invalid");
        softAssert.assertAll();
    }

    @AfterMethod
    public  void tearDown() {
        UserClient userClient = new UserClient(testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
        ProfileRequest request = new ProfileRequest(
                testValueProvider.getUserName(),
                List.of(),
                "PRIVATE",
                "PRIVATE",
                "PRIVATE",
                null,
                List.of()
        );
        userClient.putProfile(request);
    }

}
