package com.greencity.ui.utils;

public enum Language {
    EN("En"),
    UK("Uk");

    private final String label;

    Language(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
