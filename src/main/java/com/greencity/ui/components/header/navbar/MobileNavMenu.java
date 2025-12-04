package com.greencity.ui.components.header.navbar;

import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobileNavMenu extends NavBar {

    @Getter
    @FindBy(css = ".header_mobile-menu-sign-in")
    private WebElement signIn;

    @Getter
    @FindBy(css = ".header_mobile-menu-sign-up")
    private WebElement signUp;


    public MobileNavMenu(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // open() -> should be inherited from NavBar

    @Step("Click 'Sign In' in mobile navigation menu")
    public LoginModalComponent clickSignIn() {
        clickDynamicElement(signIn);
        return new LoginModalComponent(driver);
    }

    // TODO: change return type to SignUpModal once ready
    @Step("Click 'Sign Up' in mobile navigation menu")
    public void clickSignUp() {
        clickDynamicElement(signUp);
    }

    @Step("Check if 'Sign In' option is visible in mobile navigation menu")
    public boolean hasSignIn() {
        return signIn.isDisplayed();
    }

    @Step("Check if 'Sign Up' option is visible in mobile navigation menu")
    public boolean hasSignUp() {
        return signUp.isDisplayed();
    }
}
