package com.greencity.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProfileRequest {
    private String name;
    private List<String> socialNetworks;
    public String showLocation;
    public String showEcoPlace;
    public String showToDoList;
    public Coordinates coordinates;
    public List<EmailPreference> emailPreferences;

}
