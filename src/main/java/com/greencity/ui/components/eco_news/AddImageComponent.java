package com.greencity.ui.components.eco_news;

import com.greencity.ui.components.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AddImageComponent extends BaseComponent {

    @Getter
    @FindBy(css = ".dropzone")
    private WebElement imageContainer;

    @Getter
    @FindBy(id = "upload")
    private WebElement uploadImageField;

    @Getter
    @FindBy(css = ".centered label")
    private WebElement browseLink;

    @Getter
    @FindBy(css = ".cropper-buttons .secondary-global-button")
    private WebElement cancelButton;

    @Getter
    @FindBy(css = ".cropper-buttons .primary-global-button")
    private WebElement submitButton;

    @Getter
    @FindBy(css = ".image-block .warning")
    private WebElement warningMessage;

    public AddImageComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public AddImageComponent uploadImage(String filePath) {
        uploadImageField.sendKeys(filePath);
        return this;
    }

    public AddImageComponent clickBrowse() {
        browseLink.click();
        return this;
    }
    public void clickCancel() {
        cancelButton.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public String getWarningMessage() {
        return warningMessage.getText();
    }

    public boolean isWarningTextHighlighted() {
        return warningMessage.getAttribute("class").contains("warning-color");
    }

    public boolean isDropzoneHighlighted() {
        return imageContainer.getAttribute("class").contains("warning-background");
    }

    public boolean hasUploadError() {
        return isWarningTextHighlighted() && isDropzoneHighlighted();
    }

}
