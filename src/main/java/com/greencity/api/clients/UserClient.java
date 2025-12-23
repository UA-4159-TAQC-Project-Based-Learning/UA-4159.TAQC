package com.greencity.api.clients;

import io.restassured.response.Response;

public class UserClient extends BaseClient {
    public UserClient(String baseUrl) {
        super(baseUrl);
    }
    public UserClient(String baseUrl, String token) {
        super(baseUrl);
        this.setToken(token);
    }

    public Response getProfileStatistics(Integer id){
        return preparedRequest()
                .get("/user/" + id + "/profileStatistics/");
    }

}
