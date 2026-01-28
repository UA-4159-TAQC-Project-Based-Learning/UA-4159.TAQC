package com.greencity.api.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailPreference {
    private String emailPreference;
    private String periodicity;

}
