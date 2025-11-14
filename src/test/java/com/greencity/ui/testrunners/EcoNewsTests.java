package com.greencity.ui.testrunners;

import com.greencity.ui.components.newsFilter.FilterName;
import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import com.greencity.ui.pages.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class EcoNewsTests extends BaseTestRunner {

    private EcoNewsPage ecoNewsPage;

    @Test
    public void testFilterButtonsClickable() {
        EcoNewsPage ecoNewsPage = new HomePage(driver).openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            filter.clickFilterByName(filterName);
            Assertions.assertTrue(filter.isFilterActive(filterName),
                    "Filter should be active: " + filterName.getValue());
        }
    }

    @Test
    public void testAllFilterButtonsVisible() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();
        Assertions.assertTrue(filter.areAllFiltersVisible(), "Not all filter buttons are visible");
    }

    @Test
    public void testFilterButtonTexts() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            boolean found = filter.getFilterButtons().stream()
                    .anyMatch(span -> span.getText().trim().equalsIgnoreCase(filterName.getValue()));
            Assertions.assertTrue(found, "Filter button '" + filterName.getValue() + "' should exist");
        }
    }

    @Test
    public void testFilterButtonActivation() {
        EcoNewsPage ecoNewsPage = homePage.openEcoNewsPage();
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        for (FilterName filterName : FilterName.values()) {
            filter.clickFilterByName(filterName);
            Assertions.assertTrue(filter.isFilterActive(filterName),
                    "Filter '" + filterName.getValue() + "' should be active after click");
        }
    }
}
