package com.greencity.api;

import com.greencity.api.clients.LanguageClient;
import com.greencity.api.clients.LanguageClient.LanguageCode;
import com.greencity.api.testRunner.ApiTestRunnerWithUser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

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

        List<Map<String, Object>> languages = response.jsonPath().getList("$");
        softAssert.assertFalse(languages.isEmpty(),
                "Languages list is empty");

        List<String> codes = response.jsonPath().getList("name");
        softAssert.assertTrue(codes.contains("Ukrainian"),
                "Response does not contain code 'Ukrainian'");
        softAssert.assertTrue(codes.contains("English"),
                "Response does not contain code 'English'");



        softAssert.assertAll();
    }

    @Test(description = "Verify that all language codes can be retrieved")
    public void testFindAllLanguageCodes() {
        SoftAssert softAssert = new SoftAssert();

        Response response = languageClient.getAllLanguageCodes();

        softAssert.assertEquals(response.getStatusCode(), 200,
                "Status code is not 200");

        List<String> codes = response.jsonPath().getList("$");
        softAssert.assertTrue(codes.contains("uk"),
                "Response does not contain code 'uk'");
        softAssert.assertTrue(codes.contains("en"),
                "Response does not contain code 'en'");


        softAssert.assertAll();
    }

    @Test(description = "Verify that specific languages can be retrieved by code")
    public void testGetLanguagesByCode() {
        SoftAssert softAssert = new SoftAssert();

        Map<LanguageClient.LanguageCode, String> expectedLanguages = Map.of(
                LanguageClient.LanguageCode.EN, "English",
                LanguageClient.LanguageCode.UK, "Ukrainian"
        );

        for (Map.Entry<LanguageClient.LanguageCode, String> entry : expectedLanguages.entrySet()) {
            Response response = languageClient.getLanguageByCode(entry.getKey());

            softAssert.assertEquals(response.getStatusCode(), 200,
                    "Status code is not 200 for code " + entry.getKey().getCode());

            String code = response.jsonPath().getString("code");
            String name = response.jsonPath().getString("name");

            softAssert.assertEquals(code, entry.getKey().getCode(),
                    "Code mismatch for " + entry.getKey().getCode());
            softAssert.assertEquals(name, entry.getValue(),
                    "Name mismatch for " + entry.getKey().getCode());
        }

        softAssert.assertAll();
    }

}
