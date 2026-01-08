package com.greencity.api.models.econews;

public class EcoNewsCommentResponse {
    private Long id;
    private String text;
    private Long ecoNewsId;
    private Long parentCommentId;
    private String authorName;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getEcoNewsId() {
        return ecoNewsId;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }

    public String getAuthorName() {
        return authorName;
    }
}

