package com.greencity.api.models.user.submodules;


import lombok.Data;

@Data
public class OwnSecurityModel {
    public int id;
    public String password;
    public UserModel user;
}
