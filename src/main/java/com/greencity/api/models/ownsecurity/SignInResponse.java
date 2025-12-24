package com.greencity.api.models.ownsecurity;

import lombok.Getter;

public class SignInResponse {
    public Integer userId;
    @Getter
    public String accessToken;
    public String refreshToken;
    public String name;
    public Boolean ownRegistrations;

}
