package com.greencity.ui.testrunners;

import com.greencity.ui.components.newsFilter.FilterName;
import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
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
        EcoNewsPage ecoNewsPage = new HomePage(driver).openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            filter.clickFilterByName(filterName);
            softAssert.assertTrue(filter.isFilterActive(filterName), "Filter should be active: " + filterName.getValue());
        }
        softAssert.assertAll();
    }

    @Test
    public void testAllFilterButtonsVisible() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();
        Assert.assertTrue(filter.areAllFiltersVisible(), "Not all filter buttons are visible");
    }

    @Test
    public void testFilterButtonTexts() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            boolean found = filter.getFilterButtons().stream().anyMatch(span -> span.getText().trim().equalsIgnoreCase(filterName.getValue()));
            softAssert.assertTrue(found, "Filter button '" + filterName.getValue() + "' should exist");
        }
        softAssert.assertAll();
    }
}
