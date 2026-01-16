package com.greencity.cucumber.steps;

import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Dimension;

import java.nio.file.Paths;

public class CropImageSteps {

    private final Hooks hooks;

    private CreateNewsPage createNewsPage;
    private EcoNewsPage ecoNewsPage;
    private AddImageComponent addImage;

    private String cropFrameStyleBefore;
    private String cropFrameStyleAfter;

    public CropImageSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("the user uploads image for cropping {string}")
    public void uploadImageForCropping(String fileName) {
        createNewsPage = new CreateNewsPage(hooks.getDriver());

        String imagePath = Paths.get("src/test/resources/images", fileName)
                .toAbsolutePath()
                .toString();

        addImage = createNewsPage.getAddImageComponent();
        addImage.uploadImage(imagePath);
    }

    @Then("crop action buttons should be visible")
    public void cropActionButtonsShouldBeVisible() {
        hooks.getSoftAssert().assertTrue(
                addImage.getCancelButton().isDisplayed(),
                "Cancel button should be visible after image upload."
        );
        hooks.getSoftAssert().assertTrue(
                addImage.getSubmitButton().isDisplayed(),
                "Submit button should be visible after image upload."
        );
    }

    @Then("crop overlay and frame should be visible")
    public void cropOverlayAndFrameShouldBeVisible() {
        hooks.getSoftAssert().assertTrue(
                addImage.isCropOverlayDisplayed(),
                "Crop overlay should be displayed after image upload."
        );
        hooks.getSoftAssert().assertTrue(
                addImage.isCropFrameDisplayed(),
                "Crop frame should be displayed after image upload."
        );
    }

    @Then("crop frame should have non-zero size")
    public void cropFrameShouldHaveNonZeroSize() {
        Dimension size = addImage.getCropFrameSize();
        hooks.getSoftAssert().assertTrue(
                size.getWidth() > 0 && size.getHeight() > 0,
                "Crop frame should have a predefined non-zero size."
        );
    }

    @When("the user drags crop frame by {int} and {int}")
    public void dragCropFrameBy(int dx, int dy) {
        cropFrameStyleBefore = addImage.getCropFrameStylePosition();
        addImage.dragCropFrameBy(dx, dy);
        cropFrameStyleAfter = addImage.getCropFrameStylePosition();
    }

    @Then("crop frame position should change")
    public void cropFramePositionShouldChange() {
        hooks.getSoftAssert().assertNotEquals(
                cropFrameStyleAfter,
                cropFrameStyleBefore,
                "Crop frame style (top/left) should change after dragging."
        );
    }

    @When("the user submits cropped image")
    public void submitCroppedImage() {
        addImage.clickSubmit();
    }

    @Then("image preview should be displayed")
    public void imagePreviewShouldBeDisplayed() {
        createNewsPage.setImplicitWaitSeconds(0);
        hooks.getSoftAssert().assertTrue(
                addImage.isImagePreviewDisplayed(),
                "Image preview should be displayed after submitting crop selection."
        );
        createNewsPage.setImplicitWaitSeconds(createNewsPage.getDEFAULT_IMPLICIT());
    }

    @Then("cropper should be active")
    public void cropperShouldBeActive() {
        createNewsPage.setImplicitWaitSeconds(0);
        hooks.getSoftAssert().assertTrue(
                addImage.isCropperActive(),
                "Precondition: cropper should be active after uploading an image."
        );
        createNewsPage.setImplicitWaitSeconds(createNewsPage.getDEFAULT_IMPLICIT());
    }

    @When("the user cancels image cropping")
    public void cancelImageCropping() {
        addImage.clickCancel();
    }

    @Then("dropzone should be displayed")
    public void dropzoneShouldBeDisplayed() {
        hooks.getSoftAssert().assertTrue(
                addImage.isDropzoneDisplayed(),
                "After Cancel, dropzone should be displayed again."
        );
    }

    @Then("cropper should not be active")
    public void cropperShouldNotBeActive() {
        createNewsPage.setImplicitWaitSeconds(0);
        hooks.getSoftAssert().assertFalse(
                addImage.isCropperActive(),
                "After Cancel, cropper frame/overlay should not be displayed."
        );
        createNewsPage.setImplicitWaitSeconds(createNewsPage.getDEFAULT_IMPLICIT());
    }

    @Then("image preview should not be displayed")
    public void imagePreviewShouldNotBeDisplayed() {
        createNewsPage.setImplicitWaitSeconds(0);
        hooks.getSoftAssert().assertFalse(
                addImage.isImagePreviewDisplayedAlt(),
                "After Cancel, uploaded image preview should not be displayed."
        );
        createNewsPage.setImplicitWaitSeconds(createNewsPage.getDEFAULT_IMPLICIT());
    }
}
