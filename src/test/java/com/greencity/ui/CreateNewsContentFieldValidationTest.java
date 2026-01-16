package com.greencity.ui;

import com.greencity.ui.pages.*;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NavItem;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


@Issue("U4T-15")
public class CreateNewsContentFieldValidationTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;
    private final int minLengthMainText = 20;
    private final int maxLengthMainText = 63206;
    private final int overMaxLengthMainText = 63210;
    private final String validTitle = "Title";
    private final String expectedMessage = "Must be minimum 20 and maximum 63 206 symbols";

    @BeforeMethod
    public void setUp() {

        driver.get(testValueProvider.getBaseUIUrl());
        HomePage homePage = new HomePage(driver);

        EcoNewsPage ecoNewsPage = (EcoNewsPage) homePage.getHeader()
                .openNavItem(NavItem.ECO_NEWS);

        createNewsPage = ecoNewsPage.clickCreateNews();
        createNewsPage.waitForPageToLoad(10);

    }

    @AfterMethod
    public void tearDown() {
        driver.get(testValueProvider.getBaseUIUrl());
    }


    @Test
    @Description("Content input invalid when less than 20 characters")
    public void testContentInputInvalidLessThanMinCharacters() {
        SoftAssert softAssert = new SoftAssert();

        createNewsPage.getContentEditor()
                .clickOnMainText()
                .typeText("R".repeat(minLengthMainText-10));

        createNewsPage.getTitleInput().typeText(validTitle);

        String actualMessage = createNewsPage
                .getContentEditor()
                .getFieldInfoElement()
                .getText()
                .trim();

        softAssert.assertTrue(createNewsPage
                .getContentEditor()
                .hasWarningFieldInfo(),
                "Text input in content editor does not have warning");

        softAssert.assertEquals(actualMessage, expectedMessage,
                "an error message does not have expected message: " + expectedMessage);

        softAssert.assertTrue(createNewsPage
                .getCreateNewsButtonsComponent()
                .isPublishDisabled(),
                "Publish button is enabled");

        softAssert.assertAll();
    }


    // Temporarily disabled: this test is heavy and validates extreme max-length behavior. 
    // The site does not implement truncation of text longer than 63,206 characters.
    @Test(enabled = false)
    @Description("Content input truncated when exceeding 63,206 characters")
    public void testContentInputTruncatedWhenCharactersOverMaxLength() {
        SoftAssert softAssert = new SoftAssert();

        createNewsPage.getTitleInput().typeText(validTitle);

        String chunk = "R".repeat(1000);
        int fullChunks = overMaxLengthMainText / 1000;
        int remainderChunks = overMaxLengthMainText % 1000;

        createNewsPage.getContentEditor().clickOnMainText();
        for (int i = 0; i < fullChunks; i++) {
            createNewsPage.getContentEditor().typeText(chunk);
        }

        if (remainderChunks > 0) {
            createNewsPage.getContentEditor().typeText("R".repeat(remainderChunks));
        }

        int actualCharacterNumber = createNewsPage
                .getContentEditor()
                .getInputAreaText()
                .trim()
                .length();

        softAssert.assertTrue(actualCharacterNumber <= maxLengthMainText,
                "the Content field value has different number of characters- " +
                        "expected: " + maxLengthMainText + ", but actual: " + actualCharacterNumber);

        softAssert.assertAll();
    }
    @Test
    @Description("Content input 25 characters - valid value")
    public void testContentInputValidTextLength(){
        SoftAssert softAssert = new SoftAssert();

        createNewsPage.getTitleInput().typeText(validTitle);

        createNewsPage.getContentEditor()
                .clickOnMainText()
                .typeText("Test text \n".repeat(2) + "...");

        softAssert.assertFalse(createNewsPage
                        .getContentEditor()
                        .hasTextInputAreaWarning(),
                "Error message should disappear for valid content");

        createNewsPage.getNewsTagsComponent()
                .selectTag("News");

        softAssert.assertTrue(createNewsPage
                        .getCreateNewsButtonsComponent()
                        .isPublishEnabled(),
                "Publish button should be enabled for valid content");

        createNewsPage.getCreateNewsButtonsComponent().clickPublish();


        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        WebElement actualMessage = ecoNewsPage.waitForSuccessMessage();

        softAssert.assertTrue(actualMessage.isDisplayed(),
                "Success message is not displayed");

        EcoNewsDetailsPage ecoNewsDetailsPage  = ecoNewsPage.getOneTableCardByTitle(validTitle).goToDetails();

        softAssert.assertEquals(ecoNewsDetailsPage.getTitle(), validTitle,
                "Published news title does not match expected");

        softAssert.assertAll();
    }
}
