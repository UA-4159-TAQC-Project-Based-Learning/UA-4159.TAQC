package com.greencity.api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

public class BaseClient {
    protected final String baseAPIUrl;
    protected final ContentType contentType;
    @Getter
    @Setter
    protected String token;


    public BaseClient(String baseUrl) {
        this.baseAPIUrl = baseUrl;
        contentType = ContentType.JSON;
    }

    public BaseClient(String baseUrl, ContentType contentType) {
        this.baseAPIUrl = baseUrl;
        this.contentType = contentType;
    }

    public BaseClient(String baseUrl, String contentType) {
        this.baseAPIUrl = baseUrl;
        this.contentType = ContentType.valueOf(contentType);
    }


    protected RequestSpecification preparedRequest() {
        RequestSpecification request = RestAssured.given()
//                .log()
//                .body()
                .baseUri(baseAPIUrl)
                .contentType(contentType);
        if (token != null) {
            request.header("Authorization", "Bearer " + token);
        }
        return request;
    }

}