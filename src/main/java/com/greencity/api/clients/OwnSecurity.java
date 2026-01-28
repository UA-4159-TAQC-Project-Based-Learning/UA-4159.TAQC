package com.greencity.api.clients;


import com.greencity.api.models.ownsecurity.SingInRequest;
import io.restassured.response.Response;

public class OwnSecurity extends BaseClient {
    private final static String BASE_PATH = "/ownSecurity";

    public OwnSecurity(String baseUrl) {
        super(baseUrl);
    }

    public Response signIn(String email, String password) {


//        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        SingInRequest singInRequest = new SingInRequest();
        singInRequest.setEmail(email);
        singInRequest.setPassword(password);
        return preparedRequest()
                .body(singInRequest)
                .post(BASE_PATH + "/signIn");
    }


}