package com.greencity.ui.ecoNews;

import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class CreateNewsImageUpdateValidationTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;
    private final String filePathCorrectImagePNG = new File("src/test/resources/images/imagePNG5MB.png").getAbsolutePath();
    private final String filePathCorrectImageJPG = new File("src/test/resources/images/imageJPG5MB.jpg").getAbsolutePath();
    private final String filePathWrongFormatImageGIF = new File("src/test/resources/images/imageGIF1MB.gif").getAbsolutePath();
    private final String filePathBigSizeImageJPG = new File("src/test/resources/images/imageJPG15MB.jpg").getAbsolutePath();
    private final String validationMessage = "Upload only PNG or JPG. File size must be less than 10MB";


    @BeforeMethod
    public void openCreateNewsPage() {
        homePage.refreshPage();
        createNewsPage = homePage.getHeader().clickEcoNewsNavItem().clickCreateNews();
    }

    @Issue("U4T-14")
    @Description("Verify uploading valid PNG/JPG images up to 10MB")
    @Test(description = "User can successfully upload image files up to 10MB", dataProvider = "validImages")
    public void testUploadImage(String imagePath) {
        SoftAssert softAssert = new SoftAssert();

        AddImageComponent image = createNewsPage
                .getAddImageComponent()
                .uploadImage(imagePath);


        softAssert.assertEquals(image.getValidationMessage(), validationMessage,
                "Wrong validation message");
        softAssert.assertFalse(image.hasUploadError(), "Error on uploading image");

        image.clickSubmit();
        softAssert.assertFalse(image.hasUploadError(), "Error on uploading image");
        softAssert.assertTrue(image.isImagePreviewDisplayed(), "Uploaded image preview not displayed");
        softAssert.assertAll();

    }

    @Issue("U4T-14")
    @Description("Verify that uploading invalid GIF format shows warning")
    @Test(description = "Warning message shown if upload GIF files")
    public void testUploadImageGIF() {
        SoftAssert softAssert = new SoftAssert();

        AddImageComponent image = createNewsPage
                .getAddImageComponent()
                .uploadImage(filePathWrongFormatImageGIF);


        softAssert.assertEquals(image.getValidationMessage(), validationMessage,
                "Wrong validation message");

        softAssert.assertTrue(image.isWarningTextHighlighted(), "Warning message is not highlighted");
        softAssert.assertTrue(image.isDropzoneHighlighted(), "Upload Image field is not highlighted");

        softAssert.assertAll();

    }

    @Issue("U4T-14")
    @Description("Verify that uploading images over 10MB shows warning")
    @Test(description = "Warning message shown if upload image over 10 MB")
    public void testUploadBigImage() {
        SoftAssert softAssert = new SoftAssert();

        AddImageComponent image = createNewsPage
                .getAddImageComponent()
                .uploadImage(filePathBigSizeImageJPG);


        softAssert.assertEquals(image.getValidationMessage(), validationMessage,
                "Wrong validation message");

        softAssert.assertTrue(image.isWarningTextHighlighted(), "Warning message is not highlighted");
        softAssert.assertTrue(image.isDropzoneHighlighted(), "Upload Image field is not highlighted");

        softAssert.assertAll();

    }

    @DataProvider
    public Object[][] validImages() {
        return new Object[][]{
                {filePathCorrectImagePNG},
                {filePathCorrectImageJPG}
        };


    }
}
