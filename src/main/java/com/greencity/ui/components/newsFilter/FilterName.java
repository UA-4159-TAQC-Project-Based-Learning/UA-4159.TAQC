package com.greencity.ui.components.newsFilter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FilterName {
    NEWS("News"),
    EVENTS("Events"),
    EDUCATION("Education"),
    ADS("Ads"),
    INITIATIVES("Initiatives");

    private final String value;
}
