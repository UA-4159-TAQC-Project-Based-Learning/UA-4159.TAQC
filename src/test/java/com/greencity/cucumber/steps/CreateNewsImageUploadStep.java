package com.greencity.cucumber.steps;

import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.pages.CreateNewsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

public class CreateNewsImageUploadStep {

    private final Hooks hooks;
    private AddImageComponent addImageComponent;
    private CreateNewsPage createNewsPage;

    private final String validationMessage =
            "Upload only PNG or JPG. File size must be less than 10MB";

    public CreateNewsImageUploadStep(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("the user uploads the image file {string}")
    public void uploadImage(String fileName) {
        String fullPath = "src/test/resources/images/" + fileName;

        createNewsPage = new CreateNewsPage(hooks.getDriver());
        addImageComponent = createNewsPage.getAddImageComponent();

        addImageComponent.uploadImage(new File(fullPath).getAbsolutePath());
    }

    @When("the user confirms image upload")
    public void confirmImageUpload() {
        addImageComponent.clickSubmit();
    }

    @Then("the image should be uploaded successfully")
    public void imageShouldUploadSuccessfully() {
        hooks.getSoftAssert().assertFalse(
                addImageComponent.hasUploadError(),
                "Image upload should not have errors"
        );
    }

    @Then("no validation errors should be displayed")
    public void noValidationErrorsDisplayed() {
        hooks.getSoftAssert().assertEquals(
                addImageComponent.getValidationMessage(),
                validationMessage,
                "Validation message should match expected"
        );

        hooks.getSoftAssert().assertFalse(
                addImageComponent.hasUploadError(),
                "Validation error was unexpectedly displayed"
        );
    }

    @Then("the image preview should be visible")
    public void imagePreviewVisible() {
        hooks.getSoftAssert().assertTrue(
                addImageComponent.isImagePreviewDisplayed(),
                "Image preview is not visible"
        );
    }

    @Then("a validation error should be displayed with the message {string}")
    public void validationErrorDisplayed(String expectedMessage) {
        hooks.getSoftAssert().assertEquals(
                addImageComponent.getValidationMessage(),
                expectedMessage,
                "Validation message does not match expected"
        );
    }

    @Then("the upload image field should be highlighted in red")
    public void uploadImageHighlightedInRed() {
        hooks.getSoftAssert().assertTrue(
                addImageComponent.isWarningTextHighlighted(),
                "Warning text is not highlighted"
        );

        hooks.getSoftAssert().assertTrue(
                addImageComponent.isDropzoneHighlighted(),
                "Dropzone is not highlighted"
        );
    }
}

