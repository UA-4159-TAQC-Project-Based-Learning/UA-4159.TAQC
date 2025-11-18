package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.homepage.HomePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserMenu extends BaseComponent {

//    private String userFirstAndLastNameCss = "[class*='user-name']";
    private String signOutCss = "[aria-label*='sign-out']";
    private String savedCss = "[class='bookmark-icon']";
    private String notificationsCss = "[class='notification-icon']";

    @Getter
    @FindBy(css = "[class*='user-name']")
    private WebElement userFirstAndLastNameButton;

    public UserMenu(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getUserFirstAndLastNameAsText() {
        return userFirstAndLastNameButton.getText().trim();
    }

    public WebElement getUserFirstAndLastNameAsWebElement() {
        return rootElement.findElement(By.cssSelector(userFirstAndLastNameCss));
    }

    private void openProfileDropdown() {
        WebElement trigger = getUserFirstAndLastNameAsWebElement();
        clickDynamicElement(trigger);
    }

    public HomePage signOut() {
        openProfileDropdown();
        WebElement signOut = rootElement.findElement(By.cssSelector(signOutCss));
        clickDynamicElement(signOut);
        return new HomePage(driver);
    }
}
