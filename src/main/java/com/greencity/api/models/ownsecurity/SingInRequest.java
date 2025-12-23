package com.greencity.api.models.ownsecurity;

import lombok.Data;
import lombok.Getter;

@Data
public class SingInRequest {
    @Getter
    private String email;
    @Getter
    private String password;
    @Getter
    private String projectName = "GREENCITY";
}
