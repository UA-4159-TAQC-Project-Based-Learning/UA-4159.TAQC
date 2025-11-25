package com.greencity.ui.components.loginModalComponent;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.homepage.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModalComponent extends BaseComponent {

    @FindBy(xpath = "input[@id='email']")
    private WebElement emailSignInField;

    @FindBy(xpath = "input[@id='password']")
    private WebElement passwordSignInField;

//    @FindBy(xpath = "-")
//    private WebElement signInButton;

//    public HomePage loginDefaultUser() {
//        emailInput.sendKeys(testValueProvider.getUserEmail());
//        passwordInput.sendKeys(testValueProvider.getUserPassword());
//        signInButton.click();
//        return new HomePage(driver);
//    }

    public LoginModalComponent(WebDriver driver, WebElement loginModalRoot) {
        super(driver, loginModalRoot);
    }

}
