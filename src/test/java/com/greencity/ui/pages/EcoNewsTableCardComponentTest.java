package com.greencity.ui.pages;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Epic("Eco News")
@Feature("Eco News Table Card Components")
public class EcoNewsTableCardComponentTest extends BaseTestRunner {

    @Test(description = "Verify that all Eco News table cards are loaded on the page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Open EcoNews page, scroll to middle, and verify that at least one news card exists")
    public void testEcoNewsTableCardFindAll() {
        Allure.step("Click on Eco News link from home page");
        homePage.getEcoNewsLink().click();

        Allure.step("Initialize EcoNewsPage and scroll to middle of the page");
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();

        Allure.step("Get all table cards on EcoNews page");
        List<EcoNewsTableCardComponent> tableList = ecoNewsPage.getAllTableCards();

        Allure.step("Verify that at least one table card is present");
        Assert.assertTrue(tableList.size() > 0, "No Eco News table cards were found on the page");
    }

    @Test(description = "Verify that a specific Eco News table card can be found by title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open EcoNews page, scroll to middle, find the news card by title, and assert it exists")
    public void testEcoNewsTableCardFindOne() {
        SoftAssert softAssert = new SoftAssert();

        Allure.step("Click on Eco News link from home page");
        homePage.getEcoNewsLink().click();

        Allure.step("Initialize EcoNewsPage and scroll to middle of the page");
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();

        String newsTitle = "My Tea Cups News1";
        Allure.step("Search for Eco News card with title: " + newsTitle);
        EcoNewsTableCardComponent oneTable = ecoNewsPage.getOneTableCardByTitle(newsTitle);

        softAssert.assertNotNull(oneTable, "Card with title '" + newsTitle + "' was not found on the page");

        Allure.step("Assert all soft assertion");
        softAssert.assertAll();
    }
}
