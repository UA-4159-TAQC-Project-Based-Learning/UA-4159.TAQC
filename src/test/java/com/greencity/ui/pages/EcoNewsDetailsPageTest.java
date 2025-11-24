package com.greencity.ui.pages;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.SortedMap;

public class EcoNewsDetailsPageTest extends BaseTestRunner {

    @Test
    public void testEcoNewsDetailsPageElementsPresent() {
        SoftAssert softAssert = new SoftAssert();
        homePage.getEcoNewsLink().click();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();
        EcoNewsTableCardComponent oneTable = ecoNewsPage.getOneTableCardByTitle("Black cats live longer");
        EcoNewsDetailsPage detailsPage = oneTable.goToDetails();
        softAssert.assertEquals(detailsPage.getTitle(), "Black cats live longer");
        softAssert.assertEquals(detailsPage.getAuthorName(), "by Bob");
        softAssert.assertEquals(detailsPage.getNewsTextElement().getText(), "Black cats live twice as long as Orange cats");
        softAssert.assertAll();
    }

}
