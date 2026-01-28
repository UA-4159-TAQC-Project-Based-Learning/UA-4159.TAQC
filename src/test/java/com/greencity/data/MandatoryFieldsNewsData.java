package com.greencity.data;

import lombok.Getter;

import java.util.List;

@Getter
public class MandatoryFieldsNewsData {
    private String title;
    private List<String> tags;
    private String contentText;

    public MandatoryFieldsNewsData(String title, List<String> tags, String contentText) {
        this.title = title;
        this.tags = tags;
        this.contentText = contentText;
    }

}
