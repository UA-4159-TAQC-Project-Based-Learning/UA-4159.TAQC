package com.greencity.ui.utils;

public enum NavItem {
    ECO_NEWS("#/greenCity/news"),
    EVENTS("#/greenCity/events"),
    PLACES("#/greenCity/places"),
    ABOUT_US("#/greenCity/about"),
    MY_SPACE("#/greenCity/profile"),
    UBS_COURIER("#/ubs"),
    LOGO("#/greenCity");

    private final String href;

    NavItem(String href) {
        this.href = href;
    }

    public String href() {
        return href;
    }
}
