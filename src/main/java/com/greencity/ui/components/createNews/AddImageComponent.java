package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
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
    private WebElement validationMessage;

    @FindBy(css = ".image-preview img")
    private WebElement uploadedImagePreview;

    public AddImageComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Upload image from: '{filePath}'")
    public AddImageComponent uploadImage(String filePath) {
        uploadImageField.sendKeys(filePath);
        return this;
    }

    @Step("Click Browse image link")
    public AddImageComponent clickBrowse() {
        browseLink.click();
        return this;
    }

    @Step("Click Cancel image upload")
    public void clickCancel() {
        cancelButton.click();
    }

    @Step("Confirm image upload (Submit)")
    public void clickSubmit() {
        submitButton.click();
    }

    @Step("Get image validation message")
    public String getValidationMessage() {
        return validationMessage.getText();
    }


    @Step("Check if ValidationMessage is highlighted")
    public boolean isWarningTextHighlighted() {
        return validationMessage.getAttribute("class").contains("warning-color");
    }

    @Step("Check if ImageContainer is highlighted")
    public boolean isDropzoneHighlighted() {
        return imageContainer.getAttribute("class").contains("warning-background");
    }

    @Step("Check if upload errors are shown")
    public boolean hasUploadError() {
        return isWarningTextHighlighted() && isDropzoneHighlighted();
    }

    public boolean isImagePreviewDisplayed() {
        return uploadedImagePreview.isDisplayed() &&
                    uploadedImagePreview.getAttribute("src") != null &&
                    !uploadedImagePreview.getAttribute("src").isEmpty();
    }

}
