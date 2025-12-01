package com.greencity.ui;

import com.greencity.data.MandatoryFieldsNewsData;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NavItem;
import com.greencity.utils.TestValueProvider;
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
                {"http:/missing-slash"},
        };
    }

    @Test(dataProvider = "invalidUrls",
            description = "Error appears for invalid source URL")
    public void errorIsDisplayedForInvalidSource(String invalidUrl) {
        MandatoryFieldsNewsData newsData = testValueProvider.getValidMandatoryFieldsNewsData();

        createNewsPage
                .getSourceInput()
                .typeText(invalidUrl);

        softAssert.assertTrue(createNewsPage.getSourceInput().isWarningsForField(),
                "Source input should be invalid for incorrect URL format");
        softAssert.assertAll();
    }

}
