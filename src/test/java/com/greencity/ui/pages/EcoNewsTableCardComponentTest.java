package com.greencity.ui.pages;

import com.greencity.ui.components.eco_news.EcoNewsTableCardComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class EcoNewsTableCardComponentTest extends BaseTestRunner {

    @Test
    public void testEcoNewsTableCardFindAll() {
        homePage.getEcoNewsLink().click();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();
        List<EcoNewsTableCardComponent> tableList = ecoNewsPage.getAllTableCards();
        Assert.assertTrue(tableList.size() > 0);
    }

    @Test
    public void testEcoNewsTableCardFindOne() {
        homePage.getEcoNewsLink().click();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.scrollToMiddlePage();
        EcoNewsTableCardComponent oneTable = ecoNewsPage.getOneTableCardByTitle("My Tea Cups News1");
        Assert.assertNotNull(oneTable);
    }


}
