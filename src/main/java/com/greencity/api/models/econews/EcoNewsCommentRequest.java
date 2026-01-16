package com.greencity.api.models.econews;

import lombok.Data;

@Data
public class EcoNewsCommentRequest {
    private Integer ecoNewsId;
    private String text;
}