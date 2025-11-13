package com.greencity.ui.testrunners;


import com.greencity.ui.components.newsFilter.NewsFilterComponent;
import com.greencity.ui.pages.EcoNewsPage;
import org.testng.annotations.Test;

public class EcoNewsTests extends BaseTestRunner {

    private EcoNewsPage ecoNewsPage;


    @Test
    public void testFilterButtonsClickable() {
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        NewsFilterComponent filter = ecoNewsPage.getFilterComponent();

        filter.clickFilterByName("News");
        filter.clickFilterByName("Events");
        filter.clickFilterByName("Education");
        filter.clickFilterByName("Initiatives");
        filter.clickFilterByName("Ads");
    }

}
