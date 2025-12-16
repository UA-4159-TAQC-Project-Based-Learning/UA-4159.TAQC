package com.greencity.ui.ecoNews;

import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NavItem;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.nio.file.Paths;

public class CropImageTest extends TestRunnerWithUser {

    private SoftAssert softAssert;
    private CreateNewsPage createNewsPage;

    private static final String imagePath =
            Paths.get("src/test/resources/images/crop-image-test-01.jpg")
                    .toAbsolutePath()
                    .toString();

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();

        driver.get(testValueProvider.getBaseUIUrl());
        driver.navigate().refresh();

        homePage = new HomePage(driver);
        homePage.waitUntilPageLoaded();

        createNewsPage = ((EcoNewsPage) homePage
                .getHeader()
                .openNavItem(NavItem.ECO_NEWS))
                .clickCreateNews();
    }

    @AfterMethod
    public void afterMethod() {
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }

    @Test
    @Issues({@Issue("U4T-42"), @Issue("U4T-47"), @Issue("U4T-43"), @Issue("U4T-44"), @Issue("U4T-45")
    })
    @Description("Verify that after uploading an image the predefined crop frame appears, can be moved, and Submit applies selection.")
    public void uploadImage_moveCropFrame_submit_shouldApplySelection() {
        AddImageComponent addImage = createNewsPage.getAddImageComponent();
        addImage.uploadImage(imagePath);

        softAssert.assertTrue(addImage.getCancelButton().isDisplayed(),
                "Cancel button should be visible after image upload.");
        softAssert.assertTrue(addImage.getSubmitButton().isDisplayed(),
                "Submit button should be visible after image upload.");

        softAssert.assertTrue(addImage.isCropOverlayDisplayed(),
                "Crop overlay should be displayed after image upload.");
        softAssert.assertTrue(addImage.isCropFrameDisplayed(),
                "Crop frame should be displayed after image upload.");

        var size = addImage.getCropFrameSize();
        softAssert.assertTrue(size.getWidth() > 0 && size.getHeight() > 0,
                "Crop frame should have a predefined non-zero size.");

        String styleBefore = addImage.getCropFrameStylePosition();
        addImage.dragCropFrameBy(50, 40);
        String styleAfter = addImage.getCropFrameStylePosition();

        softAssert.assertNotEquals(styleAfter, styleBefore,
                "Crop frame style (top/left) should change after dragging.");

        addImage.clickSubmit();
        softAssert.assertTrue(addImage.isImagePreviewDisplayed(),
                "Image preview should be displayed after submitting crop selection.");
    }

    @Test
    @Issue("U4T-46")
    @Description("Verify that Cancel returns image section to the initial dropzone state.")
    public void uploadImage_cancel_shouldReturnToDropzoneState() {
        AddImageComponent addImage = createNewsPage.getAddImageComponent();
        addImage.uploadImage(imagePath);

        softAssert.assertTrue(addImage.isCropperActive(),
                "Precondition: cropper should be active after uploading an image.");

        addImage.clickCancel();

        softAssert.assertTrue(addImage.isDropzoneDisplayed(),
                "After Cancel, dropzone should be displayed again.");

        softAssert.assertFalse(addImage.isCropperActive(),
                "After Cancel, cropper frame/overlay should not be displayed.");

        softAssert.assertFalse(addImage.isImagePreviewDisplayedAlt(),
                "After Cancel, uploaded image preview should not be displayed.");

    }
}
