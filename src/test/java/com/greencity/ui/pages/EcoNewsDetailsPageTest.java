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

        homePage.getHeader().clickEcoNewsNavItem();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();

        EcoNewsTableCardComponent tableCard =
                ecoNewsPage.getOneTableCardByTitle("Test One Tag 1764861506356");

        EcoNewsDetailsPage detailsPage = tableCard.goToDetails();

        softAssert.assertEquals(detailsPage.getTitle(),
                "Test One Tag 1764861506356");

        softAssert.assertEquals(detailsPage.getAuthorName(),
                "by Liubomyr Halamaha");

        softAssert.assertEquals(detailsPage.getNewsTextElement().getText(),
                "Test content with 20 chars");

        softAssert.assertAll();
    }
}
