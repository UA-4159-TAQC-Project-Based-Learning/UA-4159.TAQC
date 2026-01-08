package com.greencity.api.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class AddCommentRequest {
    public String text;
    public Long parentCommentId;
}


public class EcoNewsCommentClient extends BaseClient {

    public EcoNewsCommentClient(String baseUrl, String token) {
        super(baseUrl, ContentType.MULTIPART);
        this.setToken(token);
    }

    public Response addComment(Integer ecoNewsId, String commentText) {
        String path = "/eco-news/" + ecoNewsId + "/comments";
        AddCommentRequest addCommentRequest = new AddCommentRequest(commentText, 0L);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonBody = mapper.writeValueAsString(addCommentRequest);
            return preparedRequest()

                    .multiPart("request", jsonBody, "application/json")
                    .post(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;

    }
}
