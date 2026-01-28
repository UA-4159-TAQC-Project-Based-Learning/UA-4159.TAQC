package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPanel extends BaseComponent {

    @Getter
    @FindBy(css = ".header_sign-in-link")
    private WebElement signInButton;

    @Getter
    @FindBy(css = ".header_sign-up-link")
    private WebElement signUpButton;

    public AuthPanel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Click 'Sign In' link in the header")
    public LoginModalComponent clickSignIn() {
        clickDynamicElement(signInButton);
        return new LoginModalComponent(driver);
    }

    // TODO: change return type once SignUpModal is ready
    @Step("Click 'Sign Up' link in the header")
    public void clickSignUp() {
        clickDynamicElement(signUpButton);
    }
}
