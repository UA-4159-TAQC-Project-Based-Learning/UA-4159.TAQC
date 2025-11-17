package com.greencity.ui.components.header.navbar;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobileNavMenu extends NavBar {

    private final String signInCss = ".header_mobile-menu-sign-in";
    private final String signUpCss = ".header_mobile-menu-sign-up";


    public MobileNavMenu(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    // open() -> should be inherited from NavBar

    // TODO: change return type to SignInModal once ready
    public void clickSignIn() {
        WebElement signIn = rootElement.findElement(By.cssSelector(signInCss));
        clickDynamicElement(signIn);
    }

    // TODO: change return type to SignUpModal once ready
    public void clickSignUp() {
        WebElement signUp = rootElement.findElement(By.cssSelector(signUpCss));
        clickDynamicElement(signUp);
    }
}
