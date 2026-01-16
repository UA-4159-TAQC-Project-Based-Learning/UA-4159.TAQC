package com.greencity.api;

import com.greencity.api.clients.EcoNewsCommentClient;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcoNewsCommentApiTest extends ApiTestRunnerWithUser {

    @Test
    public void shouldCreateEcoNewsComment() {
        EcoNewsCommentClient commentClient = new EcoNewsCommentClient(
                testValueProvider.getBaseUIUrl(),
                signInResponse.accessToken
        );

        Integer ecoNewsId = 556;
        String commentText = "Test comment 123!";

        Response response = commentClient.addComment(ecoNewsId, commentText);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 201, "Status code is not 201");


        softAssert.assertAll();
    }
}