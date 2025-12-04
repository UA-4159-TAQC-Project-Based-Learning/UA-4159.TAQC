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
        homePage.getHeader().clickEcoNewsNavItem();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();


        List<EcoNewsTableCardComponent> tableList = ecoNewsPage.getAllTableCards();

        Assert.assertFalse(tableList.isEmpty(), "No Eco News table cards were found on the page");
    }

    @Test(description = "Verify that a specific Eco News table card can be found by title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Open EcoNews page, scroll to middle, find the news card by title, and assert it exists")
    public void testEcoNewsTableCardFindOne() {
        SoftAssert softAssert = new SoftAssert();

        homePage.getHeader().clickEcoNewsNavItem();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();

        String newsTitle = "My Tea Cups News1";
        EcoNewsTableCardComponent oneTable = ecoNewsPage.getOneTableCardByTitle(newsTitle);

        softAssert.assertNotNull(oneTable, "Card with title '" + newsTitle + "' was not found on the page");

        softAssert.assertAll();
    }
}
