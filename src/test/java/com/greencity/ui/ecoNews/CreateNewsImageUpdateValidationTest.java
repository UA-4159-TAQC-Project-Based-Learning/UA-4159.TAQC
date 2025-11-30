package com.greencity.ui.ecoNews;

import com.greencity.ui.components.createNews.AddImageComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class CreateNewsImageUpdateValidationTest  extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;
    String filePathCorrectImagePNG = new File("src/test/resources/images/imagePNG5MB.png").getAbsolutePath();
    String filePathCorrectImageJPG = new File("src/test/resources/images/imageJPG5MB.jpg").getAbsolutePath();
    String filePathWrongFormatImageGIF = new File("src/test/resources/images/imageGIF1MB.gif").getAbsolutePath();
    String filePathBigSizeImageJPG = new File("src/test/resources/images/ImageJPG15MB.jpg").getAbsolutePath();
    String validationMessage = "Upload only PNG or JPG. File size must be less than 10MB";


    @BeforeMethod
    public void openCreateNewsPage() {
        String createNewsUrl = testValueProvider.getBaseUIUrl() + "/news/create-news";
        driver.get(createNewsUrl);

        createNewsPage = new CreateNewsPage(driver);
        createNewsPage.waitForPageToLoad(10);
    }


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
        image.clickCancel();

    }

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
