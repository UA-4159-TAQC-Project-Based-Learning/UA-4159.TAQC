package com.greencity.ui.utils;

public record AuthSession(
        String accessToken,
        String refreshToken,
        String userId
) {}
