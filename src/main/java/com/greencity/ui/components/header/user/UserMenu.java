package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserMenu extends BaseComponent {

    private String userFirstAndLastNameCss = "[class*=\"user-name\"]";
    private String signOutCss = "[aria-label*=\"sign-out\"]";
    private String savedCss = "[class=\"bookmark-icon\"]";
    private String notificationsCss = "[class=\"notification-icon\"]";

    public UserMenu(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getUserFirstAndLastNameAsText() {
        WebElement name = rootElement.findElement(By.cssSelector(userFirstAndLastNameCss));
        return name.getText().trim();
    }

    public WebElement getUserFirstAndLastNameAsWebElement() {
        return rootElement.findElement(By.cssSelector(userFirstAndLastNameCss));
    }

    private void openProfileDropdown() {
        WebElement trigger = getUserFirstAndLastNameAsWebElement();
        clickDynamicElement(trigger);
    }

    public void signOut() {
        openProfileDropdown();
        WebElement signOut = rootElement.findElement(By.cssSelector(signOutCss));
        clickDynamicElement(signOut);
    }
}
