package com.greencity.ui.pages;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic("Eco News")
@Feature("News Details")
public class EcoNewsDetailsPageTest extends BaseTestRunner {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open Eco News, scroll, find a news card, and validate title, author, and text")
    public void testEcoNewsDetailsPageElementsPresent() {

        SoftAssert softAssert = new SoftAssert();

        Allure.step("Open Eco News page");
        homePage.getEcoNewsLink().click();

        Allure.step("Scroll to the middle of the page");
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();

        Allure.step("Find news card by title: New Eco-Event Announced for Weekend");
        EcoNewsTableCardComponent tableCard =
                ecoNewsPage.getOneTableCardByTitle("New Eco-Event Announced for Weekend");

        Allure.step("Open the news details page");
        EcoNewsDetailsPage detailsPage = tableCard.goToDetails();

        Allure.step("Validate title");
        softAssert.assertEquals(detailsPage.getTitle(),
                "New Eco-Event Announced for Weekend");

        Allure.step("Validate author");
        softAssert.assertEquals(detailsPage.getAuthorName(),
                "by Maryna Lobatiuk");

        Allure.step("Validate news text");
        softAssert.assertEquals(detailsPage.getNewsTextElement().getText(),
                "A community garden opened this week, inviting residents to grow organic vegetables and learn about sustainable land use.");

        Allure.step("Assert all collected checks");
        softAssert.assertAll();
    }
}
