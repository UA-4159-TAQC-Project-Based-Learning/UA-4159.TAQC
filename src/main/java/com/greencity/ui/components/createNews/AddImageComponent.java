package com.greencity.ui.components.createNews;

import com.greencity.ui.components.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;


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


    @FindBy(css = ".image-block .warning")
    private WebElement validationMessage;

    @FindBy(css = ".image-preview img")
    private WebElement uploadedImagePreview;

    @Getter
    @FindBy(css = "image-cropper.cropper")
    private WebElement cropperRoot;

    @FindBy(css = "image-cropper.cropper .ngx-ic-cropper .ngx-ic-move")
    private WebElement cropMoveArea;

    @FindBy(css = "image-cropper.cropper .ngx-ic-cropper")
    private List<WebElement> cropFrames;

    @FindBy(css = "image-cropper.cropper .ngx-ic-overlay")
    private List<WebElement> cropOverlays;
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

    public boolean isImagePreviewDisplayedAlt() {
        try {
            return uploadedImagePreview.isDisplayed()
                    && uploadedImagePreview.getAttribute("src") != null
                    && !uploadedImagePreview.getAttribute("src").isEmpty();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check if cropper is active (frame/overlay visible)")
    public boolean isCropperActive() {
        return isAnyDisplayed(cropFrames) || isAnyDisplayed(cropOverlays);
    }

    private boolean isAnyDisplayed(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }
        try {
            return elements.stream().anyMatch(WebElement::isDisplayed);
        } catch (org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Check if dropzone is displayed in its initial state")
    public boolean isDropzoneDisplayed() {
        return imageContainer.isDisplayed() && browseLink.isDisplayed();
    }

    @Step("Check if crop overlay is displayed")
    public boolean isCropOverlayDisplayed() {
        return isAnyDisplayed(cropOverlays);
    }

    @Step("Check if crop frame is displayed")
    public boolean isCropFrameDisplayed() {
        return isAnyDisplayed(cropFrames);
    }

    @Step("Get crop frame size (width x height)")
    public Dimension getCropFrameSize() {
        return firstDisplayed(cropFrames).getSize();
    }

    @Step("Get crop frame position (top/left from style)")
    public String getCropFrameStylePosition() {
        return firstDisplayed(cropFrames).getAttribute("style");
    }

    @Step("Drag crop frame by offset dx={dx}, dy={dy}")
    public AddImageComponent dragCropFrameBy(int dx, int dy) {
        new Actions(driver)
                .clickAndHold(cropMoveArea)
                .moveByOffset(dx, dy)
                .release()
                .perform();
        return this;
    }

}
