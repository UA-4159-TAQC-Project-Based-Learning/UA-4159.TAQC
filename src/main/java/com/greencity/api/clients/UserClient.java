package com.greencity.api.clients;

import com.greencity.api.models.user.ProfileRequest;
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

    public Response putProfile(ProfileRequest request){
        return preparedRequest()
                .body(request)
                .put("/user/profile");
    }
  
    public Response updateUserLanguage(int languageId) {
        return preparedRequest()
                .put("/user/language/" + languageId);
    }

    public Response getUserLanguage() {
        return preparedRequest()
                .get("/user/lang");
    }

}
