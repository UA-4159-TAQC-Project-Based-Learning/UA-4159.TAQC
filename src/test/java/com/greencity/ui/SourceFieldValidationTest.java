package com.greencity.ui;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.components.header.core.HeaderComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NavItem;
import com.greencity.utils.TestValueProvider;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.utils.NavItem.ECO_NEWS;

@Epic("News page")
@Story("Source Field Validation")
@Feature("News page: Create News functionality {2}")
@Issue("U4T-16")
@Severity(SeverityLevel.NORMAL)
@Link(name = "LINK goto site Green City", url = "https://www.greencity.cx.ua/#/greenCity")

public class SourceFieldValidationTest extends TestRunnerWithUser {

    private CreateNewsPage createNewsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        softAssert = new SoftAssert();
        testValueProvider = new TestValueProvider();

        BasePage page = homePage
                .getHeader()
                .openNavItem(ECO_NEWS);
        createNewsPage = ((EcoNewsPage) page).clickCreateNews();
    }

    @AfterMethod
    public void cleanSession() {
        createNewsPage.refreshPage();
        driver.manage().deleteAllCookies();
    }

    @DataProvider(name = "invalidUrls")
    public Object[][] invalidUrls() {
        return new Object[][] {
                {"invalid-url"},
                {"ht!tp:/bad"},
                {"12345"},
                {"http:/missing-slash"}
        };
    }

    @Description("Validate that invalid Source URLs '{invalidUrl}' show error state and red border in Source input field")
    @Test(dataProvider = "invalidUrls",
            description = "Error appears for invalid source URL"
    )
    public void errorIsDisplayedForInvalidSource(String invalidUrl) {

        createNewsPage
                .getSourceInput()
                .typeText(invalidUrl);

        softAssert.assertTrue(createNewsPage.getSourceInput().isWarningsForField(),
                "Source input should be invalid for incorrect URL format");

        String borderColor = createNewsPage.getSourceInput().getBorderColorWhenWarning();
        softAssert.assertEquals(borderColor, "rgb(255, 0, 0)", "Source border field should be red when invalid URL");
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidUrlsForSubmit")
    public Object[][] invalidUrlsForSubmit() {
        return new Object[][] {
                {"invalid-url"},
                {"ht!tp:/bad"},
                {"12345"},
                {"http:/missing-slash"},
                {"http://"},
                {"https://"},
                {"http:/m."},
                {"https://.com"},
                {"https://te st.com"},
                {" https://test.com"},
                {"https://te st.com "},
                {"https://testnodot"},
                {"https://-test.com"},
                {"https://test-.com"},
                {"https://test..com"},
                {"https:///test.com"},
                {"https://tes$t.com"},
                {"https://test.com/path space"}
        };
    }

    @Description("Verify that the Publish button is disabled when an invalid Source URL '{invalidUrl}' is entered")
    @Test(dataProvider = "invalidUrlsForSubmit",
            description = "Publish button is disabled when invalid Source URL"
    )
    public void publishButtonDisabledForInvalidSource(String invalidUrl) {

        createNewsPage
                .getSourceInput()
                .typeText(invalidUrl);

        softAssert.assertTrue(createNewsPage.getCreateNewsButtonsComponent().isPublishDisabled(),
                "Source input should be invalid for incorrect URL format");
        softAssert.assertAll();
    }
    @DataProvider
    public static Object[][] validSources() {
        return new Object[][]{
                {"http://test1.com"},
                {"https://test2.com"}
        };
    }

    @Description("Check that a news is successfully published when a valid Source URL '{validUrl}' is entered")
    @Test(dataProvider = "validSources",
            description = "Successful News publishing with valid Source URL")
    public void isSuccessfulPublishedNewsWithValidSource(String validUrl) {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage.fillMandatoryFields(
                newsData.getTitle(),
                newsData.getTags(),
                newsData.getContentText()
        );
        createNewsPage.getSourceInput().typeText(validUrl);
        EcoNewsPage ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();

        EcoNewsTableCardComponent publishedNewsCard = ecoNewsPage.getOneTableCardByTitle(newsData.getTitle());

        softAssert.assertEquals(publishedNewsCard != null, true, "News should be present after publishing");
        softAssert.assertEquals(publishedNewsCard.getTitle(), newsData.getTitle(),
                "News title should match the one entered");
        softAssert.assertAll();
    }

    @Description("Check that a news successfully published when the Source field is empty (optional field)")
    @Test(description = "Check that news can be published with empty Source field")
    public void isSuccessfulNewsPublishingWithEmptySource() {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage.fillMandatoryFields(
                newsData.getTitle(),
                newsData.getTags(),
                newsData.getContentText()
        );
        createNewsPage.getSourceInput().clear();
        EcoNewsPage ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();
        EcoNewsTableCardComponent publishedNewsCard = ecoNewsPage.getOneTableCardByTitle(newsData.getTitle());

        softAssert.assertEquals(publishedNewsCard != null, true, "News should be present after publishing");
        softAssert.assertEquals(publishedNewsCard.getTitle(), newsData.getTitle(),
                "News title should match the one entered");
        softAssert.assertAll();
    }
}
