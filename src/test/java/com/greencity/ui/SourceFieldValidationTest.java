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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.utils.NavItem.ECO_NEWS;

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

    @Test(dataProvider = "invalidUrls",
            description = "Error appears for invalid source URL"
    )
    public void errorIsDisplayedForInvalidSource(String invalidUrl) {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage
                .getSourceInput()
                .typeText(invalidUrl);

        softAssert.assertTrue(createNewsPage.getSourceInput().isWarningsForField(),
                "Source input should be invalid for incorrect URL format");
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

    @Test(dataProvider = "invalidUrlsForSubmit",
            description = "Publish button is disable when invalid Source URL"
    )
    public void publishButtonDisabledForInvalidSource(String invalidUrl) {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage
                .getSourceInput()
                .typeText(invalidUrl);

        softAssert.assertFalse(createNewsPage.getCreateNewsButtonsComponent().isPublishEnabled(),
                "Source input should be invalid for incorrect URL format");
        softAssert.assertAll();
    }
    @DataProvider
    public static Object[][] validSources() {
        return new Object[][]{
                {"http://test1.com"}
                //{"https://test2.com"}
        };
    }

    @Test(dataProvider = "validSources",
            description = "Successful News publishing with valid Source URL")
    public void isSuccessfulPublishedNewsWithValidSource(String validUrl) {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage.fillMandatoryFields(
                newsData.getTitle(),
                newsData.getTags(),
                newsData.getContentText()
        );
        
        System.out.println("Tags from provider: " + newsData.getTags());
        System.out.println("Selected tags in component: " + createNewsPage.getNewsTagsComponent().getSelectedTags());

        createNewsPage.getSourceInput().typeText(validUrl);
        EcoNewsPage ecoNewsPage = createNewsPage
                .getCreateNewsButtonsComponent()
                .clickPublish();

        EcoNewsTableCardComponent publishedNewsCard = ecoNewsPage.getOneTableCardByTitle(newsData.getTitle());

        softAssert.assertEquals(publishedNewsCard != null, true, "News should be present after publishing");
        Assert.assertNotNull(publishedNewsCard);
        softAssert.assertEquals(publishedNewsCard.getTitle(), newsData.getTitle(),
                "News title should match the one entered");
        softAssert.assertAll();
    }
}
