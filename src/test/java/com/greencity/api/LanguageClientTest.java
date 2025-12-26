package com.greencity.api.tests;

import com.greencity.api.clients.LanguageClient;
import com.greencity.api.clients.LanguageClient.LanguageCode;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LanguageClientTest extends ApiTestRunnerWithUser {
    private LanguageClient languageClient;

    @BeforeClass
    public void setUp() {
        languageClient = new LanguageClient(
                testValueProvider.getBaseAPIUserUrl(), signInResponse.accessToken);
    }

    @Test(description = "Verify that all languages can be retrieved")
    public void testFindAllLanguages() {
        SoftAssert softAssert = new SoftAssert();

        Response response = languageClient.getAllLanguages();

        softAssert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");

        String body = response.getBody().asString();
        softAssert.assertTrue(body.contains("en"),
                "Response does not contain 'en'");

        softAssert.assertAll();
    }

    @Test(description = "Verify that all language codes can be retrieved")
    public void testFindAllLanguageCodes() {
        SoftAssert softAssert = new SoftAssert();

        Response response = languageClient.getAllLanguageCodes();

        softAssert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");

        String body = response.getBody().asString();
        softAssert.assertTrue(body.contains("uk"),
                "Response does not contain 'uk'");

        softAssert.assertAll();
    }

    @Test(description = "Verify that a specific language can be retrieved by code")
    public void testFindLanguageByCode() {
        SoftAssert softAssert = new SoftAssert();

        Response response = languageClient.getLanguageByCode(LanguageCode.EN);

        softAssert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");

        String body = response.getBody().asString();
        softAssert.assertTrue(body.contains("\"code\":\"en\""),
                "Response does not contain the correct code 'en'");

        softAssert.assertAll();
    }
}
