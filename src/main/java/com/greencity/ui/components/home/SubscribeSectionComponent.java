package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
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


    public String getSubscribeSectionTitle() {
        return subscribeSectionTitle.getText();
    }

    public String getSubscribeSectionDescriptionText() {
        return subscribeSectionDescription.getText();
    }

    public boolean isQrCodeDisplayed() {
        return qrCodeImage.isDisplayed();
    }

    public String getQrCodeAltText() {
        return qrCodeImage.getAttribute("alt");
    }

    public String getEmailInputPlaceholderText() {
        return emailInput.getAttribute("placeholder");
    }

    public void enterEmail(String email) {
        waitUntilElementClickable(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickSubscribeButton() {
        waitUntilElementClickable(subscribeButton);
        clickDynamicElement(subscribeButton);
    }

    public String getSubscribeButtonText() {
        return subscribeButton.getText();
    }
}
