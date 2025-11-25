package com.greencity.utils;

public record AuthSession(
        String accessToken,
        String refreshToken,
        String userId,
        String name,
        String language
) {}
