package com.greencity.ui.components.home;

import com.greencity.ui.components.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscribeSectionComponent extends BaseComponent {
    //root ("//div[@id ='subscribe']")
    @FindBy(xpath = ".//h2")
    private WebElement subscribeSectionTitle;

    @FindBy(xpath = ".//p")
    private WebElement subscribeSectionDescription;

    @FindBy(xpath = ".//div[@id='qr-code-wrapper']//img")
    private WebElement qrCodeImage;

    @FindBy(xpath = ".//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = ".//button[contains(@class, 'primary-global-button')]")
    private WebElement subscribeButton;

    public SubscribeSectionComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }
    //----------functional----------
    //---title
    public String getSubscribeSectionTitle() {
        return subscribeSectionTitle.getText();
    }

    //----description
    public String getSubscribeSectionDescriptionText() {
        return subscribeSectionDescription.getText();
    }

    //---QR-code
    public boolean isQrCodeDisplayed() {
        return qrCodeImage.isDisplayed();
    }
    public String getQrCodeAltText() {
        return qrCodeImage.getAttribute("alt");
    }

    //---Email input
    public String getEmailInputPlaceholderText() {
        return emailInput.getAttribute("placeholder");
    }
    public void enterEmail(String email) {
        waitUntilElementClickable(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    //---subscribeButton
    public void clickSubscribeButton() {
        waitUntilElementClickable(subscribeButton);
        clickDynamicElement(subscribeButton);
    }

    public String getSubscribeButtonText() {
        return subscribeButton.getText();
    }
}
