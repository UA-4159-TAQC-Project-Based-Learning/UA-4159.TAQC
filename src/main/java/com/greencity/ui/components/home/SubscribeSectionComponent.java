package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscribeSectionComponent extends BaseComponent {

    @FindBy(xpath = ".//h2")
    private WebElement subscribeSectionTitle;
    @Getter
    @FindBy(xpath = ".//p")
    private WebElement subscribeSectionDescription;
    @Getter
    @FindBy(xpath = ".//div[@id='qr-code-wrapper']//img")
    private WebElement qrCodeImage;
    @Getter
    @FindBy(xpath = ".//input[@type='email']")
    private WebElement emailInput;
    @Getter
    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement subscribeButton;

    public SubscribeSectionComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Get Subscribe section title text")
    public String getSubscribeSectionTitle() {
        return subscribeSectionTitle.getText();
    }

    @Step("Get Subscribe section description text")
    public String getSubscribeSectionDescriptionText() {
        return subscribeSectionDescription.getText();
    }

    @Step("Check if QR code image is displayed")
    public boolean isQrCodeDisplayed() {
        return qrCodeImage.isDisplayed();
    }

    @Step("Get QR code image 'alt' attribute text")
    public String getQrCodeAltText() {
        return qrCodeImage.getAttribute("alt");
    }

    @Step("Get email input placeholder text")
    public String getEmailInputPlaceholderText() {
        return emailInput.getAttribute("placeholder");
    }

    @Step("Enter email into subscription input: {email}")
    public void enterEmail(String email) {
        waitUntilElementClickable(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Click 'Subscribe' button")
    public void clickSubscribeButton() {
        waitUntilElementClickable(subscribeButton);
        clickDynamicElement(subscribeButton);
    }

    @Step("Get 'Subscribe' button text")
    public String getSubscribeButtonText() {
        return subscribeButton.getText();
    }
}
