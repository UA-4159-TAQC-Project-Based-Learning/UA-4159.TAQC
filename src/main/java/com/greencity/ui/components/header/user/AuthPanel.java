package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
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

    // TODO: change return type once SignInModal is ready
    public LoginModalComponent clickSignIn() {
        clickDynamicElement(signInButton);
        return new LoginModalComponent(driver);
    }

    // TODO: change return type once SignUpModal is ready
    public void clickSignUp() {
        clickDynamicElement(signUpButton);
    }
}
