package com.greencity.ui.ecoNews;

import com.greencity.ui.components.createNews.CancelNewsModal;
import com.greencity.ui.components.createNews.CreateNewsButtonsComponent;
import com.greencity.ui.pages.CreateNewsPage;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.ui.utils.NavItem;
import com.greencity.utils.EcoNewsTestData;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CancelNewsTest extends TestRunnerWithUser {

    private SoftAssert softAssert;
    private CreateNewsPage createNewsPage;

    // Used for one test only - headerNav_emptyForm_shouldNavigateWithoutPopup()
    public static final String CREATE_NEWS_URL = "/create-news";

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();

        // 'clear state' after prev test -> need for parameterized tests to work
        driver.get(testValueProvider.getBaseUIUrl());
        driver.navigate().refresh();

        homePage = new HomePage(driver);
        homePage.waitUntilPageLouder();
        // pre-condition for all test cases -> user is on Create News page with empty form
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
    @Issue("U4T-30")
    @Description("Verify that the 'Cancel' button is displayed and enabled on the Create News page.")
    public void cancelButtonIsVisibleAndEnabledOnCreateNewsPage() {
        CreateNewsButtonsComponent buttons = createNewsPage.getCreateNewsButtonsComponent();

        softAssert.assertTrue(buttons.getCancelButton().isDisplayed(), "Cancel button should be visible on Create News page.");
        softAssert.assertTrue(buttons.getCancelButton().isEnabled(), "Cancel button should be enabled on Create News page.");
    }

    @Test(
            dataProvider = "createNewsDataForCancelTests",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issues({
            @Issue("U4T-35"),
            @Issue("U4T-31")
    })
    @Description("Verify that clicking 'Cancel' on a filled Create News form opens a confirmation popup with the expected elements.")
    public void cancel_shouldShowConfirmationPopup(String title, String content) {
        CancelNewsModal cancelNewsModal = createNewsPage
                .enterTitleAndContent(title, content)
                .openCancelModal()
                .waitUntilOpened();

        softAssert.assertTrue(cancelNewsModal.getCancelNewsChangesModalTitle().isDisplayed(),
                "Cancel confirmation popup title should be visible when cancelling with data entered."
        );

        softAssert.assertTrue(
                cancelNewsModal.getContinueNewsEditingModalButtonAlternative().isDisplayed(),
                "'Continue editing' button should be visible in the popup when cancelling with data entered."
        );

        softAssert.assertTrue(
                cancelNewsModal.getYesCancelChangesModalButtonAlternative().isDisplayed(),
                "'Yes, cancel' button should be visible in the popup when cancelling with data entered."
        );
    }

    @Test(
            dataProvider = "uniqueTitleAndContentToCancel",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-32")
    @Description("Verify that confirming cancel discards the news, redirects to Eco News page, and clears the Create News form when reopened.")
    public void confirmCancel_shouldDiscardData_andRedirectToEcoNews(String uniqueTitle, String uniqueContent) {
        CancelNewsModal cancelNewsModal = createNewsPage
                .enterTitleAndContent(uniqueTitle, uniqueContent)
                .openCancelModal()
                .waitUntilOpened();

        cancelNewsModal.getYesCancelChangesModalButtonAlternative().click();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.waitUntilPageLouder();
        String currentUrl = driver.getCurrentUrl();

        // Verifications that user is redirected back to Eco News page
        softAssert.assertTrue(
                ecoNewsPage.ecoNewsPageIsOpened(),
                "User should be redirected back to Eco News page after confirming cancel."
        );
        Assert.assertNotNull(currentUrl);
        softAssert.assertTrue(
                currentUrl.contains(NavItem.ECO_NEWS.href()),
                "After confirming cancel, URL should point to Eco News page. Actual: " + currentUrl
        );

        // Verification that news item is not created after cancel
        softAssert.assertNull(
                ecoNewsPage.findCardByTitle(uniqueTitle),
                "Cancelled news should NOT appear in Eco News list."
        );

        // If user reopens Create News, form should be empty
        createNewsPage = ecoNewsPage.clickCreateNews();
        String actualTitleValue = createNewsPage.getTitleInput().getValue();
        String actualContentValue = createNewsPage.getTextEditor().getText();

        softAssert.assertEquals(actualTitleValue, "",
                "Title input should be empty after reopening Create News."
        );

        softAssert.assertTrue(actualContentValue == null || actualContentValue.isBlank(),
                "Content editor should be empty after reopening Create News."
        );
    }

    @Test(
            dataProvider = "uniqueTitleAndContentToCancel",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-33")
    @Description("Verify that cancelling in the confirmation popup keeps the user on Create News page and preserves the entered data.")
    public void cancelInPopup_shouldStayOnPage_andKeepEnteredData(String expectedTitle, String expectedContent) {
        CancelNewsModal cancelNewsModal = createNewsPage
                .enterTitleAndContent(expectedTitle, expectedContent)
                .openCancelModal()
                .waitUntilOpened();

        cancelNewsModal.getContinueNewsEditingModalButtonAlternative().click();
        cancelNewsModal.waitForModalClosed();

        softAssert.assertTrue(
                createNewsPage.getPageTitle().isDisplayed(),
                "User should stay on Create News page after cancelling in the confirmation popup."
        );

        String actualTitle = createNewsPage.getTitleInput().getValue();
        String actualContent = createNewsPage.getTextEditor().getText();

        softAssert.assertEquals(actualTitle, expectedTitle,
                "Title input value should remain unchanged after cancelling in popup."
        );

        softAssert.assertEquals(actualContent, expectedContent,
                "Content editor value should remain unchanged after cancelling in popup."
        );
    }

    @Test(
            dataProvider = "uniqueTitleAndContentToCancel",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-36")
    @Description("Verify that clicking outside the cancel popup closes it and keeps previously entered data.")
    public void clickOutsidePopUp_shouldClosePopup_andKeepData(String expectedTitle, String expectedContent) {
        CancelNewsModal cancelNewsModal = createNewsPage
                .enterTitleAndContent(expectedTitle, expectedContent)
                .openCancelModal()
                .waitUntilOpened();

        cancelNewsModal.closeByClickingOutsidePopUp();

        softAssert.assertTrue(
                createNewsPage.getPageTitle().isDisplayed(),
                "User should stay on Create News page after cancelling in the confirmation popup."
        );

        String actualTitle = createNewsPage.getTitleInput().getValue();
        String actualContent = createNewsPage.getTextEditor().getText();

        softAssert.assertEquals(actualTitle, expectedTitle,
                "Title input value should remain unchanged after cancelling in popup."
        );

        softAssert.assertEquals(actualContent, expectedContent,
                "Content editor value should remain unchanged after cancelling in popup."
        );
    }

    @Test(
            dataProvider = "uniqueTitleAndContentToCancel",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-39")
    @Description("Verify that closing the cancel popup via the cross icon keeps the user on Create News and preserves the entered data.")
    public void closeViaCross_shouldKeepData(String expectedTitle, String expectedContent) {
        CancelNewsModal cancelNewsModal = createNewsPage
                .enterTitleAndContent(expectedTitle, expectedContent)
                .openCancelModal()
                .waitUntilOpened();

        cancelNewsModal.getCrossIconForCloseChangesModalAlternative().click();
        cancelNewsModal.waitForModalClosed();

        softAssert.assertTrue(
                createNewsPage.getPageTitle().isDisplayed(),
                "User should stay on Create News page after cancelling in the confirmation popup."
        );

        String actualTitle = createNewsPage.getTitleInput().getValue();
        String actualContent = createNewsPage.getTextEditor().getText();

        softAssert.assertEquals(actualTitle, expectedTitle,
                "Title input value should remain unchanged after cancelling in popup."
        );

        softAssert.assertEquals(actualContent, expectedContent,
                "Content editor value should remain unchanged after cancelling in popup."
        );
    }

    @Test(
            dataProvider = "navItemsForCancelTest",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-40")
    @Description("Verify that attempt to navigate via header with filled Create News form shows the cancel confirmation popup.")
    public void headerNav_withData_shouldShowConfirmationPopup(NavItem item) {
        createNewsPage
                .enterTitleAndContent("Title entered", "Content entered")
                .getHeader()
                .clickNavItemWithoutNavigation(item);

        CancelNewsModal cancelNewsModal = new CancelNewsModal(driver)
                .waitUntilOpened();

        softAssert.assertTrue(
                cancelNewsModal.getCancelNewsChangesModalTitle().isDisplayed(),
                "Cancel confirmation popup title should be visible when cancelling with data entered."
        );

        softAssert.assertTrue(
                cancelNewsModal.getContinueNewsEditingModalButtonAlternative().isDisplayed(),
                "'Continue editing' button should be visible in the popup when cancelling with data entered."
        );

        softAssert.assertTrue(
                cancelNewsModal.getYesCancelChangesModalButtonAlternative().isDisplayed(),
                "'Yes, cancel' button should be visible in the popup when cancelling with data entered."
        );
    }

    @Test(
            dataProvider = "navItemsForCancelTest",
            dataProviderClass = EcoNewsTestData.class
    )
    @Issue("U4T-41")
    @Description("Verify that navigating via header from Create News with an empty form does not show a popup and redirects.")
    public void headerNav_emptyForm_shouldNavigateWithoutPopup(NavItem item) {
        String initialUrl = driver.getCurrentUrl();
        Assert.assertNotNull(initialUrl);
        softAssert.assertTrue(
                initialUrl.contains(CREATE_NEWS_URL),
                "Precondition: user should be on Create News page."
        );

        createNewsPage.getHeader().openNavItem(item);
        String currentUrl = driver.getCurrentUrl();

        softAssert.assertFalse(
                currentUrl.contains(CREATE_NEWS_URL),
                "User should not stay on Create News page after clicking header with empty form."
        );

        switch (item) {
            case ECO_NEWS -> {
                softAssert.assertTrue(
                        currentUrl.contains("/#/greenCity/news"),
                        "Clicking ECO_NEWS with empty form should navigate to Eco News list page. Actual URL: " + currentUrl
                );
            }
            case LOGO -> {
                softAssert.assertEquals(
                        currentUrl,
                        testValueProvider.getBaseUIUrl(),
                        "Clicking logo with empty form should navigate to Home page."
                );
            }
        }
    }

    @Test
    @Issue("U4T-34")
    @Description("Verify that the cancel confirmation popup appears within one second after clicking Cancel.")
    public void cancelNews_popupShouldAppearWithinOneSecond() {
        createNewsPage.enterTitleAndContent("Title entered", "Content entered");
        CreateNewsButtonsComponent buttons = createNewsPage.getCreateNewsButtonsComponent();

        long startTime = System.currentTimeMillis();

        buttons.getCancelButton().click();
        CancelNewsModal modal = createNewsPage.getCancelNewsModal();

        long timeTaken = System.currentTimeMillis() - startTime;

        softAssert.assertTrue(
                modal.isVisible(),
                "Cancel popup did not become visible."
        );

        softAssert.assertTrue(
                timeTaken <= 1000,
                "Popup appeared in " + timeTaken + " ms (should be <= 1000 ms)."
        );
    }
}