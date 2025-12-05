package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.homepage.HomePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMenu extends BaseComponent {

    @Getter
    @FindBy(css = ".user-name")
    private WebElement userFirstAndLastName;

    @Getter
    @FindBy(css = "[aria-label*='sign-out']")
    private WebElement signOut;

    @Getter
    @FindBy(css = ".bookmark-icon")
    private WebElement saved;

    @Getter
    @FindBy(css = ".notification-icon")
    private WebElement notifications;

    public UserMenu(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get logged-in user name via the header")
    public String getUserFirstAndLastNameAsText() {
        return userFirstAndLastName.getText().trim();
    }

    private void openProfileDropdown() {
        WebElement trigger = getUserFirstAndLastName();
        clickDynamicElement(trigger);
    }

    @Step("Sign out via the header")
    public HomePage signOut() {
        openProfileDropdown();
        clickDynamicElement(signOut);
        return new HomePage(driver);
    }
}
