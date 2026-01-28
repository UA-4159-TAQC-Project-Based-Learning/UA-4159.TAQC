package com.greencity.api.models.user;


import lombok.Data;

@Data
public class ProfileStatisticsResponse {
    public Integer amountHabitsInProgress;
    public Integer amountHabitsAcquired;
    public Integer amountPublishedNews;
    public Integer amountOrganizedAndAttendedEvents;
}
