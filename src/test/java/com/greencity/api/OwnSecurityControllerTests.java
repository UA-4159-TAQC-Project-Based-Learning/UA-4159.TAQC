package com.greencity.api;

import com.greencity.api.clients.OwnSecurity;
import com.greencity.api.models.ownsecurity.SignInResponse;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OwnSecurityControllerTests extends ApiTestRunner {



    @Test
    public void successSingInTests() {

        OwnSecurity ownSecurity = new OwnSecurity(testValueProvider.getBaseAPIUserUrl());
        SoftAssert softAssert = new SoftAssert();
        Response response = ownSecurity.signIn(
                testValueProvider.getUserEmail(),
                testValueProvider.getUserPassword()
        );
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        SignInResponse signInResponse = response.as(SignInResponse.class);
        softAssert.assertNotNull(signInResponse.accessToken, "Access token is null");
        softAssert.assertNotNull(signInResponse.refreshToken, "Refresh token is null");
        softAssert.assertEquals(signInResponse.userId, testValueProvider.getUserId(), "User ID is not correct");
        softAssert.assertEquals(signInResponse.name, testValueProvider.getUserName(), "User name is not correct");
        softAssert.assertAll();

    }
}
