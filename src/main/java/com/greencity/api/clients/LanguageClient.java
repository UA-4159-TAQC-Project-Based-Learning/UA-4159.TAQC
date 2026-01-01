package com.greencity.api.clients;

import io.restassured.response.Response;

public class LanguageClient extends BaseClient {
    private static final String BASE_PATH = "/lang";

    public enum LanguageCode {
        EN("en"),
        UK("uk");

        private final String code;

        LanguageCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public LanguageClient(String baseUrl) {
        super(baseUrl);
    }

    public LanguageClient(String baseUrl, String token) {
        super(baseUrl);
        this.setToken(token);
    }

    public Response getAllLanguages() {
        return preparedRequest()
                .get(BASE_PATH);
    }

    public Response getAllLanguageCodes() {
        return preparedRequest()
                .get(BASE_PATH + "/codes");
    }

    public Response getLanguageByCode(LanguageCode code) {
        return preparedRequest()
                .get(BASE_PATH + "/codes/" + code.getCode());
    }
}
