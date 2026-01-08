package com.greencity.api.clients;

import io.restassured.response.Response;

public class EcoNewsCommentClient extends BaseClient {

    public EcoNewsCommentClient(String baseUrl, String token) {
        super(baseUrl);
        this.setToken(token);
    }

    public Response addComment(Integer ecoNewsId, String commentText) {
        String path = "/eco-news/" + ecoNewsId + "/comments";
        return preparedRequest()
                .body("{\"text\":\"" + commentText + "\"}")
                .post(path);
    }
}
