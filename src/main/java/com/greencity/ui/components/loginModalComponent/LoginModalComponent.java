package com.greencity.ui.components.loginModalComponent;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModalComponent extends BaseComponent {

    // Intentionally not using @FindBy for the modal root to support the overloaded constructor
    private static final By MODAL_ROOT_LOCATOR = By.cssSelector("app-auth-modal");

    public LoginModalComponent(WebDriver driver, WebElement loginModalRoot) {
        super(driver, loginModalRoot);
    }

    /**
     * Overloaded constructor: waits for the auth modal to appear
     * and uses it as the component root.
     */
    public LoginModalComponent(WebDriver driver) {
        this(driver, new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(MODAL_ROOT_LOCATOR)));
    }

}
