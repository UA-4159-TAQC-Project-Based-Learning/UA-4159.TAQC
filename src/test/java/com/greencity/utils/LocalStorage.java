package com.greencity.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Util for working with window.localStorage.
 * Allows saving localStorage (auth keys) to avoid multiple logins.
 *
 * Usage example:
 * In @BeforeAll -> initSession
 * LocalStorage ls = new LocalStorage(driver);
 * savedSession = new AuthSession(
 *         ls.getAccessToken(),
 *         ls.getRefreshToken(),
 *         ls.getUserId()
 * );
 *
 * Later, in @BeforeEach (or any other relevant place) -> restore localStorage
 * ls = new LocalStorage(driver);
 * ls.setItem("accessToken", savedSession.accessToken());
 * ls.setItem("refreshToken", savedSession.refreshToken());
 * ls.setItem("userId", savedSession.userId());
 *
 */
public class LocalStorage {

    private static final String ACCESS_TOKEN_KEY  = "accessToken";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";
    private static final String USER_ID_KEY       = "userId";
    private static final String NAME_KEY          = "name";
    private static final String LANGUAGE_KEY      = "language";

    private final JavascriptExecutor js;

    public LocalStorage(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * Retrieves a value from localStorage by key.
     * @param key -> localStorage key (e.g. "accessToken")
     * @return -> value as String
     */
    public String getItem(String key) {
        return (String) js.executeScript(
                "return window.localStorage.getItem(arguments[0]);",
                key
        );
    }

    public void setItem(String key, String value) {
        js.executeScript(
                "window.localStorage.setItem(arguments[0], arguments[1]);",
                key, value
        );
    }

    public void clear() {
        js.executeScript("window.localStorage.clear();");
    }

    public String getAccessToken() {
        return getItem(ACCESS_TOKEN_KEY);
    }

    public String getRefreshToken() {
        return getItem(REFRESH_TOKEN_KEY);
    }

    public String getUserId() {
        return getItem(USER_ID_KEY);
    }

    public String getName() {return getItem(NAME_KEY);}

    public String getLanguage() {return getItem(LANGUAGE_KEY);}
}
