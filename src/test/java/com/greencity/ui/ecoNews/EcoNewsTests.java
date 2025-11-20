package com.greencity.ui.ecoNews;

import com.greencity.ui.components.newsFilter.FilterName;
import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import lombok.Getter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EcoNewsTests extends BaseTestRunner {

    @Getter
    private SoftAssert softAssert;

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @Test
    public void testFilterButtonsClickable() {
        HomePage homePage = new HomePage(driver);
        homePage.getEcoNewsLink().click();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        NewsFilterComponent filter = ecoNewsPage.getNewsFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            filter.clickFilterByName(filterName);
            softAssert.assertTrue(filter.isFilterActive(filterName), "Filter should be active: " + filterName.getValue());
        }
        softAssert.assertAll();
    }

    @Test
    public void testAllFilterButtonsVisible() {
        HomePage homePage = new HomePage(driver);

        homePage.getEcoNewsLink().click();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);

        NewsFilterComponent filter = ecoNewsPage.getNewsFilterComponent();
        Assert.assertTrue(filter.areAllFiltersVisible(), "Not all filter buttons are visible");
    }

    @Test
    public void testFilterButtonTexts() {
        HomePage homePage = new HomePage(driver);

        homePage.getEcoNewsLink().click();

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        NewsFilterComponent filter = ecoNewsPage.getNewsFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            boolean found = filter.getFilterButtons().stream().anyMatch(span -> span.getText().trim().equalsIgnoreCase(filterName.getValue()));
            softAssert.assertTrue(found, "Filter button '" + filterName.getValue() + "' should exist");
        }
        softAssert.assertAll();
    }
}
