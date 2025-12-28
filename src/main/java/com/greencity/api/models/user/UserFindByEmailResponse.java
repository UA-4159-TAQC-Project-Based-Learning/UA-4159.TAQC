package com.greencity.api.models.user;


import com.greencity.api.models.user.submodules.LanguageModel;
import com.greencity.api.models.user.submodules.OwnSecurityModel;
import lombok.Data;

import java.util.Date;

@Data
public class UserFindByEmailResponse {
    public int id;
    public String name;
    public String email;
    public String role;
    public String userStatus;
    public Object verifyEmail;
    public String emailNotification;
    public Date dateOfRegistration;
    public String refreshTokenKey;
    public OwnSecurityModel ownSecurity;
    public String showLocation;
    public String showEcoPlace;
    public String showToDoList;
    public Date lastActivityTime;
    public LanguageModel languageVO;
    public String firstName;
    public String uuid;
    public Object userNotificationPreferenceDto;
}
