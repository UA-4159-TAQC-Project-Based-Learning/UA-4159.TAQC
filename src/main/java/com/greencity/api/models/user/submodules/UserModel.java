package com.greencity.api.models.user.submodules;


import lombok.Data;

import java.util.Date;

@Data
public class UserModel {
    public int id;
    public Object name;
    public String email;
    public String role;
    public Object userStatus;
    public Object verifyEmail;
    public Object emailNotification;
    public Object dateOfRegistration;
    public Object refreshTokenKey;
    public Object ownSecurity;
    public Object showLocation;
    public Object showEcoPlace;
    public Object showToDoList;
    public Object lastActivityTime;
    public Object languageVO;
    public Object firstName;
    public Object uuid;
    public Object userNotificationPreferenceDto;
}
