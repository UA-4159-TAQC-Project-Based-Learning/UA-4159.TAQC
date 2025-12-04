package com.greencity.ui.components.loginModalComponent;

import com.greencity.ui.components.BaseComponent;
import com.greencity.ui.pages.profile.ProfilePage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginModalComponent extends BaseComponent {

    // Intentionally not using @FindBy for the modal root to support the overloaded constructor
    private static final By MODAL_ROOT_LOCATOR = By.cssSelector("app-auth-modal");

    @Getter
    @FindBy(css = ".close-modal-window .cross-btn")
    private WebElement closeButton;

    @Getter
    @FindBy(css = "input#email")
    private WebElement emailInput;

    @Getter
    @FindBy(css = "input#password")
    private WebElement passwordInput;

    @Getter
    @FindBy(css = "button[type='submit']")
    private WebElement signInButton;

    @Getter
    @FindBy(css = "button.google-sign-in")
    private WebElement googleSignInButton;

    @Getter
    @FindBy(css = ".forgot-password")
    private WebElement forgotPasswordLink;

    @Getter
    @FindBy(css = ".missing-account a")
    private WebElement signUpLink;

    // appears only when there is a general error message ("Bad password", "Bad email or password")
    @FindBy(css = ".alert-general-error")
    private WebElement generalErrorMessage;

    public LoginModalComponent(WebDriver driver, WebElement loginModalRoot) {
        super(driver, loginModalRoot);
    }

    /**
     * Overloaded constructor: waits for the auth modal to appear
     * and uses it as the component root.
     */
    public LoginModalComponent(WebDriver driver) {
        this(driver, new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MODAL_ROOT_LOCATOR)));
    }

    public boolean isOpen() {
        return rootElement.isDisplayed();
    }

    public void close() {
        clickDynamicElement(closeButton);
    }

    @Step("Type email: {email}")
    public LoginModalComponent typeEmail(String email) {
        waitUntilElementVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Type password: {password}")
    public LoginModalComponent typePassword(String password) {
        waitUntilElementVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Submit login form")
    public ProfilePage submit() {
        clickDynamicElement(signInButton);
        return new ProfilePage(driver);
    }

    // TODO - change return type once Profile page is ready
    @Step("Login with email: {email} and password: {password}")
    public void login(String email, String password) {
        typeEmail(email);
        typePassword(password);
        submit();
    }

    public void clickForgotPassword() {
        clickDynamicElement(forgotPasswordLink);
    }

    public void clickSignInWithGoogle() {
        clickDynamicElement(googleSignInButton);
    }

    public void clickSignUpLink() {
        clickDynamicElement(signUpLink);
    }

    public boolean hasGeneralError() {
        return generalErrorMessage.isDisplayed();
    }

    public String getGeneralErrorText() {
        if (!hasGeneralError()) {
            return "";
        }
        return generalErrorMessage.getText().trim();
    }

}