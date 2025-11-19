package com.greencity.ui.components.header.navbar;

import com.greencity.ui.components.loginModalComponent.LoginModalComponent;
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

    public LoginModalComponent clickSignIn() {
        if (!hasSignIn()) {throw new IllegalStateException("Auth panel is not available in header.");}

        clickDynamicElement(signIn);
        return new LoginModalComponent(driver);
    }

    // TODO: change return type to SignUpModal once ready
    public void clickSignUp() {
        clickDynamicElement(signUp);
    }

    public boolean hasSignIn() {
        try { return signIn.isDisplayed(); } catch (Exception ignored) { return false; }
    }

    public boolean hasSignUp() {
        try { return signUp.isDisplayed(); } catch (Exception ignored) { return false; }
    }
}
