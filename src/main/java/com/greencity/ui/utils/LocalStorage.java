package com.greencity.ui.utils;

import com.greencity.ui.Base;
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
 * ls.setLSItemLS("accessToken", savedSession.accessToken());
 * ls.setLSItemLS("refreshToken", savedSession.refreshToken());
 * ls.setLSItemLS("userId", savedSession.userId());
 *
 */
public class LocalStorage extends Base {

    private static final String ACCESS_TOKEN_KEY  = "accessToken";
    private static final String REFRESH_TOKEN_KEY = "refreshToken";
    private static final String USER_ID_KEY       = "userId";

    public LocalStorage(WebDriver driver) {
        super(driver);
    }

    /**
     *
     * @param key -> localStorage key (e.g. "accessToken")
     * @return -> value as String
     */
    public String getItemFromLS(String key) {
        return (String) threadJs.executeScript(
                "return window.localStorage.getItem(arguments[0]);",
                key
        );
    }

    public void setLSItemLS(String key, String value) {
        threadJs.executeScript(
                "window.localStorage.setItem(arguments[0], arguments[1]);",
                key, value
        );
    }

    public void clearLS() {
        threadJs.executeScript("window.localStorage.clear();");
    }

    public String getAccessToken() {
        return getItemFromLS(ACCESS_TOKEN_KEY);
    }

    public String getRefreshToken() {
        return getItemFromLS(REFRESH_TOKEN_KEY);
    }

    public String getUserId() {
        return getItemFromLS(USER_ID_KEY);
    }
}
