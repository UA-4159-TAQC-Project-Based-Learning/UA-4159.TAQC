package com.greencity.ui.components.header.user;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthPanel extends BaseComponent {

    private String signInButtonCss = "[class*='sign-in']";
    private String signUpButtonCss = "[class*='sign-up']";

    public AuthPanel(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // TODO: change return type once SignInModal is ready
    public void clickSignIn() {
        WebElement signIn = rootElement.findElement(By.cssSelector(signInButtonCss));
        clickDynamicElement(signIn);
    }

    // TODO: change return type once SignUpModal is ready
    public void clickSignUp() {
        WebElement signUp = rootElement.findElement(By.cssSelector(signUpButtonCss));
        clickDynamicElement(signUp);
    }
}
